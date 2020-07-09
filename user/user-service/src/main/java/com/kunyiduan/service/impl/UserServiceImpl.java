package com.kunyiduan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kunyiduan.bean.user.RegisterVO;
import com.kunyiduan.entity.User;
import com.kunyiduan.mapper.UserMapper;
import com.kunyiduan.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

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

    @Override
    public void register(RegisterVO registerVO) {
        User user = new User();
        BeanUtils.copyProperties(registerVO,user);
        Timestamp date = new Timestamp(System.currentTimeMillis());
        user.setCreateTime(date);
        user.setUpdateTime(date);
        baseMapper.insert(user);
    }
}
