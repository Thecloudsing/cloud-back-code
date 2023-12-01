package com.luoanforum.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description: 公共错误枚举
 */
@Getter
@AllArgsConstructor
public enum CommonErrorEnum implements ErrorEnum {
    /**
     * 系统错误
     */
    SYSTEM_ERROR(10000, "系统出小差了，请稍后再试哦~~"),
    /**
     * 参数校验失败
     */
    PARAM_VALID(10001, "参数校验失败"),
    /**
     * 请求太频繁了，请稍后再试哦~~
     */
    FREQUENCY_LIMIT(10002, "请求太频繁了，请稍后再试哦~~"),
    /**
     * 请求太频繁了，请稍后再试哦~~
     */
    LOCK_LIMIT(10003, "请求太频繁了，请稍后再试哦~~"),
    /**
     * 未授权，无权限访问
     */
    UNAUTHORIZED(401, "未授权，无权限访问"),
    ;
    private final Integer errorCode;
    private final String errorMsg;
}
