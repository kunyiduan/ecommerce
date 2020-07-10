package com.kunyiduan.controller;

import com.kunyiduan.bean.ResponseDto;
import com.kunyiduan.bean.user.RegisterVO;
import com.kunyiduan.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 密码使用对称加密算法AES
     * @param registerVO
     * @return
     */
    @ApiOperation("注册")
    @PostMapping("/register")
    public ResponseDto register(@RequestBody @Validated RegisterVO registerVO){
        userService.register(registerVO);
        return new ResponseDto().success();
    }

}
