package com.example.luoanforum.service;

/**
 * @author 落扶苏
 * @version 1.1
 */
public interface RegisterService {
    //账号注册
    String registerAccount(String account, String pwd, String username);

    //邮箱注册
    String registerEmail(String email, String pwd, String username);

    //手机号注册
    String registerSms(String sms, String pwd);

    //获取邮箱验证码
    String getMailboxVerificationCode(String email);

    //查询账号存在
    String duplicateQueryAccount(String account);
    String duplicateQueryEmail(String email);
}
