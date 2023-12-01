package com.dreams.common.exception.enums;

import com.dreams.common.exception.ErrorEnum;
import lombok.AllArgsConstructor;

/**
 * Description:
 *
 * @author luoan
 * @since 2023/10/19
 */
@AllArgsConstructor
public enum BusinessErrorEnum implements ErrorEnum {

    /**
     * 业务异常
     */
    BUSINESS_ERROR(8000, "业务异常"),
    ;

    private final Integer errorCode;
    private final String errorMsg;

    @Override
    public Integer getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return null;
    }
}
