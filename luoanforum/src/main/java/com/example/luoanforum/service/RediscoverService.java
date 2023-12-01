package com.example.luoanforum.service;

//import javax.servlet.http.HttpSession;

import jakarta.servlet.http.HttpSession;

/**
 * @author 落扶苏
 * @version 1.1
 */
public interface RediscoverService {
    //获取邮箱找回密码
    String emailRetrievalCode(String email, HttpSession session);

    //邮箱找回修改密码
    String changePasswordFoundByEmail(String email, String emailCode, String newPwd, HttpSession session);
}
