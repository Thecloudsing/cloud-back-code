package com.example.luoanforum.pojo;

/**
 * @author 落扶苏
 * @version 1.1
 */
public class AccountPwdCookie {
    private String uid;
    private String account;
    private String password;

    public AccountPwdCookie(String uid, String account, String password) {
        this.uid = uid;
        this.account = account;
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
}
