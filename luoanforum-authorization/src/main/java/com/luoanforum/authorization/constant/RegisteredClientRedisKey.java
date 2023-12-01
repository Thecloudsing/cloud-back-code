package com.luoanforum.authorization.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RegisteredClientRedisKey implements ServicePartKey {

    /**
     * 根据 id 查询时放入Redis中的部分 key
     */
    REGISTERED_CLIENT_ID("registered_client:id"),

    /**
     * 根据 clientId 查询时放入Redis中的部分 key
     */
    REGISTERED_CLIENT_CLIENT_ID("registered_client:clientId"),
    ;

    final String key;

}
