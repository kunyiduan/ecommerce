package com.kunyiduan.interception;

import com.kunyiduan.exception.ExceptionCode;
import com.kunyiduan.exception.GlobalException;
import com.kunyiduan.jwt.JwtUtils;
import com.kunyiduan.utils.ConstantUtils;
import com.kunyiduan.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
public class SsoInterceptor implements HandlerInterceptor {

    private SsoInterceptor ssoInterceptor;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private JwtUtils jwtUtils;

    @PostConstruct
    public void init() {
        if (Objects.isNull(ssoInterceptor)) {
            ssoInterceptor = new SsoInterceptor();
        }
        ssoInterceptor.redisUtils = this.redisUtils;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String token = httpServletRequest.getHeader("token");
        if (!StringUtils.isEmpty(token)) {
            String version = jwtUtils.getVersion(token);
            String telephone = jwtUtils.getTelephone(token);
            String savaVersion = ssoInterceptor.redisUtils.get(ConstantUtils.TOKEN_VERSION.concat(telephone)).toString();
            if (!version.equals(savaVersion)) {
                throw new GlobalException(ExceptionCode.USER_LOGIN_ERROR);
            }
            return true;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
