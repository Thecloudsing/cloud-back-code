package com.dreams.oauth2.domain.entity.security;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

/**
 * Description: 基于redis的授权确认存储实体
 *
 * @author luoan
 * @since 2023/10/25
 */
@Data
@Builder
@RedisHash(value = "authorizationConsent")
public class RedisAuthorizationConsent {
    /**
     * 额外提供的主键
     */
    @Id
    private String id;

    /**
     * 当前授权确认的客户端id
     */
    @Indexed
    private String registeredClientId;

    /**
     * 当前授权确认用户的 username
     */
    @Indexed
    private String principalName;

    /**
     * 授权确认的scope
     */
    private String authorities;
}
