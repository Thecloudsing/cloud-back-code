package org.dreams.weyun.common.exception;

import lombok.Data;

import java.io.Serial;

/**
 * Description:
 *
 * @author luoan
 * @since 2023/10/19
 */
public class BusinessException extends RuntimeException implements ErrorEnum {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected Integer errorCode;

    /**
     * 错误信息
     */
    protected String errorMsg;

    public BusinessException(Integer errorCode, String format) {
        super(format);
        this.errorCode = errorCode;
        this.errorMsg = format;
    }


    @Override
    public String getMessage() {
        return errorMsg;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
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
