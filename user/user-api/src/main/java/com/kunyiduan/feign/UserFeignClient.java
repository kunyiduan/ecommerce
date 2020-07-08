package com.kunyiduan.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@FeignClient(name = "USER", path = "/user")
@RestController
public interface UserFeignClient {

    /**
     * 通过token获取用户信息
     * @param token
     * @return
     */
    @PostMapping("/getUserByToken")
    ResponseDto getUserByToken(@NotBlank(message = "token不能为空") @RequestParam("token") String token);

}
