package com.kunyiduan.feign;

import com.kunyiduan.bean.UserInfoVO;
import com.kunyiduan.fallback.UserFeignFallback;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "USER", fallbackFactory = UserFeignFallback.class)
@ResponseBody
@Api("用户对外API")
public interface UserFeignClient {

    //使用信号量隔离-用户信息存储于redis-请求线程与调用线程是同一条线程，无线程切换，开销低，可以传递http header信息
    //若使用线程池隔离，有线程切换，无法传递http header信息
    @ApiOperation("通过token获取用户信息")
    @GetMapping("/user/token")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY, value = "SEMAPHORE"),// 信号量 隔离
            @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_SEMAPHORE_MAX_CONCURRENT_REQUESTS, value = "100")//信号量最大并发度
    })
    UserInfoVO getUserInfoByToken(@RequestHeader("token") String token);

}
