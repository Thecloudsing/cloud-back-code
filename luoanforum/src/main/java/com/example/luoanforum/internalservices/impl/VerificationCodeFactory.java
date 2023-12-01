package com.example.luoanforum.internalservices.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 落扶苏
 * @version 1.1
 */

@Service
public class VerificationCodeFactory {
    @Autowired
    private RandomFactory random;

    private final StringBuffer str = new StringBuffer();

    public String emailVerificationCode() {
        long time = new Date().getTime();
        str.delete(0,str.length());
        for (int i = 0; i < 6; i++) {
            str.append(random.getRandom().nextInt(10));
        }
        str.append(":");
        str.append(time);
        return str.toString();
    }

    public String specifyVerificationCode(Integer j) {
        str.delete(0,str.length());
        for (int i = 0; i < j; i++) {
            str.append(random.getRandom().nextInt(10));
        }
        return str.toString();
    }
}
