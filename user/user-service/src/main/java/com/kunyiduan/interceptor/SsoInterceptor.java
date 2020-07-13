package com.kunyiduan.interceptor;

import com.kunyiduan.enums.ResultCode;
import com.kunyiduan.exception.BusinessException;
import com.kunyiduan.jwt.JwtUtil;
import com.kunyiduan.utils.ConstantUtil;
import com.kunyiduan.utils.RedisUtil;
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
public class SsoInterceptor implements HandlerInterceptor {

    private SsoInterceptor ssoInterceptor;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JwtUtil jwtUtil;

    @PostConstruct
    public void init() {
        if (Objects.isNull(ssoInterceptor)) {
            ssoInterceptor = new SsoInterceptor();
        }
        ssoInterceptor.redisUtil = this.redisUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String token = httpServletRequest.getHeader("token");
        if (!StringUtils.isEmpty(token)) {
            String currentVersion = jwtUtil.getVersion(token);
            String telephone = jwtUtil.getTelephone(token);
            String savaVersion = ssoInterceptor.redisUtil.get(ConstantUtil.TOKEN_VERSION.concat(telephone)).toString();
            log.info("currentVersion-------------------------------------" + currentVersion);
            log.info("savaVersion-------------------------------------" + savaVersion);
            if (!currentVersion.equals(savaVersion)) {
                throw new BusinessException(ResultCode.USER_LOGIN_ERROR);
            }
            return true;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
