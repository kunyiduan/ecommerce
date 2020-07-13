package com.kunyiduan.controller;

import com.kunyiduan.bean.ResponseDto;
import com.kunyiduan.bean.user.LoginPhoneVO;
import com.kunyiduan.bean.user.PasswordVO;
import com.kunyiduan.bean.user.RegisterVO;
import com.kunyiduan.bean.user.UserInfoVO;
import com.kunyiduan.jwt.JwtUtils;
import com.kunyiduan.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

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
@Api(description = "用户",tags = {"user"})
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 密码使用AES加密
     * @param registerVO
     * @return
     */
    @ApiOperation("注册")
    @PostMapping("/register")
    public ResponseDto<Boolean> register(@RequestBody @Validated RegisterVO registerVO){
        Boolean flag = userService.register(registerVO);
        return new ResponseDto<Boolean>().success(flag);
    }

    /**
     * 登录成功返回token
     * @param loginPhoneVO
     * @return
     */
    @ApiOperation("登录")
    @RequestMapping("/login")
    public ResponseDto<String> login(@RequestBody @Validated LoginPhoneVO loginPhoneVO){
        String token = userService.login(loginPhoneVO);
        return new ResponseDto<>(token);
    }

    @ApiOperation("通过token获取用户信息")
    @GetMapping("/token")
    public ResponseDto<UserInfoVO> getUserInfoByToken(@RequestHeader("token") String token){
        UserInfoVO userInfo = userService.getUserInfoByToken(token);
        return new ResponseDto<>(userInfo);
    }

    @ApiOperation("修改密码")
    @PostMapping("/password")
    public ResponseDto modifyPassword(@RequestHeader("token") String token, @RequestBody @Validated PasswordVO passwordVO){
        String telephone = jwtUtils.getTelephone(token);
        userService.modifyPassword(telephone,passwordVO.getCurrentPassword(),passwordVO.getNewPassword());
        return new ResponseDto().success();
    }


    @ApiOperation("退出登录")
    @DeleteMapping("/logout")
    public ResponseDto logout(@RequestHeader("token") String token) {
        userService.logout(token);
        return new ResponseDto().success();
    }

}
