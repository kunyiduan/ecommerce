package com.kunyiduan.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EncryptUtils {

    @Value("${encrption.slat}")
    private String slat;

    public String encryptBase64(String str){
        return Base64.encodeBase64String(str.concat(slat).getBytes());
    }

    public String decryptBase64(String str){
        return String.valueOf(Base64.decodeBase64(str.concat(slat).getBytes()));
    }

    public String encryptMd5(String str){
        return DigestUtils.md5Hex(str.concat(slat));
    }

    public String encryptSha1(String str){
        return DigestUtils.sha1Hex(str.concat(slat));
    }

    public String encryptSha256(String str){
        return DigestUtils.sha256Hex(str.concat(slat));
    }

}
