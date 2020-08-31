package com.kunyiduan.fallback;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author achilles
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020/08/31 16:12:00
 */
@Component
public class ProductFeignFallback implements FallbackFactory<ProductFeignFallback> {
    @Override
    public ProductFeignFallback create(Throwable throwable) {
        return null;
    }
}
