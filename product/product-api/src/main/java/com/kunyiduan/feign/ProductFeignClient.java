package com.kunyiduan.feign;

import com.kunyiduan.fallback.ProductFeignFallback;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RestController;

@FeignClient(name = "PRODUCT",fallbackFactory = ProductFeignFallback.class)
@RestController
@Api("商品对外API")
public interface ProductFeignClient {

}
