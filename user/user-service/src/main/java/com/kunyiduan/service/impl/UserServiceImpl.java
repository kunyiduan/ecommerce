package com.kunyiduan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kunyiduan.bean.user.RegisterVO;
import com.kunyiduan.entity.User;
import com.kunyiduan.mapper.UserMapper;
import com.kunyiduan.service.UserService;
import com.kunyiduan.utils.EncryptUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author achilles
 * @since 2020-07-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private EncryptUtils encryptUtils;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean register(RegisterVO registerVO) {
        User user = new User();
        BeanUtils.copyProperties(registerVO,user);
        user.setPassword(encryptUtils.encryptSha1(registerVO.getPassword()));
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        int count = baseMapper.insert(user);
        return count == 1 ? true : false;
    }

}
