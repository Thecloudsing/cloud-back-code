package com.example.luoanforum.service.impl;

import com.example.luoanforum.mapper.UserMapper;
import com.example.luoanforum.pojo.AccountPwdCookie;
import com.example.luoanforum.pojo.UserInformation;
import com.example.luoanforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 落扶苏
 * @version 1.1
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public void ModificationInformation(String uid, String username, String gender, String birthday) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long newTime = 0;
        try {
            newTime = simpleDateFormat.parse(birthday).getTime();
        } catch (ParseException e) {e.printStackTrace();}
        long currentTime = new Date().getTime();
        String age = String.valueOf((currentTime - newTime) / 60 /60 / 24 / 365 /1000);
        userMapper.userModificationInformation(uid,username,age,gender,birthday);
    }

    @Override
    public void ModificationEmail(String uid, String email) {
        userMapper.userModificationEmail(uid,email);
    }

    @Override
    public void ModificationSms(String uid, String cell) {
        userMapper.userModificationSms(uid,cell);
    }

    @Override
    public void ModifiesAvatar(String uid, String url) {
        userMapper.userModifiesAvatar(uid,url);
    }

    @Override
    public void Likes(String uid) {
        userMapper.userLikes(uid);
    }

    @Override
    public UserInformation returnUserInformation(String uid) {
        return userMapper.returnUserInformation(uid);
    }

}
