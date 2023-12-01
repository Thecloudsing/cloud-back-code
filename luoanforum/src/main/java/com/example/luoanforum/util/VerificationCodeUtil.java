package com.example.luoanforum.util;

import java.util.Date;

/**
 * @author 落扶苏
 * @version 1.1
 */
public class VerificationCodeUtil {
    public static boolean Code(String newCode, String oldCode) {
        if (newCode == null || oldCode == null) return false;
        long time = new Date().getTime();
        String[] code2 = oldCode.split(":");
        if (time - Long.parseLong(code2[1]) < 300000) {
            if (newCode.equals(code2[0])) {
                return true;
            }
        }
        return false;
    }

    public static String returnFirstCode(String code) {
        String[] code1 = code.split(":");
        return code1[0];
    }
}
