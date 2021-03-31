package com.kunyiduan.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kunyiduan.enums.ResultCode;
import com.kunyiduan.exception.BusinessException;
import com.kunyiduan.utils.ConstantUtil;
import com.kunyiduan.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class JwtUtil {

    @Value(value = "${token.expired}")
    private long expired;

    @Value(value = "${token.secret}")
    private String secret;

    private final static String tokenPrefix = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.";

    //解决redisUtil无法注入导致的空指针异常
    private static JwtUtil jwtUtil;

    @Autowired
    private RedisUtil redisUtil;

    @PostConstruct
    public void init() {
        if (Objects.isNull(jwtUtil)) {
            jwtUtil = new JwtUtil();
        }
        jwtUtil.redisUtil = this.redisUtil;
    }

    /**
     * 生成token
     *
     * @param id
     * @param telephone
     * @return
     */
    public String sign(String id, String telephone) {
        long version = System.currentTimeMillis();
        jwtUtil.redisUtil.set(ConstantUtil.TOKEN_VERSION.concat(telephone), String.valueOf(version), expired);
        Date date = new Date(version + expired);
        Map<String, Object> header = new HashMap<>(2);
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        //header + payload + signature
        String token = JWT.create().withHeader(header)
                .withClaim("id", id).withClaim("telephone", telephone).withClaim("version", String.valueOf(version))
                .withExpiresAt(date).sign(Algorithm.HMAC256(secret));
        String[] split = token.split("\\.");
        String result = split[1] + "." + split[2];
        return result;
    }

    /**
     * 验证token是否有效
     *
     * @param token
     * @return
     */
    public Boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            verifier.verify(tokenPrefix + token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 通过token获取id
     *
     * @param token
     * @return
     */
    public String getId(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            DecodedJWT jwt = verifier.verify(tokenPrefix + token);
            Map<String, Claim> claimMap = jwt.getClaims();
            String id = claimMap.get("id").asString();
            return id;
        } catch (TokenExpiredException e) {
            throw new BusinessException(ResultCode.TOKEN_EXPIRED);
        }
    }

    /**
     * 通过token获取telephone
     *
     * @param token
     * @return
     */
    public String getTelephone(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            DecodedJWT jwt = verifier.verify(tokenPrefix + token);
            Map<String, Claim> claimMap = jwt.getClaims();
            String telephone = claimMap.get("telephone").asString();
            return telephone;
        } catch (TokenExpiredException e) {
            throw new BusinessException(ResultCode.TOKEN_EXPIRED);
        }
    }

    public long getExpired() {
        return expired;
    }

    /**
     * 通过token获取version
     *
     * @param token
     * @return
     */
    public String getVersion(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            DecodedJWT jwt = verifier.verify(tokenPrefix + token);
            Map<String, Claim> claimMap = jwt.getClaims();
            String version = claimMap.get("version").asString();
            return version;
        } catch (TokenExpiredException e) {
            throw new BusinessException(ResultCode.TOKEN_EXPIRED);
        }
    }

    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZWxlcGhvbmUiOiIxNTkyNTEwODU3NSIsImlkIjoiMTI4MTg2NzY5ODUxNTA3OTE3MCIsImV4cCI6MTU5NjAzNTgxMSwidmVyc2lvbiI6IjE1OTQ3Mzk4MTE5ODkifQ.O1VKLajtk9w3jrECYRxBaHdcKWgT7CpJOyN1qr82A4E";
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("Uk8eVNmrOmJjt8IIRz5Y4xOlYspP4Lp1")).build();
        DecodedJWT jwt = verifier.verify(token);
        Map<String, Claim> claimMap = jwt.getClaims();
        String version = claimMap.get("version").asString();
        System.out.println(version);
    }

}

