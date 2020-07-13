package com.kunyiduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kunyiduan.bean.user.LoginPhoneVO;
import com.kunyiduan.bean.user.RegisterVO;
import com.kunyiduan.bean.user.UserInfoVO;
import com.kunyiduan.entity.User;
import com.kunyiduan.exception.ExceptionCode;
import com.kunyiduan.exception.GlobalException;
import com.kunyiduan.jwt.JwtUtils;
import com.kunyiduan.mapper.UserMapper;
import com.kunyiduan.service.UserService;
import com.kunyiduan.utils.AesUtils;
import com.kunyiduan.utils.ConstantUtils;
import com.kunyiduan.utils.EncryptUtils;
import com.kunyiduan.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;

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
    private EncryptUtils encryptUtils;

    @Autowired
    private AesUtils aesUtils;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean register(RegisterVO registerVO) {
        User user = new User();
        BeanUtils.copyProperties(registerVO,user);
        String originalPassword = aesUtils.decrypt(registerVO.getPassword());
        user.setPassword(encryptUtils.encryptSha1(originalPassword));
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        int count = baseMapper.insert(user);
        return count == 1 ? true : false;
    }

    @Override
    public String login(LoginPhoneVO loginPhoneVO) {
        User user = this.getUserByPhone(loginPhoneVO.getTelephone());
        if(!ObjectUtils.isEmpty(user)){
            String originalPassword = aesUtils.decrypt(loginPhoneVO.getPassword());
            String encryptionPassword = encryptUtils.encryptSha1(originalPassword);
            if(user.getPassword().equals(encryptionPassword)){
                String token = jwtUtils.sign(String.valueOf(user.getId()),user.getTelephone());
                return token;
            }else {
                throw new GlobalException(ExceptionCode.USER_PASSWORD_ERROR);
            }
        }else {
            throw new GlobalException(ExceptionCode.USER_TELEPHONE_ERROR);
        }
    }

    @Override
    public UserInfoVO getUserInfoByToken(String token) {
        String telephone = jwtUtils.getTelephone(token);
        User user = this.getUserByPhone(telephone);
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(user,userInfoVO);
        return userInfoVO;
    }

    @Override
    public void modifyPassword(String telephone, String currentPassword, String newPassword) {
        User user = this.getUserByPhone(telephone);
        String currentOriginalPassword = aesUtils.decrypt(currentPassword);
        String currentEncryptionPassword = encryptUtils.encryptSha1(currentOriginalPassword);
        if(!ObjectUtils.isEmpty(user)){
            if(user.getPassword().equals(currentEncryptionPassword)){
                String newOriginalPassword = aesUtils.decrypt(newPassword);
                String newEncryptionPassword = encryptUtils.encryptSha1(newOriginalPassword);
                LambdaUpdateWrapper<User> updateWrapper = Wrappers.lambdaUpdate();
                Date date = new Date();
                updateWrapper.set(User::getPassword,newEncryptionPassword)
                             .set(User::getUpdateTime,date)
                             .eq(User::getTelephone,telephone);
                userMapper.update(null,updateWrapper);
                redisUtils.del(ConstantUtils.USER_TELEPHONE.concat(telephone));
            }else {
                throw new GlobalException(ExceptionCode.USER_PASSWORD_ERROR);
            }
        }
    }

    @Override
    public void logout(String token) {
        String telephone = jwtUtils.getTelephone(token);
        redisUtils.del(ConstantUtils.USER_TELEPHONE.concat(telephone));
    }

    @Override
    public User getUserByPhone(String telephone) {
        User user;
        if(redisUtils.hasKey(ConstantUtils.USER_TELEPHONE.concat(telephone))){
            user = (User) redisUtils.get(ConstantUtils.USER_TELEPHONE.concat(telephone));
        }else {
            LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.eq(User::getTelephone,telephone);
            user = userMapper.selectOne(queryWrapper);
            redisUtils.set(ConstantUtils.USER_TELEPHONE.concat(telephone),user);
        }
        return user;
    }

}
