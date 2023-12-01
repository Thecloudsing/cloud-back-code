package com.dreams.oauth2.authorization.handler;

import com.dreams.oauth2.domain.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

/**
 * Description: 登录失败处理类
 *
 * @author luoan
 * @since 2023/10/27
 */
@Slf4j
public class LoginFailureHandler extends AbstractAuthenticationHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception
    ) throws IOException, ServletException {
        log.warn("Login failure. {}", exception.getMessage());
        Result<Object> result = Result.fail(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
        responseResult(result, response);
    }
}
