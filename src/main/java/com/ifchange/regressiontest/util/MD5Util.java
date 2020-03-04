package com.ifchange.regressiontest.util;

import com.ifchange.regressiontest.constant.Const;

import java.security.MessageDigest;

/**
 * @ClassName: MD5Util
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 14:45 2019/4/2
 */
public class MD5Util {
    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f" };

    private static String byteArrayToHexString(byte b[]) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) resultSb.append(byteToHexString(aB));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * 返回大写MD5
     *
     * @param origin
     * @param charSetName
     * @return
     */
    private static String MD5Encode(String origin, String charSetName) {
        String resultString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charSetName == null || "".equals(charSetName))
                resultString = byteArrayToHexString(md.digest(origin.getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(origin.getBytes(charSetName)));
        } catch (Exception ignored) {
        }
        return resultString != null ? resultString.toUpperCase() : null;
    }

    public static String MD5EncodeUtf8(String origin) {
        origin = origin + Const.SERCET;
        return MD5Encode(origin, Const.CODING);
    }

    public static void main(String[] args) {
        String str = MD5Util.MD5EncodeUtf8("test123");
        System.out.println(str);
    }
}
