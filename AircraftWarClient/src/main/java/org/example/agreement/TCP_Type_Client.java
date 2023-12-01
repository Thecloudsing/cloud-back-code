package org.example.agreement;

import java.io.Serializable;

public enum TCP_Type_Client implements Serializable {
    LOGIN_SUCCESS,//登录成功
    LOGIN_FAILURE,//登录失败
    REGISTER_SUCCESS,//注册成功

    //REGISTER_ACCOUNT_FAILURE,//注册账号重复
    REGISTER_CODE_FAILURE,//注册码错误

    //REGISTER_CODE_TIMEOUT,//注册码超时
    REGISTER_GET_CODE_SUCCESS,//获取注册码成功
    REGISTER_GET_CODE_FAILURE,//获取注册码失败
    MODIFY_SUCCESS,//密码修改成功
    MODIFY_PASSWORD_FAILURE,//旧密码错误
    FORGET_SUCCESS,//找回密码成功
    FORGET_CODE_FAILURE,//找回码错误

    //FORGET_CODE_TIMEOUT,//找回码超时
    FORGET_GET_CODE_SUCCESS,//获取找回码成功
    FORGET_GET_CODE_FAILURE,//获取找回码失败
    FRIEND_GET_SUCCESS,//获取好友列表
    FRIEND_ADD_SUCCESS,//添加好友
    CREATE_ROOM_SUCCESS,//创建房间成功
    CREATE_ROOM_CODE_FAILURE,//创建房间码重复
    JOIN_ROOM_SUCCESS,//加入房间成功
    JOIN_ROOM_OVERFLOW_FAILURE,//加入房间满人
    EXIT_ROOM_SUCCESS,//退出房间成功
    EXIT_ROOM_DESTRUCTION,//房间销毁
    GAME_INITIALIZE,//游戏初始化
    GAME_RUNNING,//游戏运行中
    GAME_END,//游戏结束
    Exit_APP//退出
}
