package com.kunyiduan.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RestController;

@FeignClient(name = "POINTS")
@RestController
public interface ProductFeignClient {

}
