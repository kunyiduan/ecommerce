package com.kunyiduan.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/business")
@Api(description = "用户", tags = {"business"})
public class BusinessController {

    @ApiOperation("注册")
    @PostMapping("/register")
    public Boolean register() {
        return false;
    }

}
