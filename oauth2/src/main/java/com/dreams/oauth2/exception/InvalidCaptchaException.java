package com.dreams.oauth2.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Description: 验证码异常类 <br/>
 * <strong style="color: red">校验验证码异常时抛出</strong>
 *
 * @author luoan
 * @since 2023/10/25
 */
public class InvalidCaptchaException extends AuthenticationException {
    public InvalidCaptchaException(String msg) {
        super(msg);
    }
}
