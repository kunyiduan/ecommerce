package com.kunyiduan.service;

import com.kunyiduan.bean.user.LoginPhoneVO;
import com.kunyiduan.bean.user.RegisterVO;
import com.kunyiduan.bean.user.UserInfoVO;
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

    String login(LoginPhoneVO loginPhoneVO);

    UserInfoVO getUserInfoByToken(String token);

    void modifyPassword(String telephone,String currentPassword,String newPassword);

    void logout(String token);


    User getUserByPhone(String telephone);

}
