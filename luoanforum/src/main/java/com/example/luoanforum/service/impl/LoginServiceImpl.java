package com.example.luoanforum.service.impl;


import com.example.luoanforum.internalservices.impl.SendMail;
import com.example.luoanforum.internalservices.impl.VerificationCodeFactory;
import com.example.luoanforum.mapper.LoginMapper;
import com.example.luoanforum.mapper.UserMapper;
import com.example.luoanforum.pojo.AccountPwdCookie;
import com.example.luoanforum.pojo.UserInformation;
import com.example.luoanforum.service.LoginService;
import com.example.luoanforum.util.VerificationCodeUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import javax.servlet.http.HttpServletRequest;

/**
 * @author 落扶苏
 * @version 1.1
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

//    @Autowired(required = false)
    @Autowired
    private SendMail sendMail;
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VerificationCodeFactory codeFactory;

////    @PostConstruct
//    private void init() {
//        System.out.println("/...............");
//        List<PersonalInformation> personalInformations = loginMapper.selectPersonAll();
//        for (PersonalInformation p: personalInformations)
//            System.out.println(p);
//
//    }

//    @Value("${username}")

//    public String show() {
//        System.out.println("/...............");
//        StringBuilder stringBuilder = new StringBuilder();
//        List<PersonalInformation> personalInformations = loginMapper.selectPersonAll();
//        for (PersonalInformation p: personalInformations)
//            stringBuilder.append(p + "\n");
//            //System.out.println(p);
//        return stringBuilder.toString();
//    }

    @Override
    public AccountPwdCookie accountVerificationLogin(String account, String password) {
        String uid = loginMapper.accountQueryUserExistence(account, password);
        if (uid != null) {
            return  new AccountPwdCookie(uid, account, password);
        }
        return null;
    }

    @Override
    public AccountPwdCookie emailVerificationLogin(String email, String password) {
        String uid = loginMapper.getEmailUid(email);
        if (uid != null) {
            String account = loginMapper.otherQueryUserExistence(uid, password);
            if (account != null)
                return new AccountPwdCookie(uid, account, password);
        }
        return null;
    }

    @Override
    public AccountPwdCookie smsVerificationLogin(String sms, String password) {
        String uid = loginMapper.getSmsUid(sms);
        if (uid != null) {
            String account = loginMapper.otherQueryUserExistence(uid, password);
            if (account != null)
                return new AccountPwdCookie(uid, account, password);
        }
        return null;
    }

    @Override
    public UserInformation returnUserEssentialInformation(String uid) {
        return userMapper.returnUserInformation(uid);
    }

    @Override
    public String verifyCookies(HttpServletRequest req) {
        return null;
    }

    @Override
    public AccountPwdCookie findUserByAccount(String account) {
        return loginMapper.passwordQueryUserExistenceByAccount(account);
    }

    @Override
    public String getEmailLoginCode(String email) {
        if (loginMapper.queryWhetherTheEmailIsRegistered(email) == null)
            return null;
        String code = codeFactory.emailVerificationCode();
        sendMail.signInMail(email, VerificationCodeUtil.returnFirstCode(code));
        return code;
    }

    @Override
    public AccountPwdCookie getInformation(String email) {
        String uid = loginMapper.getEmailUid(email);
        if (uid != null) {
            return loginMapper.passwordQueryUserExistenceById(uid);
        }
        return null;
    }
}
