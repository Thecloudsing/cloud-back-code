package com.luoanforum.authorization.handler;

import com.luoanforum.common.domain.vo.response.Result;
import com.luoanforum.common.exception.CommonErrorEnum;
import com.luoanforum.common.utils.JsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Oauth2FailureHandler implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String message;
        if (exception instanceof OAuth2AuthenticationException auth2AuthenticationException) {
            OAuth2Error error = auth2AuthenticationException.getError();
            message = "认证信息错误：" + error.getErrorCode() + error.getDescription();
        } else {
            message = exception.getMessage();
        }

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().write(JsonUtils.toStr(Result.fail(CommonErrorEnum.UNAUTHORIZED, message)));
        response.getWriter().flush();

    }
}