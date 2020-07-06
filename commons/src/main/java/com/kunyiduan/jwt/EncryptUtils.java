package com.kunyiduan.jwt;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class EncryptUtils {

    private static final String slat = "331051cf937c11ea93460ab807a98606";

    public static String getBase64(String str){
        String base64 = Base64.encodeBase64String(str.concat(slat).getBytes());
        return base64;
    }

    public static String getMd5(String str){
        String md5 = DigestUtils.md5Hex(str.concat(slat));
        return md5;
    }

    /**
     * 不推荐使用
     * @param str
     * @return
     */
    public static String getSha(String str){
        String sha = DigestUtils.shaHex(str.concat(slat));
        return sha;
    }

    public static String getSha1(String str){
        String sha1 = DigestUtils.sha1Hex(str.concat(slat));
        return sha1;
    }

    public static String getSha256(String str){
        String sha256 = DigestUtils.sha256Hex(str.concat(slat));
        return sha256;
    }

//    public static void main(String[] args) {
//        System.out.println(getMd5("achilles"));
//        System.out.println(getMd5("AchiLLes"));
//        System.out.println(getSha("achilles"));
//        System.out.println(getSha1("achilles"));
//        System.out.println(getBase64("achilles"));
//        System.out.println(getSha256("achilles"));
//    }

}
