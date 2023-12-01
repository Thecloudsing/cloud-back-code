package com.dreams.oauth2.authorization.handler;

import com.dreams.oauth2.constant.SecurityConstants;
import com.dreams.oauth2.domain.Result;
import com.dreams.oauth2.util.JsonUtils;
import io.netty.handler.codec.http.HttpMethod;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Description:
 *
 * @author luoan
 * @since 2023/10/27
 */
@Slf4j
public class LoginTargetAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
    /**
     * 设备码认证页面地址
     */
    private final String deviceActivatePageUri;
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * @param loginFormUrl          URL where the login page can be found. Should either be
     *                              relative to the web-app context path (include a leading {@code /}) or an absolute
     *                              URL.
     * @param deviceActivatePageUri 设备码验证页面地址
     */

    public LoginTargetAuthenticationEntryPoint(String loginFormUrl, String deviceActivatePageUri) {
        super(loginFormUrl);
        this.deviceActivatePageUri = deviceActivatePageUri;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String deviceVerificationUri = "/oauth2/device_verification";
        // 兼容设备码前后端分离
        if (request.getRequestURI().equals(deviceVerificationUri)
                && request.getMethod().equals(HttpMethod.POST.name())
                && UrlUtils.isAbsoluteUrl(deviceActivatePageUri)) {
            // 如果是请求验证设备激活码(user_code)时未登录并且设备码验证页面是前后端分离的那种则写回json
            Result<String> success = Result.fail(HttpStatus.UNAUTHORIZED.value(), ("登录已失效，请重新打开设备提供的验证地址"));
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(JsonUtils.objectCovertToJson(success));
            response.getWriter().flush();
            return;
        }

        // 获取表单登录地址
        String loginForm = determineUrlToUseForThisRequest(request, response, authException);
        if (!UrlUtils.isAbsoluteUrl(loginForm)) {
            // 不是绝对路径调用父类方法处理
            super.commence(request, response, authException);
            return;
        }
        StringBuffer requestUrl = request.getRequestURL();
        if (!ObjectUtils.isEmpty(requestUrl)) {
            requestUrl.append('?').append(request.getQueryString());
        }
        // 重定向地址添加nonce参数，该参数的值为sessionId
        // 绝对路径在重定向前添加target参数
        String targetParameter = URLEncoder.encode(requestUrl.toString(), StandardCharsets.UTF_8);
        String targetUrl = UriComponentsBuilder.fromUriString(loginForm)
                .queryParam("target", targetParameter)
                .queryParam(SecurityConstants.NONCE_HEADER_NAME, request.getSession().getId())
                .build().toUriString();
        log.debug("重定向至前后端分离的登录页面：{}", targetUrl);
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
}
