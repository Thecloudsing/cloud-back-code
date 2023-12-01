package org.example.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class CodeData {
    private final Random RANDOM = new Random();
    private final Map<String,RegisterCode> REGISTER_CODE_MAP = new HashMap<>();
    private final Map<String,ForgetCode> FORGET_CODE_MAP = new HashMap<>();

    @Value("${register_code_Length}")
    private int registerCodeLength;
    @Value("${forget_code_Length}")
    private int forgetCodeLength;
    private long registerTimeout;
    private long forgetTimeout;
    private final StringBuilder STRING_BUILDER = new StringBuilder();
    private String randomCode(int codeLength) {
        if (this.STRING_BUILDER.length() != 0) {
            this.STRING_BUILDER.delete(0,this.STRING_BUILDER.length());
        }
        for (int i = 0; i < codeLength; i++) {
            this.STRING_BUILDER.append(this.RANDOM.nextInt(10));
        }
        return this.STRING_BUILDER.toString();
    }
    class RegisterCode {
        private final String code = randomCode(registerCodeLength);
        private final long timestamp = new Date().getTime();
    }

    class ForgetCode {
        private final String code = randomCode(forgetCodeLength);
        private final long timestamp = new Date().getTime();
    }

    @Value("${register_timeout}")
    private void setRegisterTimeout(int s) {
        this.registerTimeout = s * 1000L;
    }
    @Value("${forget_timeout}")
    private void setForgetTimeout(int s) {
        this.forgetTimeout = s * 1000L;
    }
    public String addRegisterCode(String account) {
        RegisterCode registerCode = new RegisterCode();
        this.REGISTER_CODE_MAP.put(account,registerCode);
        return registerCode.code;
    }

    public String addForgetCode(String account) {
        ForgetCode forgetCode = new ForgetCode();
        this.FORGET_CODE_MAP.put(account,forgetCode);
        return forgetCode.code;
    }

    public boolean verificationRegisterCode(String account, String code) {
        if (!this.REGISTER_CODE_MAP.containsKey(account))
            return false;
        RegisterCode registerCode = this.REGISTER_CODE_MAP.get(account);
        if (!registerCode.code.equals(code))
            return false;
        if (registerCode.timestamp + registerTimeout <= new Date().getTime()) {
            return false;
        }
        return true;
    }

    public boolean verificationForgetCode(String account, String code) {
        if (!this.FORGET_CODE_MAP.containsKey(account))
            return false;
        ForgetCode forgetCode = this.FORGET_CODE_MAP.get(account);
        if (!forgetCode.code.equals(code))
            return false;
        if (forgetCode.timestamp + forgetTimeout <= new Date().getTime()) {
            return false;
        }
        return true;
    }
}
