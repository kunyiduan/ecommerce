package com.kunyiduan.controller;

import com.kunyiduan.bean.user.LoginPhoneParam;
import com.kunyiduan.bean.user.PasswordParam;
import com.kunyiduan.bean.user.RegisterParam;
import com.kunyiduan.bean.user.UserInfoVO;
import com.kunyiduan.jwt.JwtUtil;
import com.kunyiduan.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author achilles
 * @since 2020-07-08
 */
@RestController
@RequestMapping("/user")
@Api(description = "用户", tags = {"user"})
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 密码使用AES加密
     *
     * @param registerParam
     * @return
     */
    @ApiOperation("注册")
    @PostMapping("/register")
    public Boolean register(@RequestBody @Validated RegisterParam registerParam) {
        Boolean flag = userService.register(registerParam);
        return flag;
    }

    /**
     * 登录成功返回token
     *
     * @param loginPhoneParam
     * @return
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    public String login(@RequestBody @Validated LoginPhoneParam loginPhoneParam) {
        String token = userService.login(loginPhoneParam);
        return token;
    }

    @ApiOperation("通过token获取用户信息")
    @GetMapping("/token")
    public UserInfoVO getUserInfoByToken(@RequestHeader("token") String token) {
        UserInfoVO userInfo = userService.getUserInfoByToken(token);
        return userInfo;
    }

    @ApiOperation("修改密码")
    @PostMapping("/password")
    public void modifyPassword(@RequestHeader("token") String token, @RequestBody @Validated PasswordParam passwordParam) {
        String telephone = jwtUtil.getTelephone(token);
        userService.modifyPassword(telephone, passwordParam.getCurrentPassword(), passwordParam.getNewPassword());
    }


    @ApiOperation("退出登录")
    @DeleteMapping("/logout")
    public void logout(@RequestHeader("token") String token) {
        userService.logout(token);
    }

}
