package com.kunyiduan.fallback;

import com.kunyiduan.bean.UserInfoVO;
import com.kunyiduan.feign.UserFeignClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * Created by duankunyi on 2020/7/18.
 */
@Component
@Slf4j
public class UserFeignFallback implements FallbackFactory<UserFeignClient> {

    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient() {

            @Override
            public UserInfoVO getUserInfoByToken(@RequestHeader("token") String token) {
                log.warn("user service hystrix circuit breaker fallback getUserInfoByToken method! ");
                return null;
            }

        };
    }
}
