package com.kunyiduan.feign;

import com.kunyiduan.bean.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "USER")
@RestController
@ApiModel("用户对外API")
public interface UserFeignClient {

    @ApiOperation("注册")
    @PostMapping("/register")
    ResponseDto register(@RequestBody @Validated RegisterVO registerVO);

    @ApiOperation("登录")
    @RequestMapping("/login")
    ResponseDto<String> login(@RequestBody @Validated LoginPhoneVO loginPhoneVO);

    @ApiOperation("通过token获取用户信息")
    @GetMapping("/token")
    ResponseDto<UserInfoVO> getUserInfoByToken(@RequestHeader("token") String token);

    @ApiOperation("修改密码")
    @PostMapping("/password")
    ResponseDto modifyPassword(@RequestHeader("token") String token, @RequestBody @Validated PasswordVO passwordVO);

    @ApiOperation("退出登录")
    @DeleteMapping("/logout")
    ResponseDto logout(@RequestHeader("token") String token);

}
