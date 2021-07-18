package com.kunyiduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kunyiduan.bean.PointsParam;
import com.kunyiduan.bean.user.LoginPhoneParam;
import com.kunyiduan.bean.user.RegisterParam;
import com.kunyiduan.bean.user.UserBO;
import com.kunyiduan.bean.user.UserInfoVO;
import com.kunyiduan.entity.User;
import com.kunyiduan.enums.ResultCode;
import com.kunyiduan.exception.BusinessException;
import com.kunyiduan.feign.PointsFeignClient;
import com.kunyiduan.jwt.JwtUtil;
import com.kunyiduan.mapper.UserMapper;
import com.kunyiduan.service.UserService;
import com.kunyiduan.util.AESUtil;
import com.kunyiduan.util.EncryptUtil;
import com.kunyiduan.utils.ConstantUtil;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author achilles
 * @since 2020-07-08
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EncryptUtil encryptUtil;

    @Autowired
    private AESUtil AESUtil;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private PointsFeignClient pointsFeignClient;

    @Value("${user.redis.expired.time:}")
    private int useRedisExpiredTime;

    @Override
//    @Transactional(rollbackFor = RuntimeException.class)
    @GlobalTransactional(timeoutMills = 300000, name = "dubbo-gts-seata-example")
    public boolean register(RegisterParam registerParam) {
        User user = new User();
        BeanUtils.copyProperties(registerParam, user);
        String originalPassword = AESUtil.decrypt(registerParam.getPassword());
        user.setPassword(encryptUtil.encryptSha1(originalPassword));
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        int count = baseMapper.insert(user);
        //注册成功，送100积分--异步调用
        UserBO userBO = this.getUserByPhone(registerParam.getTelephone());
        log.debug(userBO.toString());
        PointsParam pointsParam = new PointsParam(Long.parseLong(userBO.getId()), 100);
        pointsFeignClient.create(pointsParam);
        return count == 1 ? true : false;
    }

    @Override
    public String login(LoginPhoneParam loginPhoneParam) {
        UserBO userBO = this.getUserByPhone(loginPhoneParam.getTelephone());
        if (userBO != null) {
            String originalPassword = AESUtil.decrypt(loginPhoneParam.getPassword());
            String encryptionPassword = encryptUtil.encryptSha1(originalPassword);
            if (userBO.getPassword().equals(encryptionPassword)) {
                String token = jwtUtil.sign(userBO.getId(), userBO.getTelephone());
                return token;
            } else {
                throw new BusinessException(ResultCode.USER_PASSWORD_ERROR);
            }
        } else {
            throw new BusinessException(ResultCode.USER_TELEPHONE_ERROR);
        }
    }

    @Override
    public UserInfoVO getUserInfoByToken(String token) {
        String telephone = jwtUtil.getTelephone(token);
        UserBO userBO = this.getUserByPhone(telephone);
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(userBO, userInfoVO);
        return userInfoVO;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void modifyPassword(String telephone, String currentPassword, String newPassword) {
        UserBO userBO = this.getUserByPhone(telephone);
        String currentOriginalPassword = AESUtil.decrypt(currentPassword);
        String currentEncryptionPassword = encryptUtil.encryptSha1(currentOriginalPassword);
        if (!ObjectUtils.isEmpty(userBO)) {
            if (userBO.getPassword().equals(currentEncryptionPassword)) {
                String newOriginalPassword = AESUtil.decrypt(newPassword);
                String newEncryptionPassword = encryptUtil.encryptSha1(newOriginalPassword);
                LambdaUpdateWrapper<User> updateWrapper = Wrappers.lambdaUpdate();
                Date date = new Date();
                updateWrapper.set(User::getPassword, newEncryptionPassword)
                        .set(User::getUpdateTime, date)
                        .eq(User::getTelephone, telephone);
                userMapper.update(null, updateWrapper);
                redisTemplate.delete(ConstantUtil.USER_TELEPHONE.concat(telephone));
            } else {
                throw new BusinessException(ResultCode.USER_PASSWORD_ERROR);
            }
        }
    }

    @Override
    public void logout(String token) {
        String telephone = jwtUtil.getTelephone(token);
        //退出登录之后，签发的token失效
        redisTemplate.delete(ConstantUtil.TOKEN_VERSION.concat(telephone));
    }

    @Override
    public UserBO getUserByPhone(String telephone) {
        UserBO userBO = new UserBO();
        if (redisTemplate.hasKey(ConstantUtil.USER_TELEPHONE.concat(telephone))) {
            Map userEntries = redisTemplate.boundHashOps(ConstantUtil.USER_TELEPHONE.concat(telephone)).entries();
            userBO.setId(String.valueOf(userEntries.get("id")));
            userBO.setBirthday((Date) userEntries.get("birthday"));
            userBO.setEmail(String.valueOf(userEntries.get("email")));
            userBO.setNickName(String.valueOf(userEntries.get("nickName")));
            userBO.setPassword(String.valueOf(userEntries.get("password")));
            userBO.setPhoto(String.valueOf(userEntries.get("photo")));
            userBO.setTelephone(String.valueOf(userEntries.get("telephone")));
        } else {
            LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.eq(User::getTelephone, telephone).eq(User::getStatus, 1);
            User user = userMapper.selectOne(queryWrapper);

            Map<String, Object> userMap = new HashMap<>(8);
            userMap.put("id", user.getId());
            userMap.put("telephone", user.getTelephone());
            userMap.put("nickName", user.getNickName());
            userMap.put("photo", user.getPhoto());
            userMap.put("password", user.getPassword());
            userMap.put("email", user.getEmail());
            userMap.put("birthday", user.getBirthday());
            redisTemplate.opsForHash().putAll(ConstantUtil.USER_TELEPHONE.concat(telephone), userMap);
            //用户信息缓存设置5-6天过期策略-缓存雪崩；用户不活跃，缓存失效，避免用户信息永久存储与redis
            redisTemplate.expire(ConstantUtil.USER_TELEPHONE.concat(telephone), new Random().nextInt(24 * 60 * 60) + useRedisExpiredTime, TimeUnit.SECONDS);

            BeanUtils.copyProperties(user, userBO);
        }
        return userBO;
    }

}
