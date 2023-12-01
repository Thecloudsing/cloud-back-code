package com.luoanforum.common.exception;


import lombok.Getter;

/**
 * Description: 业务异常
 */
@Getter
public class BusinessCustomException extends AbstractCustomException {

    public BusinessCustomException(ErrorEnum errorEnum) {
        super(errorEnum);
    }

    public BusinessCustomException(BusinessCustomError errorEnum, Throwable exception) {
        super(errorEnum, exception);
    }

    public static BusinessCustomException create(BusinessCustomError error) {
        return new BusinessCustomException(error);
    }
    public static BusinessCustomException create(BusinessCustomError error, Throwable exception) {
        return new BusinessCustomException(error, exception);
    }
}
