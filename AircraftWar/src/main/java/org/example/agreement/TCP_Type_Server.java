package org.example.agreement;

import java.io.Serializable;

public enum TCP_Type_Server implements Serializable {
    LOGIN("Login"),//登录
    REGISTER("Register"),//注册
    FORGET("Forget"),//忘记
    GET_REGISTER_CODE("GetRegisterCode"),//获取注册码
    GET_FORGET_CODE("GetForgetCode"),//获取找回码
    CREATE_ROOM("CreateRoom"),//创建
    JOIN_ROOM("JoinRoom"),//加入
    MATCHING("Matching"),//匹配
    ADD_FRIEND("AddFriend"),//加好友
    FRIEND("Friend"),//朋友
    GAME("Game"),//游戏
    OFFLINE("Offline"),//掉线
    EXIT("Exit"),
    EXIT_ROOM("ExitRoom");//退出

    public final String value;
    TCP_Type_Server(String value) {
        this.value = value;
    }
}
