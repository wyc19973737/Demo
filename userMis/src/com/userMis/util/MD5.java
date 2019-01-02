package com.userMis.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import sun.misc.BASE64Encoder;

public class MD5 {

    public static void main(String[] args) throws Exception {
        // 加密后的字符串
        // LdB4/O8maPRt1HqIX8tUbw==
        // oyohSfs57qaPJCuFsjR80w==
        System.out.println(encode("aaaaa111"));

        System.out.println(UUID.randomUUID().toString());
    }

    public static String encode(String str) {
        if (FormatEmpty.isEmpty(str)) {
            return null;
        }
        try {
            // 确定计算方法
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            return base64en.encode(md5.digest(str.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
