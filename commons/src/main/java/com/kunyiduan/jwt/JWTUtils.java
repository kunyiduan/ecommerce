package com.kunyiduan.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kunyiduan.exception.ExceptionCode;
import com.kunyiduan.exception.GlobalException;
import com.kunyiduan.utils.ConstantUtils;
import com.kunyiduan.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class JWTUtils {

    @Value(value = "${token.expired}")
    private static long expired;

    @Value(value = "${token.secret}")
    private static String secret;

    //解决redisUtils无法注入
    private JWTUtils jwtUtils;

    @Autowired
    private RedisUtils redisUtils;

    @PostConstruct
    public void init(){
        if (Objects.isNull(jwtUtils)) {
            jwtUtils = new JWTUtils();
        }
        jwtUtils.redisUtils = this.redisUtils;
    }

    /**
     * 生成token
     * @param id
     * @param telephone
     * @return
     */
    public String sign(String id,String telephone) {
        long version = System.currentTimeMillis();
        jwtUtils.redisUtils.set(ConstantUtils.TOKEN_VERSION.concat(telephone),String.valueOf(version));
        Date date = new Date(version+expired);
        Map<String,Object> header = new HashMap<>(2);
        header.put("alg","HS256");
        header.put("typ","JWT");
        //header + payload + signature
        String token = JWT.create().withHeader(header)
                .withClaim("id",id).withClaim("telephone",telephone).withClaim("version",String.valueOf(version))
                .withExpiresAt(date).sign(Algorithm.HMAC256(secret));
        return token;
    }

    /**
     * 验证token是否有效
     * @param token
     * @return
     */
    public Boolean verify(String token){
        try{
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            verifier.verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 通过token获取id
     * @param token
     * @return
     */
    public String getId(String token) {
        try{
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claimMap = jwt.getClaims();
            String id = claimMap.get("id").asString();
            return id;
        } catch (TokenExpiredException e){
            throw new GlobalException(ExceptionCode.TOKEN_EXPIRED);
        }
    }

    /**
     * 通过token获取telephone
     * @param token
     * @return
     */
    public String getTelephone(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claimMap = jwt.getClaims();
            String telephone = claimMap.get("telephone").asString();
            return telephone;
        } catch (TokenExpiredException e){
            throw new GlobalException(ExceptionCode.TOKEN_EXPIRED);
        }
    }

    /**
     * 通过token获取version
     * @param token
     * @return
     */
    public String getVersion(String token) {
        try{
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claimMap = jwt.getClaims();
            String version = claimMap.get("version").asString();
            return version;
        } catch (TokenExpiredException e){
            throw new GlobalException(ExceptionCode.TOKEN_EXPIRED);
        }
    }

}

