package com.luoanforum.authorization.constant;

import com.luoanforum.authorization.properties.SpringAuthorizationServerRedisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Component
public class RedisKey implements RedisKeyInterface {

    @Autowired
    private SpringAuthorizationServerRedisProperties springAuthorizationServerRedisProperties;

    @Override
    public String getRedisKey(ServicePartKey servicePartKey, String ...params) {
        return splicingKey(servicePartKey.getKey(), params);
    }

    public String getRedisKey(String ...params) {
        return splicingKey(null, params);
    }

    /**
     * 拼接 Redis Key
     * @return key
     */
    public String splicingKey(String prefix, String ...params) {
        StringBuilder builder = new StringBuilder(springAuthorizationServerRedisProperties.getPrefix());
        Optional.ofNullable(prefix).ifPresent(pre -> builder.append(":").append(pre));
        for (String param : params) {
            builder.append(":").append(param);
        }
        return builder.toString();
    }

    public SpringAuthorizationServerRedisProperties getRedisProperties() {
        return springAuthorizationServerRedisProperties;
    }
}
