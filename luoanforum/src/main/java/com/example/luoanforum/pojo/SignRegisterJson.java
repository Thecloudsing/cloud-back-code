package com.example.luoanforum.pojo;

/**
 * @author 落扶苏
 * @version 1.1
 */
public class SignRegisterJson {
    private String type;
    private String account;
    private String password;
    private String username;
    private String registerCode;
    private String emailLoginCode;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }

    public String getEmailLoginCode() {
        return emailLoginCode;
    }

    public void setEmailLoginCode(String emailLoginCode) {
        this.emailLoginCode = emailLoginCode;
    }
}
