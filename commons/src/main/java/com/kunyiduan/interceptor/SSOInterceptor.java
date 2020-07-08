package com.kunyiduan.interceptor;

import com.kunyiduan.exception.ExceptionCode;
import com.kunyiduan.exception.LocalException;
import com.kunyiduan.jwt.JWTUtils;
import com.kunyiduan.utils.ConstantUtils;
import com.kunyiduan.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
@Slf4j
public class SSOInterceptor implements HandlerInterceptor {

    private static SSOInterceptor ssoInterceptor;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private JWTUtils jwtUtils;

    @PostConstruct
    public void init(){
        if (Objects.isNull(ssoInterceptor)) {
            ssoInterceptor = new SSOInterceptor();
        }
        ssoInterceptor.redisUtils = this.redisUtils;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String token = httpServletRequest.getHeader("token");
        if(!StringUtils.isEmpty(token)){
            String version = jwtUtils.getVersion(token);
            String telephone = jwtUtils.getTelephone(token);
            String savaVersion = ssoInterceptor.redisUtils.get(ConstantUtils.TOKEN_VERSION.concat(telephone)).toString();
            if(!version.equals(savaVersion)){
                throw new LocalException(ExceptionCode.USER_LOGIN_ERROR);
            }
            return true;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
