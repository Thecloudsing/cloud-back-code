package com.luoanforum.authorization.service;

import com.luoanforum.authorization.client.RedisRegisteredClientRepository;
import com.luoanforum.authorization.configuration.RedisSpringAuthorizationServerConfiguration;
import com.luoanforum.authorization.constant.AuthorizationConsentRedisKey;
import com.luoanforum.authorization.constant.RedisKey;
import com.luoanforum.authorization.properties.SpringAuthorizationServerRedisProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RedisOAuth2AuthorizationConsentService implements OAuth2AuthorizationConsentService {
    @Autowired
    @Qualifier(RedisSpringAuthorizationServerConfiguration.REDIS_TEMPLATE_OAUTH2_AUTHORIZATION_CONSENT_BEAN_NAME)
    private RedisTemplate<String, OAuth2AuthorizationConsent> redisTemplate;
    private JdbcOAuth2AuthorizationConsentService jdbcOAuth2AuthorizationConsentService;

    @Autowired
    private RedisKey redisKey;

    @Autowired
    public void setJdbcOAuth2AuthorizationConsentService(JdbcTemplate jdbcTemplate, RedisRegisteredClientRepository registeredClientRepository) {
        this.jdbcOAuth2AuthorizationConsentService = new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
    }

    @Override
    public void save(OAuth2AuthorizationConsent authorizationConsent) {
        Assert.notNull(authorizationConsent, "authorizationConsent cannot be null");
        setAuthorizationConsent(authorizationConsent);
        jdbcOAuth2AuthorizationConsentService.save(authorizationConsent);
    }

    @Override
    public void remove(OAuth2AuthorizationConsent authorizationConsent) {
        Assert.notNull(authorizationConsent, "authorizationConsent cannot be null");
        redisTemplate.delete(getAuthorizationConsentKey(authorizationConsent));
        jdbcOAuth2AuthorizationConsentService.remove(authorizationConsent);
    }

    @Override
    public OAuth2AuthorizationConsent findById(String registeredClientId, String principalName) {
        Assert.hasText(registeredClientId, "registeredClientId cannot be empty");
        Assert.hasText(principalName, "principalName cannot be empty");
        OAuth2AuthorizationConsent oAuth2AuthorizationConsent = redisTemplate
                .opsForValue().get(getAuthorizationConsentKey(registeredClientId, principalName));
        return Optional
                .ofNullable(oAuth2AuthorizationConsent)
                .map(authorizationConsent -> {
                    log.debug("Redis AuthorizationConsent Found ==> {}", authorizationConsent);
                    return authorizationConsent;
                })
                .orElseGet(() -> Optional.ofNullable(
                        jdbcOAuth2AuthorizationConsentService.findById(registeredClientId, principalName)
                                )
                            .map((ac) -> {
                                log.debug("Redis AuthorizationConsent Not Found, Try To Find In Database ==> {}", ac);
                                setAuthorizationConsent(ac);
                                return ac;
                            })
                            .orElseThrow()
                );
    }

    /**
     * 持久化 Redis AuthorizationConsent
     * @param authorizationConsent ${@link OAuth2AuthorizationConsent}
     */
    public void setAuthorizationConsent(@NonNull OAuth2AuthorizationConsent authorizationConsent) {
        redisTemplate.opsForValue().set(
                getAuthorizationConsentKey(authorizationConsent),
                authorizationConsent,
                redisKey.getRedisProperties().getAuthorizationConsentTimeout(),
                TimeUnit.SECONDS
        );
    }

    /**
     * 获取 Redis AuthorizationConsent Key
     */
    public String getAuthorizationConsentKey(@NonNull OAuth2AuthorizationConsent authorizationConsent) {
        return redisKey.getRedisKey(
                AuthorizationConsentRedisKey.OAUTH2_AUTHORIZATION_CONSENT,
                authorizationConsent.getRegisteredClientId(),
                authorizationConsent.getPrincipalName()
        );
    }

    /**
     * 获取 Redis AuthorizationConsent Key
     */
    public String getAuthorizationConsentKey(@NonNull String registeredClientId, @NonNull String principalName) {
        return redisKey.getRedisKey(
                AuthorizationConsentRedisKey.OAUTH2_AUTHORIZATION_CONSENT,
                registeredClientId,
                principalName
        );
    }
}
