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

    //必须声明为statis，类级别对象，防止空指针异常
    private static SsoInterceptor ssoInterceptor;

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
        ssoInterceptor.jwtUtil = this.jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String token = httpServletRequest.getHeader("token");
        if (!StringUtils.isEmpty(token)) {
            String telephone = ssoInterceptor.jwtUtil.getTelephone(token);
            if(ssoInterceptor.redisUtil.hasKey(ConstantUtil.TOKEN_VERSION.concat(telephone))){
                String currentVersion = ssoInterceptor.jwtUtil.getVersion(token);
                String savaVersion = ssoInterceptor.redisUtil.get(ConstantUtil.TOKEN_VERSION.concat(telephone)).toString();
                if (!currentVersion.equals(savaVersion)) {
                    throw new BusinessException(ResultCode.USER_LOGIN_ERROR);
                }
            }else {
                throw new BusinessException(ResultCode.USER_LOGIN_ERROR);
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
