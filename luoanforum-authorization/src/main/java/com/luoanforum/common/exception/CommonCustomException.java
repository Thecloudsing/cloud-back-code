package com.luoanforum.common.exception;

import lombok.Getter;

/**
 * Description: 公共异常
 */
@Getter
public class CommonCustomException extends AbstractCustomException {

    public CommonCustomException(CommonErrorEnum errorEnum) {
        super(errorEnum);
    }

    public CommonCustomException(CommonErrorEnum errorEnum, Throwable exception) {
        super(errorEnum, exception);
    }

    public static CommonCustomException create(CommonErrorEnum error) {
        return new CommonCustomException(error);
    }

    public static CommonCustomException create(CommonErrorEnum error, Throwable exception) {
        return new CommonCustomException(error, exception);
    }
}
