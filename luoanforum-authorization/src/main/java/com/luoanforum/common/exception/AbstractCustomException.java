package com.luoanforum.common.exception;

import java.io.Serial;

/**
 * Description: 异常抽象类 自定义异常继承此类
 */
public abstract class AbstractCustomException extends RuntimeException implements ErrorEnum {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Integer errorCode;
    private final String errorMsg;


    public AbstractCustomException(ErrorEnum errorEnum) {
        super(errorEnum.getErrorMsg());
        errorCode = errorEnum.getErrorCode();
        errorMsg = errorEnum.getErrorMsg();

    }
    public AbstractCustomException(ErrorEnum errorEnum, Throwable exception) {
        super(errorEnum.getErrorMsg(), exception);
        this.errorCode = errorEnum.getErrorCode();
        this.errorMsg = errorEnum.getErrorMsg();
    }

    @Override
    public Integer getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
