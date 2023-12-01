package com.luoanforum.common.domain.vo.response;

import com.luoanforum.common.exception.ErrorEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("返回规范")
public class Result<T> {
    @ApiModelProperty("成功标识")
    private Boolean success;
    @ApiModelProperty("错误码")
    private Integer errCode;
    @ApiModelProperty("错误信息")
    private String errMsg;
    @ApiModelProperty("数据")
    private T data;
    /**
     * 返回空数据【成功】
     */
    public static <T> Result<T> success() {
        return Result.<T>builder()
                .success(Boolean.TRUE)
                .build();
    }
    /**
     * 返回数据【成功】
     */
    public static <T> Result<T> success(T data) {
        return Result.<T>builder()
                .success(Boolean.TRUE)
                .data(data)
                .build();
    }
    /**
     * 返回错误信息【失败】
     */
    public static <T> Result<T> fail(@NonNull Integer code, String msg) {
        return Result.<T>builder()
                .success(Boolean.FALSE)
                .errCode(code)
                .errMsg(msg)
                .build();
    }
    /**
     * 返回错误信息【失败】
     */
    public static <T> Result<T> fail(@NonNull ErrorEnum errorEnum) {
        return Result.<T>builder()
                .success(Boolean.FALSE)
                .errCode(errorEnum.getErrorCode())
                .errMsg(errorEnum.getErrorMsg())
                .build();
    }

    /**
     * 返回错误信息【失败】
     */
    public static <T> Result<T> fail(ErrorEnum errorEnum, String msg) {
        return Result.<T>builder()
                .success(Boolean.FALSE)
                .errCode(errorEnum.getErrorCode())
                .errMsg(errorEnum.getErrorMsg())
                .errMsg(String.format("%s => %s", errorEnum.getErrorMsg(), msg))
                .build();
    }
}
