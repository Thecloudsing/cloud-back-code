package com.dreams.oauth2.authorization.handler;

import com.dreams.oauth2.domain.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationException;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.util.Optional;

/**
 * Description: 授权确认前后端分离适配响应处理
 *
 * @author luoan
 * @since 2023/10/27
 */
@RequiredArgsConstructor
public class ConsentAuthorizationResponseHandler
        extends AbstractAuthenticationHandler
        implements AuthenticationSuccessHandler {
    /**
     * 授权确认页面
     */
    private final String consentPageUri;

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        String redirectUri = getAuthorizationRequestUri(authentication);
        if (request.getMethod().equals(HttpMethod.POST.name()) && UrlUtils.isAbsoluteUrl(redirectUri)) {
            Result<String> result = Result.success(redirectUri);
            responseResult(result, response);
        } else {
            redirectStrategy.sendRedirect(request, response, redirectUri);
        }
    }

    /**
     * 获取重定向的回调地址
     *
     * @param authentication 授权请求认证
     * @return 回调地址
     */
    private String getAuthorizationRequestUri(Authentication authentication) {
        OAuth2AuthorizationCodeRequestAuthenticationToken authorizationCodeRequest =
                (OAuth2AuthorizationCodeRequestAuthenticationToken) authentication;
        if (ObjectUtils.isEmpty(authorizationCodeRequest.getRedirectUri())) {
            String authorizeUriError = "Authorization Request Uri is empty";
            throw new OAuth2AuthorizationCodeRequestAuthenticationException(new OAuth2Error
                    (OAuth2ErrorCodes.INVALID_REQUEST, authorizeUriError, null),
                    authorizationCodeRequest);
        }
        Optional.ofNullable(authorizationCodeRequest.getAuthorizationCode())
                .orElseThrow(() -> new OAuth2AuthorizationCodeRequestAuthenticationException(new OAuth2Error
                        (OAuth2ErrorCodes.INVALID_REQUEST, "Authorization Code is empty", null),
                        authorizationCodeRequest));
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromUriString(authorizationCodeRequest.getRedirectUri())
                .queryParam(OAuth2ParameterNames.CODE, authorizationCodeRequest.getAuthorizationCode().getTokenValue());

        if (StringUtils.hasText(authorizationCodeRequest.getState())) {
            uriComponentsBuilder.queryParam(
                    OAuth2ParameterNames.STATE,
                    UriUtils.encode(authorizationCodeRequest.getState(), charset)
            );
        }
        return uriComponentsBuilder.build(true).toUriString();

    }
}
