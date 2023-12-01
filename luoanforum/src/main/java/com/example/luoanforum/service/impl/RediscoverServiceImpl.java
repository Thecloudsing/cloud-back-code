package com.example.luoanforum.service.impl;

import com.example.luoanforum.internalservices.impl.SendMail;
import com.example.luoanforum.internalservices.impl.VerificationCodeFactory;
import com.example.luoanforum.mapper.RediscoverMapper;
import com.example.luoanforum.service.RediscoverService;
import com.example.luoanforum.util.VerificationCodeUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import javax.servlet.http.HttpSession;

/**
 * @author 落扶苏
 * @version 1.1
 */
@Service("rediscoverService")
public class RediscoverServiceImpl implements RediscoverService {

    @Autowired
    private SendMail sendMail;
    @Autowired
    private RediscoverMapper rediscoverMapper;
    @Autowired
    private VerificationCodeFactory codeFactory;

    @Override
    public String emailRetrievalCode(String email, HttpSession session) {
        if (rediscoverMapper.queryWhetherTheEmailIsRegistered(email) == null)
            return "error:";
        String code = codeFactory.emailVerificationCode();
        session.setAttribute("emailCode",code);
        sendMail.rediscoverMail(email, VerificationCodeUtil.returnFirstCode(code));
        return "success:";
    }

    @Override
    public String changePasswordFoundByEmail(String email, String emailCode, String newPwd, HttpSession session) {
        if (VerificationCodeUtil.Code(emailCode,(String) session.getAttribute("emailCode"))) {
            String uid = rediscoverMapper.getEmailUid(email);
            rediscoverMapper.changePassword(uid,newPwd);
            return "success:";
        }
        return "error:";
    }
}
