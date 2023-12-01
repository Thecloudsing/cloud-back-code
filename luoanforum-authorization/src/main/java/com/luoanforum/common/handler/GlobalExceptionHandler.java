package com.luoanforum.common.handler;

import com.luoanforum.common.domain.vo.response.Result;
import com.luoanforum.common.exception.BusinessCustomException;
import com.luoanforum.common.exception.CommonErrorEnum;
import com.luoanforum.common.exception.CommonCustomException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.Null;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Description: Controller全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler implements ResponseBodyAdvice {
    /**
     * 业务异常
     * @param e 业务异常对象
     * @return 错误信息
     */
    @ExceptionHandler(value = BusinessCustomException.class)
    public Result<Null> businessExceptionHandler(BusinessCustomException e) {
        log.warn("business exception！The reason is：{}", e.getMessage());
        return Result.fail(e.getErrorCode(), e.getErrorMsg());
    }

    /**
     * 常见异常
     * @param e 常见异常对象
     * @return 错误信息
     */
    @ExceptionHandler(value = CommonCustomException.class)
    public Result<Null> commonExceptionHandler(CommonCustomException e) {
        log.warn("common exception！The reason is：{}", e.getMessage());
        return Result.fail(e.getErrorCode(), e.getErrorMsg());
    }

    /**
     * MethodArgumentNotValidException validation参数校验异常
     * @param e validation参数校验异常对象 @Valid
     * @return 错误信息
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class) // @Valid
    public Result<Null> methodArgumentNotValidExceptionExceptionHandler(MethodArgumentNotValidException e) {
        StringBuilder errorMsg = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(x -> errorMsg.append(x.getField()).append(x.getDefaultMessage()).append(","));
        String message = errorMsg.toString();
        log.info("validation parameters error！The reason is:{}", message);
        return Result.fail(CommonErrorEnum.PARAM_VALID.getErrorCode(), message.substring(0, message.length() - 1));
    }

    /**
     * BindException validation参数校验异常
     * @param e validation参数校验异常对象 @Validated
     * @return 错误信息
     */
    @ExceptionHandler(value = BindException.class) // @Validated
    public Result<Null> bindException(BindException e) {
        StringBuilder errorMsg = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(x -> errorMsg.append(x.getField()).append(x.getDefaultMessage()).append(","));
        String message = errorMsg.toString();
        log.info("validation parameters error！The reason is:{}", message);
        return Result.fail(CommonErrorEnum.PARAM_VALID.getErrorCode(), message.substring(0, message.length() - 1));
    }

    /**
     * Exception 未知异常
     * @param e 未知异常对象
     * @return 错误信息
     */
    @ExceptionHandler(value = Exception.class)
    public Result<Null> systemExceptionHandler(Exception e) {
        log.error("system exception！The reason is：{}", e.getMessage(), e);
        return Result.fail(CommonErrorEnum.SYSTEM_ERROR);
    }

    /**
     * NullPointerException 空指针异常
     * @param e 空指针异常对象
     * @return 错误信息
     */
    @ExceptionHandler(value = NullPointerException.class)
    public Result<Null> exceptionHandler(NullPointerException e) {
        log.error("null point exception！The reason is:{}", e.getMessage(), e);
        return Result.fail(CommonErrorEnum.SYSTEM_ERROR);
    }

    /**
     * Throwable 顶级异常
     * @param e Throwable异常对象
     * @return 错误信息
     */
    @ExceptionHandler(value = Throwable.class)
    public Result<Null> throwableHandler(Throwable e) {
        log.error("throwable exception！The reason is:{}", e.getMessage(), e);
        return Result.fail(CommonErrorEnum.SYSTEM_ERROR);
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType, // 返回值类型
            MediaType selectedContentType, // 选择的内容类型
            Class selectedConverterType, // 选择的转换器类型
            ServerHttpRequest request,
            ServerHttpResponse response
    ) {
        return Result.success(body);
    }
}
