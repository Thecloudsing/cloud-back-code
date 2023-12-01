package org.example.agreement;

import java.io.Serializable;

public enum TCP_Type_Client implements Serializable {
    LOGIN_SUCCESS("LoginService"),//登录成功
    LOGIN_FAILURE("LoginService"),//登录失败
    REGISTER_SUCCESS("RegisterService"),//注册成功

    //REGISTER_ACCOUNT_FAILURE,//注册账号重复
    REGISTER_CODE_FAILURE("RegisterService"),//注册码错误

    //REGISTER_CODE_TIMEOUT,//注册码超时
    REGISTER_GET_CODE_SUCCESS("RegisterService"),//获取注册码成功
    REGISTER_GET_CODE_FAILURE("RegisterService"),//获取注册码失败
    MODIFY_SUCCESS("ForgetService"),//密码修改成功
    MODIFY_PASSWORD_FAILURE("ForgetService"),//旧密码错误
    FORGET_SUCCESS("ForgetService"),//找回密码成功
    FORGET_CODE_FAILURE("ForgetService"),//找回码错误

    //FORGET_CODE_TIMEOUT,//找回码超时
    FORGET_GET_CODE_SUCCESS("ForgetService"),//获取找回码成功
    FORGET_GET_CODE_FAILURE("ForgetService"),//获取找回码失败
    FRIEND_GET_SUCCESS("FriendService"),//获取好友列表
    FRIEND_ADD_SUCCESS("FriendService"),//添加好友
    CREATE_ROOM_SUCCESS("RoomService"),//创建房间成功
    CREATE_ROOM_CODE_FAILURE("RoomService"),//创建房间码重复
    JOIN_ROOM_SUCCESS("RoomService"),//加入房间成功
    JOIN_ROOM_OVERFLOW_FAILURE("RoomService"),//加入房间满人
    EXIT_ROOM_SUCCESS("RoomService"),//退出房间成功
    EXIT_ROOM_DESTRUCTION("RoomService"),//房间销毁
    GAME_INITIALIZE("GameService"),//游戏初始化
    GAME_RUNNING("GameService"),//游戏运行中
    GAME_END("GameService"),//游戏结束
    Exit_APP("ExitService"),//退出
    MATCH_SUCCESS("MatchService"),//匹配成功
    MATCH_FAILURE("MatchService"),//匹配失败
    MATCH_ING("MatchService"),//匹配中
    MATCH_EXIT("MatchService");//退出匹配

    public final String value;

    TCP_Type_Client(String value) {
        this.value = value;
    }

}
