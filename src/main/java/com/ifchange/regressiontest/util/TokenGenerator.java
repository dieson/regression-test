package com.ifchange.regressiontest.util;


import com.ifchange.regressiontest.dto.ExceptionDto;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * @ClassName: TokenGenerator
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 14:44 2019/1/11
 */
public class TokenGenerator {
    private static final char[] hexCode = "0123456789abcdef".toCharArray();

    public static String generateValue() {
        return generateValue(UUID.randomUUID().toString());
    }

    public static String toHexString(byte[] data) {
        if (data == null) {
            return null;
        }
        StringBuilder r = new StringBuilder(data.length * 2);
        for (byte b : data) {
            r.append(hexCode[(b >> 4) & 0xF]);
            r.append(hexCode[(b & 0xF)]);
        }
        return r.toString();
    }

    public static String generateValue(String param) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(param.getBytes());
            byte[] messageDigest = algorithm.digest();
            return toHexString(messageDigest);
        } catch (Exception e) {
            throw new ExceptionDto("生成Token失败", e);
        }
    }
}
