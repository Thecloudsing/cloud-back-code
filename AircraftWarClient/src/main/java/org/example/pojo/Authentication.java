package org.example.pojo;

import java.io.Serializable;

public class Authentication implements Serializable {

    private String code;
    private String account;
    private String password;
    private static final long serialVersionUID = 1L;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
