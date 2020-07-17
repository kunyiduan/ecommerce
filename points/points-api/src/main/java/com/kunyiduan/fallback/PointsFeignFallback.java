package com.kunyiduan.fallback;

import com.kunyiduan.bean.PointsVO;
import com.kunyiduan.feign.PointsFeignClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by duankunyi on 2020/7/17.
 */
@Component
@Slf4j
public class PointsFeignFallback implements FallbackFactory<PointsFeignClient> {

    @Override
    public PointsFeignClient create(Throwable throwable) {
        return new PointsFeignClient() {
            @Override
            public Boolean create(@RequestBody @Validated PointsVO pointsVO) {
                log.info("points feign create fallback");
                return null;
            }
        };
    }

}
