package com.dreams.common.domain.vo.response;

import com.dreams.common.exception.ErrorEnum;
import lombok.Data;

/**
 * Description: base response body
 *
 * @author luoan
 * @since 2023/10/19
 */

@Data
public class ApiResult<T> {
    /**
     * 成功标识true or false
     */
    private Boolean success;
    /**
     * 错误码
     */
    private Integer errCode;
    /**
     * 错误消息
     */
    private String errMsg;
    /**
     * 返回对象
     */
    private T data;
    public static ApiResult<String> redirect(String url) {
        ApiResult<String> result = new ApiResult<>();
        result.setData(url);
        result.setErrCode(401);
        result.setErrMsg("redirect");
        result.setSuccess(Boolean.TRUE);
        return result;
    }
    public static <T> ApiResult<T> success() {
        ApiResult<T> result = new ApiResult<T>();
        result.setData(null);
        result.setSuccess(Boolean.TRUE);
        return result;
    }

    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> result = new ApiResult<T>();
        result.setData(data);
        result.setSuccess(Boolean.TRUE);
        return result;
    }

    public static <T> ApiResult<T> fail(Integer code, String msg) {
        ApiResult<T> result = new ApiResult<T>();
        result.setSuccess(Boolean.FALSE);
        result.setErrCode(code);
        result.setErrMsg(msg);
        return result;
    }

    public static <T> ApiResult<T> fail(ErrorEnum errorEnum) {
        ApiResult<T> result = new ApiResult<T>();
        result.setSuccess(Boolean.FALSE);
        result.setErrCode(errorEnum.getErrorCode());
        result.setErrMsg(errorEnum.getErrorMsg());
        return result;
    }

    public boolean isSuccess() {
        return this.success;
    }
}
