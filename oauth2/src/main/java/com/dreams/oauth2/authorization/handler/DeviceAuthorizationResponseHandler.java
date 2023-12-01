package com.dreams.oauth2.authorization.handler;

import com.dreams.oauth2.domain.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

/**
 * Description:
 *
 * @author luoan
 * @since 2023/10/27
 */
@RequiredArgsConstructor
public class DeviceAuthorizationResponseHandler extends AbstractAuthenticationHandler implements AuthenticationSuccessHandler {
    /**
     * 设备码验证成功后跳转地址
     */
    private final String deviceActivatedPageUri;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        Result<String> result = Result.success(deviceActivatedPageUri);
        responseResult(result, response);
    }
}
