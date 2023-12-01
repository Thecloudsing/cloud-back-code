package com.example.luoanforum.service;

import com.example.luoanforum.pojo.AccountPwdCookie;
import com.example.luoanforum.pojo.UserInformation;

/**
 * @author 落扶苏
 * @version 1.1
 */
public interface UserService {
    //修改用户基本数据
    void ModificationInformation(String uid, String username, String gender, String birthday);

    //修改用户邮箱
    void ModificationEmail(String uid, String email);

    //修改用户手机号
    void ModificationSms(String uid, String cell);

    //修改用户头像地址
    void ModifiesAvatar(String uid, String url);

    //用户点赞
    void Likes(String uid);

    //返回用户个人信息
    UserInformation returnUserInformation(String uid);

}
