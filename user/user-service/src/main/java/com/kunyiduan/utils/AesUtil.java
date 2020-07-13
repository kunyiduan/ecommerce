package com.kunyiduan.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AesUtil {

    @Value("${encryption.aes.secret}")
    private String secret;

    @Value("${encryption.aes.slat}")
    private String slat;

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
    public String decrypt(String str) {
        return Encryptors.text(secret, slat).decrypt(str);
    }

    public static void main(String[] args) {
        String reuslt = Encryptors.text("NyBGPAI)E*BDHw+BLSDA9PuekPiGFSU&", "b6db1d1ec27811ea").encrypt("achilles");
        System.out.println(reuslt);
    }

}
