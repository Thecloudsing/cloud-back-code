package com.luoanforum.authorization.service;

import com.luoanforum.authorization.client.RedisRegisteredClientRepository;
import com.luoanforum.authorization.configuration.RedisSpringAuthorizationServerConfiguration;
import com.luoanforum.authorization.constant.AuthorizationRedisKey;
import com.luoanforum.authorization.constant.RedisKey;
import com.luoanforum.authorization.constant.ServicePartKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.server.authorization.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
//@Service
public class RedisOAuth2AuthorizationService implements OAuth2AuthorizationService {

    @Autowired
    @Qualifier(RedisSpringAuthorizationServerConfiguration.REDIS_TEMPLATE_OAUTH2_AUTHORIZATION_BEAN_NAME)
    private RedisTemplate<String, OAuth2Authorization> redisTemplate;
    private JdbcOAuth2AuthorizationService jdbcOAuth2AuthorizationService;

    @Autowired
    private RedisKey redisKey;

    @Autowired
    public void setJdbcOAuth2AuthorizationConsentService(JdbcTemplate jdbcTemplate, RedisRegisteredClientRepository registeredClientRepository) {
        this.jdbcOAuth2AuthorizationService = new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
    }

    @Override
    public void save(OAuth2Authorization authorization) {
        Assert.notNull(authorization, "authorization cannot be null");
        setAuthorization(authorization);
        jdbcOAuth2AuthorizationService.save(authorization);
    }

    @Override
    public void remove(OAuth2Authorization authorization) {
        Assert.notNull(authorization, "authorization cannot be null");
        redisTemplate.delete(
                getAuthorizationKey(AuthorizationRedisKey.OAUTH2_AUTHORIZATION_ID, authorization.getId())
        );
        Optional.ofNullable(authorization.getAccessToken())
                .map(OAuth2Authorization.Token::getToken)
                .map(OAuth2AccessToken::getTokenValue)
                .ifPresent(tokenValue -> {
                    redisTemplate.delete(
                            getAuthorizationKey(
                                    AuthorizationRedisKey.OAUTH2_AUTHORIZATION_TOKEN_TYPE,
                                    OAuth2TokenType.ACCESS_TOKEN.getValue(),
                                    tokenValue
                            )
                    );
                });
        Optional.ofNullable(authorization.getRefreshToken())
                .map(OAuth2Authorization.Token::getToken)
                .map(OAuth2RefreshToken::getTokenValue)
                .ifPresent(tokenValue -> {
                    redisTemplate.delete(
                            getAuthorizationKey(
                                    AuthorizationRedisKey.OAUTH2_AUTHORIZATION_TOKEN_TYPE,
                                    OAuth2TokenType.REFRESH_TOKEN.getValue(),
                                    tokenValue
                            )
                    );
                });
        jdbcOAuth2AuthorizationService.remove(authorization);
    }

    @Override
    public OAuth2Authorization findById(String id) {
        Assert.hasText(id, "id cannot be null");
        return Optional.ofNullable(getAuthorization(AuthorizationRedisKey.OAUTH2_AUTHORIZATION_ID, id))
                .map((authorization) -> {
                    log.debug("Redis Authorization FindById Found ==> {}", authorization);
                    return authorization;
                })
                .orElseGet(() -> Optional
                        .ofNullable(jdbcOAuth2AuthorizationService.findById(id))
                        .map((authorization -> {
                            log.debug("Redis Authorization FindById Not Found, Try To Find In Database ==> {}", authorization);
                            setAuthorization(authorization);
                            return authorization;
                        }))
                        .orElseThrow()
                );
    }

    @Override
    public OAuth2Authorization findByToken(String token, OAuth2TokenType tokenType) {
        Assert.hasText(token, "token cannot be null");
        Assert.notNull(tokenType, "tokenType cannot be null");
        return Optional.ofNullable(
                getAuthorization(
                        AuthorizationRedisKey.OAUTH2_AUTHORIZATION_TOKEN_TYPE,
                        tokenType.getValue(), token
                ))
                .map((authorization) -> {
                    log.debug("Redis Authorization FindByToken Found ==> {}", authorization);
                    return authorization;
                })
                .orElseGet(() -> Optional
                        .ofNullable(jdbcOAuth2AuthorizationService.findByToken(token, tokenType))
                        .map((authorization -> {
                            log.debug("Redis Authorization FindByToken Not Found, Try To Find In Database ==> {}", authorization);
                            setAuthorization(authorization);
                            return authorization;
                        }))
                        .orElseThrow()
                );
    }

    /**
     * 保存授权信息到redis
     * @param authorization {@link OAuth2Authorization}
     */
     public void setAuthorization(OAuth2Authorization authorization) {
         long timeout = redisKey.getRedisProperties().getAuthorizationTimeout();
         redisTemplate.opsForValue().set(
                 getAuthorizationKey(AuthorizationRedisKey.OAUTH2_AUTHORIZATION_ID, authorization.getId()),
                 authorization,
                 timeout,
                 TimeUnit.SECONDS
         );

         Optional.ofNullable(authorization.getAccessToken())
                 .map(OAuth2Authorization.Token::getToken)
                 .map(OAuth2AccessToken::getTokenValue)
                 .ifPresent(tokenValue -> {
                     redisTemplate.opsForValue().set(
                             getAuthorizationKey(
                                     AuthorizationRedisKey.OAUTH2_AUTHORIZATION_TOKEN_TYPE,
                                     OAuth2TokenType.ACCESS_TOKEN.getValue(),
                                     tokenValue
                             ),
                             authorization,
                             timeout,
                             TimeUnit.SECONDS
                     );
                 });

            Optional.ofNullable(authorization.getRefreshToken())
                    .map(OAuth2Authorization.Token::getToken)
                    .map(OAuth2RefreshToken::getTokenValue)
                    .ifPresent(tokenValue -> {
                        redisTemplate.opsForValue().set(
                                getAuthorizationKey(
                                        AuthorizationRedisKey.OAUTH2_AUTHORIZATION_TOKEN_TYPE,
                                        OAuth2TokenType.REFRESH_TOKEN.getValue(),
                                        tokenValue
                                ),
                                authorization,
                                timeout,
                                TimeUnit.SECONDS
                        );
                    });
     }

    /**
     * 从redis中获取授权信息
     */
    public OAuth2Authorization getAuthorization(AuthorizationRedisKey authorizationRedisKey, String ...params) {
        return redisTemplate.opsForValue().get(
                getAuthorizationKey(authorizationRedisKey, params)
        );
    }

/**
  * 获取授权信息的key
  * @param authorizationKey {@link AuthorizationRedisKey}
  * @param params 其他拼接参数
  * @return key
  */
    public String getAuthorizationKey(AuthorizationRedisKey authorizationKey, String ...params) {
        return redisKey.getRedisKey(authorizationKey, params);
    }
}
