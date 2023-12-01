package org.dreams.weyun.common.exception.enums;

import org.dreams.weyun.common.exception.ErrorEnum;
import lombok.AllArgsConstructor;

/**
 * Description:
 *
 * @author luoan
 * @since 2023/10/19
 */
@AllArgsConstructor
public enum CommonErrorEnum implements ErrorEnum {

    /**
     * 通用异常
     */
    SYSTEM_ERROR(1000,"系统出错"),
    PARAM_VALID(2000,"参数校验失败"),
    ;

    private final Integer errorCode;
    private final String errorMsg;

    @Override
    public Integer getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
