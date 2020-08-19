package com.kunyiduan.fallback;

import com.kunyiduan.feign.MessageFeignClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by duankunyi on 2020/7/17.
 */
@Component
@Slf4j
public class MessageFeignFallback implements FallbackFactory<MessageFeignClient> {

    @Override
    public MessageFeignClient create(Throwable throwable) {
        return new MessageFeignClient() {

        };
    }

}
