package com.kunyiduan.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author achilles
 * @since 2020-07-08
 */
@Controller
@RequestMapping("/user")
@Api(description = "用户",tags = {"user"})
public class UserController {

}
