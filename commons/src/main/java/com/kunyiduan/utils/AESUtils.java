package com.kunyiduan.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.stereotype.Component;

@Component
public class AESUtils {

    @Value("${encryption.aes.secret}")
    private static String secret;

    @Value("${encryption.aes.slat}")
    private static String slat;

    /**
     * 加密
     * @param str
     * @return
     */
    public String encrypt(String str){
        return Encryptors.text(secret, slat).encrypt(str);
    }

    /**
     * 解密
     * @param str
     * @return
     */
    private String decrypt(String str) {
        return Encryptors.text(secret, slat).decrypt(str);
    }

}
