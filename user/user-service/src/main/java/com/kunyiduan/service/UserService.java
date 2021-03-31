package com.kunyiduan.service;

import com.kunyiduan.bean.user.LoginPhoneParam;
import com.kunyiduan.bean.user.RegisterParam;
import com.kunyiduan.bean.user.UserBO;
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

    boolean register(RegisterParam registerParam);

    String login(LoginPhoneParam loginPhoneParam);

    UserInfoVO getUserInfoByToken(String token);

    void modifyPassword(String telephone, String currentPassword, String newPassword);

    void logout(String token);

    UserBO getUserByPhone(String telephone);

}
