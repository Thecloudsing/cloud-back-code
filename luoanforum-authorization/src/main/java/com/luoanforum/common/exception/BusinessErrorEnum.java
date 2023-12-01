package com.luoanforum.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.Serializable;
import java.lang.constant.Constable;
import java.lang.constant.ConstantDesc;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Description: 业务校验异常码
 */
@Getter
@AllArgsConstructor
public enum BusinessErrorEnum implements BusinessCustomError {
//    USER_EXIST(1001, "用户已存在"),
//    USER_NOT_EXIST(1002, "用户不存在"),
//    USER_LOGIN_ERROR(1003, "用户名或密码错误"),
//    USER_LOGIN_EXPIRED(1004, "登录已过期，请重新登录"),
//    USER_LOGIN_ERROR_MORE(1005, "密码错误次数过多，请稍后再试"),
    BUSINESS_ERROR(1111, "业务异常"),
    ;
    /**
     * 错误码
     */
    private final Integer errorCode;
    /**
     * 错误信息
     */
    private final String errorMsg;

}
