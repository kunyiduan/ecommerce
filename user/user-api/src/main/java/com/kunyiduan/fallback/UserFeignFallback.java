package com.kunyiduan.fallback;

import com.kunyiduan.bean.LoginPhoneVO;
import com.kunyiduan.bean.PasswordVO;
import com.kunyiduan.bean.RegisterVO;
import com.kunyiduan.bean.UserInfoVO;
import com.kunyiduan.feign.UserFeignClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
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
            public void register(@RequestBody @Validated RegisterVO registerVO) {
            }

            @Override
            public String login(@RequestBody @Validated LoginPhoneVO loginPhoneVO) {
                return null;
            }

            @Override
            public UserInfoVO getUserInfoByToken(@RequestHeader("token") String token) {
                return null;
            }

            @Override
            public void modifyPassword(@RequestHeader("token") String token, @RequestBody @Validated PasswordVO passwordVO) {

            }

            @Override
            public void logout(@RequestHeader("token") String token) {

            }
        };
    }
}
