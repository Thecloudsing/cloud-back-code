package com.dreams.common.service;

import com.dreams.common.domain.dto.RequestInfo;

/**
 * Description:
 *
 * @author luoan
 * @since 2023/10/19
 */
public class RequestHolder {
    private static final ThreadLocal<RequestInfo> threadLocal = new ThreadLocal<>();

    public static void set(RequestInfo requestInfo) {
        threadLocal.set(requestInfo);
    }

    public static RequestInfo get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
