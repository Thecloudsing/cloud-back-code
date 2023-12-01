package com.example.luoanforum.service;

import com.example.luoanforum.pojo.AccountPwdCookie;
import com.example.luoanforum.pojo.UserInformation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.transaction.annotation.Transactional;

//import javax.servlet.http.HttpServletRequest;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

/**
 * @author 落扶苏
 * @version 1.1
 */
public interface LoginService {
//    String show();

    //验证登录是否合法 error => -1
    @Transactional(isolation = READ_COMMITTED)
    AccountPwdCookie accountVerificationLogin(String account, String password);

    AccountPwdCookie emailVerificationLogin(String email, String password);
    AccountPwdCookie smsVerificationLogin(String sms, String password);
    //登录成功返回用户基本信息
    UserInformation returnUserEssentialInformation(String uid);

    //验证cookie携带的账户密码
    String verifyCookies(HttpServletRequest req);

    AccountPwdCookie findUserByAccount(String account);

    //获取邮箱登录验证码
    String getEmailLoginCode(String email);

    //通过邮箱获取账号密码
    AccountPwdCookie getInformation(String email);
}
