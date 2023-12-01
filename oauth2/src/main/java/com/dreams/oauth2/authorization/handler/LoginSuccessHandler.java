package com.dreams.oauth2.authorization.handler;

import com.dreams.oauth2.domain.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

/**
 * Description: 登录成功处理类
 *
 * @author luoan
 * @since 2023/10/27
 */
@Slf4j
public class LoginSuccessHandler extends AbstractAuthenticationHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        log.info("Login success. {}", authentication.getDetails());
        Result<String> result = Result.success();
        responseResult(result, response);
    }
}
