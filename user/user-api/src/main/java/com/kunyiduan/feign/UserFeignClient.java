package com.kunyiduan.feign;

import com.kunyiduan.bean.RegisterVO;
import com.kunyiduan.bean.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@FeignClient(name = "USER", path = "/user")
@RestController
public interface UserFeignClient {

//    @PostMapping("/getUserByToken")
//    ResponseDto getUserByToken(@NotBlank(message = "token不能为空") @RequestParam("token") String token);

    @PostMapping("/register")
    ResponseDto register(@RequestBody @Validated RegisterVO registerVO);

}
