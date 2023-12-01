package com.example.luoanforum.service.impl;

import com.example.luoanforum.internalservices.impl.AccountFactoryImpl;
import com.example.luoanforum.internalservices.impl.SendMail;
import com.example.luoanforum.internalservices.impl.VerificationCodeFactory;
import com.example.luoanforum.mapper.RegisterMapper;
import com.example.luoanforum.service.RegisterService;
import com.example.luoanforum.util.VerificationCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author 落扶苏
 * @version 1.1
 */
@Service("registerService")
public class RegisterServiceImpl implements RegisterService {

    @Value("${host}")
    private String host;
    @Autowired
    SendMail sendMail;
    @Autowired
    AccountFactoryImpl accountFactory;
    @Autowired
    VerificationCodeFactory codeFactory;
    @Autowired
    RegisterMapper registerMapper;

    @Override
    public String registerAccount(String account, String pwd, String username) {
        if (registerMapper.queryWhetherTheAccountIsRegistered(account) == null) {
            registerMapper.registerAccount(account, pwd);
            String uid = registerMapper.getAccountUid(account, pwd);
            registerMapper.registerUser(uid,username," "," ",host + "systemdefault/v2-6afa72220d29f045c15217aa6b275808_hd.png");
            return uid;
        }
        return null;
    }

    @Override
    public String registerEmail(String email, String pwd, String username) {
        String temp;
        registerMapper.registerAccount(temp = accountFactory.accountFactory(), pwd);
        String uid = registerMapper.getAccountUid(temp, pwd);
        if (uid != null) {
            registerMapper.registerUser(uid, username, email, " ",host + "systemdefault/v2-6afa72220d29f045c15217aa6b275808_hd.png");
            return temp;
        }
        return null;
    }

    @Override
    public String registerSms(String sms, String pwd) {
        return null;
    }

    @Override
    public String getMailboxVerificationCode(String email) {
        if (registerMapper.queryWhetherTheEmailIsRegistered(email) != null)
            return null;
        String code = codeFactory.emailVerificationCode();
        sendMail.registerMail(email, VerificationCodeUtil.returnFirstCode(code));
        return code;
    }

    @Override
    public String duplicateQueryAccount(String account) {
        return registerMapper.queryWhetherTheAccountIsRegistered(account);
    }

    @Override
    public String duplicateQueryEmail(String email) {
        return registerMapper.queryWhetherTheEmailIsRegistered(email);
    }
}
