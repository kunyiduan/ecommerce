package com.kunyiduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kunyiduan.bean.user.LoginPhoneVO;
import com.kunyiduan.bean.user.RegisterVO;
import com.kunyiduan.entity.User;
import com.kunyiduan.jwt.JwtUtils;
import com.kunyiduan.mapper.UserMapper;
import com.kunyiduan.service.UserService;
import com.kunyiduan.utils.AesUtils;
import com.kunyiduan.utils.EncryptUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.Objects;

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

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean register(RegisterVO registerVO) {
        User user = new User();
        BeanUtils.copyProperties(registerVO,user);
        String originPassword = aesUtils.decrypt(registerVO.getPassword());
        user.setPassword(encryptUtils.encryptSha1(originPassword));
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        int count = baseMapper.insert(user);
        return count == 1 ? true : false;
    }

    @Override
    public String login(LoginPhoneVO loginPhoneVO) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(User::getTelephone,loginPhoneVO.getTelephone());
        User user = userMapper.selectOne(queryWrapper);
        if(!ObjectUtils.isEmpty(user)){
            String originPassword = aesUtils.decrypt(loginPhoneVO.getPassword());
            String encryptionPassword = encryptUtils.encryptSha1(originPassword);
            if(user.getPassword().equals(encryptionPassword)){
                String token = jwtUtils.sign(String.valueOf(user.getId()),user.getTelephone());
                return token;
            }
        }
        return null;
    }

}
