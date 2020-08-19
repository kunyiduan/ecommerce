package com.kunyiduan.feign;

import com.kunyiduan.fallback.MessageFeignFallback;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "MESSAGE", fallbackFactory = MessageFeignFallback.class)
@RequestMapping("/message")
@ResponseBody
@Api("短信API")
public interface MessageFeignClient {

}
