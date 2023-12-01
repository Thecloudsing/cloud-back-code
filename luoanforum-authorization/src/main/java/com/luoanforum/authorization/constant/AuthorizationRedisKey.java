package com.luoanforum.authorization.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthorizationRedisKey implements ServicePartKey {
    /**
     * 根据 id 查询时放入Redis中的部分 key
     */
    OAUTH2_AUTHORIZATION_ID("oauth2_authorization:id"),

    /**
     * 根据 token类型、token 查询时放入Redis中的部分 key
     */
    OAUTH2_AUTHORIZATION_TOKEN_TYPE("oauth2_authorization:tokenType")
    ;

    final String key;
}
