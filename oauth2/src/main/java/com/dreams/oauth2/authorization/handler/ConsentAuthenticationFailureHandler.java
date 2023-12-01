package com.dreams.oauth2.authorization.handler;

import com.dreams.oauth2.domain.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.UrlUtils;

import java.io.IOException;

/**
 * Description: 授权确认失败处理
 *
 * @author luoan
 * @since 2023/10/27
 */
@RequiredArgsConstructor
public class ConsentAuthenticationFailureHandler
        extends AbstractAuthenticationHandler
        implements AuthenticationFailureHandler {
    /**
     * 授权确认页面路径
     */
    private final String consentPageUri;

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception
    ) throws IOException, ServletException {
        // 获取当前认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 获取具体的异常
        OAuth2AuthenticationException oAuth2AuthenticationException = (OAuth2AuthenticationException) exception;
        OAuth2Error error = oAuth2AuthenticationException.getError();
        // 异常信息
        String message = authentication == null ? "登录已失效" : error.toString(); // 第二次点击“拒绝”会因为之前取消时删除授权申请记录而找不到对应的数据，导致抛出 [invalid_request] OAuth 2.0 Parameter: state

        if (request.getMethod().equals(HttpMethod.POST.toString())
                && UrlUtils.isAbsoluteUrl(consentPageUri)) {
            Result<Object> result = Result.fail(HttpStatus.BAD_REQUEST.value(), message);
            responseResult(result, response);
        } else {
            // 在地址栏输入授权申请地址或设备码流程的验证地址错误(user_code错误)
            response.sendError(HttpStatus.BAD_REQUEST.value(), error.toString());
        }
    }
}
