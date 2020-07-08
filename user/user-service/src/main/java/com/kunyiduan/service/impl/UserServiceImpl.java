package com.kunyiduan.service.impl;

import com.kunyiduan.entity.User;
import com.kunyiduan.mapper.UserMapper;
import com.kunyiduan.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author achilles
 * @since 2020-07-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
