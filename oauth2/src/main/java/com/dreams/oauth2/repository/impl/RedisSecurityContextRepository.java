package com.dreams.oauth2.repository.impl;

import com.dreams.oauth2.constant.RedisConstants;
import com.dreams.oauth2.constant.SecurityConstants;
import com.dreams.oauth2.domain.security.SupplierDeferredSecurityContext;
import com.dreams.oauth2.support.RedisOperator;
import com.dreams.oauth2.support.RedisUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.DeferredSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.function.Supplier;

/**
 * Description: 基于redis存储认证信息
 *
 * @author luoan
 * @since 2023/10/27
 */
@Repository
@RequiredArgsConstructor
public class RedisSecurityContextRepository implements SecurityContextRepository {
    private final RedisOperator<SecurityContext> redisOperator;
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        // HttpServletRequest request = requestResponseHolder.getRequest();
        // return readSecurityContextFromRedis(request);
        // 方法已过时，使用 loadDeferredContext 方法
        throw new UnsupportedOperationException("Method deprecated.");
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        String nonce = getNonce(request);
        if (ObjectUtils.isEmpty(nonce)) {
            return;
        }

        // 如果当前的context是空的，则移除
        SecurityContext emptyContext = securityContextHolderStrategy.createEmptyContext();
        if (emptyContext.equals(context)) {
            // todo redis bug
//            redisOperator.delete((RedisConstants.SECURITY_CONTEXT_PREFIX_KEY + nonce));
            RedisUtils.del((RedisConstants.SECURITY_CONTEXT_PREFIX_KEY + nonce));
        } else {
            // 保存认证信息
            // todo redis bug
//            redisOperator.set((RedisConstants.SECURITY_CONTEXT_PREFIX_KEY + nonce), context, RedisConstants.DEFAULT_TIMEOUT_SECONDS);
            RedisUtils.set((RedisConstants.SECURITY_CONTEXT_PREFIX_KEY + nonce), context, RedisConstants.DEFAULT_TIMEOUT_SECONDS);
        }
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        String nonce = getNonce(request);
        if (ObjectUtils.isEmpty(nonce)) {
            return false;
        }
        // todo redis bug
//        return redisOperator.get(RedisConstants.SECURITY_CONTEXT_PREFIX_KEY + nonce) != null;
        return RedisUtils.get(RedisConstants.SECURITY_CONTEXT_PREFIX_KEY + nonce, SecurityContext.class) != null;
    }

    @Override
    public DeferredSecurityContext loadDeferredContext(HttpServletRequest request) {
        Supplier<SecurityContext> supplier = () -> readSecurityContextFromRedis(request);
        return new SupplierDeferredSecurityContext(supplier, securityContextHolderStrategy);
    }

    /**
     * 从redis中读取认证信息
     *
     * @param request 当前请求
     * @return 认证信息
     */
    private SecurityContext readSecurityContextFromRedis(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        String nonce = getNonce(request);
        if (ObjectUtils.isEmpty(nonce)) {
            return null;
        }
        // todo redis bug
//        return redisOperator.get((RedisConstants.SECURITY_CONTEXT_PREFIX_KEY + nonce));
        return RedisUtils.get((RedisConstants.SECURITY_CONTEXT_PREFIX_KEY + nonce), SecurityContext.class);
    }

    /**
     * 先从请求头中找，找不到去请求参数中找，找不到获取当前session的id
     * 获取当前session的sessionId
     *
     * @param request 当前请求
     * @return 随机字符串(sessionId)，这个字符串本来是前端生成，现在改为后端获取的sessionId
     */
    private String getNonce(HttpServletRequest request) {
        String nonce = request.getHeader(SecurityConstants.NONCE_HEADER_NAME);
        if (ObjectUtils.isEmpty(nonce)) {
            nonce = request.getParameter(SecurityConstants.NONCE_HEADER_NAME);
            HttpSession session = request.getSession(Boolean.FALSE);
            if (ObjectUtils.isEmpty(nonce) && session != null) {
                nonce = session.getId();
            }
        }
        return nonce;

    }
}
