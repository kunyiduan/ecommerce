package com.kunyiduan.service;

import com.kunyiduan.bean.user.RegisterVO;
import com.kunyiduan.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author achilles
 * @since 2020-07-08
 */
public interface UserService extends IService<User> {

    Boolean register(RegisterVO registerVO);

}
