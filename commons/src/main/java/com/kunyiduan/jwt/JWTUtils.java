package com.kunyiduan.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kunyiduan.exception.ExceptionCode;
import com.kunyiduan.exception.LocalException;
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

    private static JWTUtils jwtUtils;

    @Autowired
    private RedisUtils redisUtils;

    @PostConstruct
    public void init(){
        if (Objects.isNull(jwtUtils)) {
            jwtUtils = new JWTUtils();
        }
        jwtUtils.redisUtils = this.redisUtils;
    }

    @Value(value = "${jwt.expired}")
    private static long EXPIRED;

    private static final String SECRET = "d2Cdf0e08E9811eAB79500155d01d427";

    /**
     * 生成token
     * @param id
     * @param telephone
     * @return
     */
    public String sign(String id,String telephone) {
        long version = System.currentTimeMillis();
        jwtUtils.redisUtils.set(ConstantUtils.TOKEN_VERSION.concat(telephone),String.valueOf(version));
        Date date = new Date(version+EXPIRED);
        Map<String,Object> header = new HashMap<>(2);
        header.put("alg","HS256");
        header.put("typ","JWT");
        //header + payload + signature
        String token = JWT.create().withHeader(header)
                .withClaim("id",id).withClaim("telephone",telephone).withClaim("version",String.valueOf(version))
                .withExpiresAt(date).sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /**
     * 验证token是否有效
     * @param token
     * @param id
     * @param telephone
     * @return
     */
    public Boolean verify(String token,String id,String telephone){
        try{
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
                    .withClaim("id",id).withClaim("telephone",telephone).build();
            verifier.verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Boolean verify(String token){
        try{
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
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
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claimMap = jwt.getClaims();
            String id = claimMap.get("id").asString();
            return id;
        } catch (TokenExpiredException e){
            throw new LocalException(ExceptionCode.TOKEN_EXPIRED);
        }
    }

    /**
     * 通过token获取telephone
     * @param token
     * @return
     */
    public String getTelephone(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claimMap = jwt.getClaims();
            String telephone = claimMap.get("telephone").asString();
            return telephone;
        } catch (TokenExpiredException e){
            throw new LocalException(ExceptionCode.TOKEN_EXPIRED);
        }
    }

    /**
     * 通过token获取version
     * @param token
     * @return
     */
    public String getVersion(String token) {
        try{
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claimMap = jwt.getClaims();
            String version = claimMap.get("version").asString();
            return version;
        } catch (TokenExpiredException e){
            throw new LocalException(ExceptionCode.TOKEN_EXPIRED);
        }
    }

//    public static void main(String [] args){
//        String id = "1260049336568340481";
//        String telephone = "13924819200";
//
//        String token = sign(id,telephone);
//        System.out.println(token);
//
//        String token1 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZWxlcGhvbmUiOiIxNTkyNTEwODU3NSIsImlkIjoiMTI1OTc1MzE4ODMzODQ2NjgxOCIsImV4cCI6MTU5MjczMjM3NSwidmVyc2lvbiI6IjE1OTE0MzYzNzUzMjkifQ.td_Gd5vAOXE8_JCAhi1FJEy3CGlprKa_V64j-GYx4Oc";
//        JWTUtils jwtUtils = new JWTUtils();
//        String version = jwtUtils.getVersion(token1);
//        System.out.println(version);
//        Boolean flag = verify(token1, id, telephone);
//        System.out.println(flag);
//
//        String telephone1 = getTelephone(token1);
//        System.out.println(telephone1);
//
//        String id1 = getId(token1);
//        System.out.println(id1);
//
//        Boolean verify = verify(token1);
//        System.out.println(verify);
//
//    }

}

