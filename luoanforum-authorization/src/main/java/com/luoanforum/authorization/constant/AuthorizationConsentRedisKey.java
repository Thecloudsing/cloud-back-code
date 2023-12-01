package com.luoanforum.authorization.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthorizationConsentRedisKey implements ServicePartKey {
    /**
     * 查询时放入Redis中的部分 key
     */
    OAUTH2_AUTHORIZATION_CONSENT("oauth2_authorization_consent"),
    ;
    final String key;
}
