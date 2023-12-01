package com.dreams.common.interceptors;

import com.dreams.common.annotation.Accessibility;
import com.dreams.common.annotation.Authorization;
import com.dreams.common.exception.enums.HttpErrorEnum;
import com.dreams.common.service.TokenService;
import com.dreams.common.contant.MDCKey;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;

/**
 * Description:
 *
 * @author luoan
 * @since 2023/10/19
 */

@Slf4j
@Order(-2)
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHORIZATION_SCHEMA = "Bearer ";
    public static final String ATTRIBUTE_UID = "uid";
    @Autowired
    private TokenService tokenService;

    @Value("${auth.enable:true}")
    private boolean enableAuth;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = getToken(request);
        Long validUid = tokenService.getValidUid(token);

        if (Objects.isNull(validUid) && isInternal(handler)) {//没有登录态
            HttpErrorEnum.ACCESS_DENIED.sendHttpError(response);
            return false;
        }
        request.setAttribute(ATTRIBUTE_UID, validUid);
        MDC.put(MDCKey.UID, String.valueOf(validUid));
        return true;
    }

    // 获取状态
    private boolean isInternal(Object handler) {
        if (enableAuth && isAccessibility(handler)) {
            return false;
        }
        return isAuthorization(handler);
    }

    private boolean isAccessibility(Object handler) {
        if (handler instanceof HandlerMethod handlerMethod) {
            Method method = handlerMethod.getMethod();
            return method.isAnnotationPresent(Accessibility.class);
        }
        return false;
    }

    /**
     * 判断是不是公共方法，可以未登录访问的
     *
     * @param handler 切面处理器
     * @return boolean
     */
    private boolean isAuthorization(Object handler) {
        if (handler instanceof HandlerMethod handlerMethod) {
            Method method = handlerMethod.getMethod();
            return method.isAnnotationPresent(Authorization.class);
        }
        return false;
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER);
        return Optional.ofNullable(header)
                .filter(h -> h.startsWith(AUTHORIZATION_SCHEMA))
                .map(h -> h.substring(AUTHORIZATION_SCHEMA.length()))
                .orElse(null);
    }
}

