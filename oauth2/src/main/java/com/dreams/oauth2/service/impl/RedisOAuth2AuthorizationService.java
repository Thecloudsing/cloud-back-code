package com.dreams.oauth2.service.impl;

import com.dreams.oauth2.domain.entity.security.RedisOAuth2Authorization;
import com.dreams.oauth2.repository.RedisOAuth2AuthorizationRepository;
import com.dreams.oauth2.util.AuthorizationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationCode;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/**
 * Description: 基于redis的授权管理服务
 *
 * @author luoan
 * @since 2023/10/27
 */
@Service
@RequiredArgsConstructor
public class RedisOAuth2AuthorizationService implements OAuth2AuthorizationService {
    private static final AuthorizationUtils authorizationUtils = new AuthorizationUtils(RedisOAuth2AuthorizationService.class);
    private final RegisteredClientRepository registeredClientRepository;
    private final RedisOAuth2AuthorizationRepository oAuth2AuthorizationRepository;

    @Override
    public void save(OAuth2Authorization authorization) {
        Optional<RedisOAuth2Authorization> existingAuthorization = oAuth2AuthorizationRepository.findById(authorization.getId());

        // 如果存在就先删除
        existingAuthorization.map(RedisOAuth2Authorization::getId)
                .ifPresent(oAuth2AuthorizationRepository::deleteById);

        // 过期时间，默认永不过期
        AtomicLong maxTimeout = new AtomicLong(-1L);
        // 所以code的过期时间，方便计算最大值
        List<Instant> expiresAtList = new ArrayList<>();

        RedisOAuth2Authorization entity = toEntity(authorization);

        // 如果有过期时间就存入
        Optional.ofNullable(entity.getAuthorizationCodeExpiresAt())
                .ifPresent(expiresAtList::add);

        // 如果有过期时间就存入
        Optional.ofNullable(entity.getAccessTokenExpiresAt())
                .ifPresent(expiresAtList::add);

        // 如果有过期时间就存入
        Optional.ofNullable(entity.getRefreshTokenExpiresAt())
                .ifPresent(expiresAtList::add);

        // 如果有过期时间就存入
        Optional.ofNullable(entity.getOidcIdTokenExpiresAt())
                .ifPresent(expiresAtList::add);

        // 如果有过期时间就存入
        Optional.ofNullable(entity.getUserCodeExpiresAt())
                .ifPresent(expiresAtList::add);

        // 如果有过期时间就存入
        Optional.ofNullable(entity.getDeviceCodeExpiresAt())
                .ifPresent(expiresAtList::add);

        // 获取最大的日期
        expiresAtList.stream().max(Comparator.comparing(Instant::toEpochMilli))
                .ifPresent(maxInstant -> {
                    // 计算时间差
                    Duration between = Duration.between(Instant.now(), maxInstant);
                    // 转为分钟
                    maxTimeout.set(between.toMillis());
                });

        // 设置过期时间
        entity.setTimeout(maxTimeout.get());

        // 保存至redis
        oAuth2AuthorizationRepository.save(entity);
    }

    @Override
    public void remove(OAuth2Authorization authorization) {
        Assert.notNull(authorization, "authorization cannot be null");
        oAuth2AuthorizationRepository.deleteById(authorization.getId());
    }

    @Override
    public OAuth2Authorization findById(String id) {
        Assert.hasText(id, "id cannot be empty");
        return oAuth2AuthorizationRepository.findById(id)
                .map(this::toObject)
                .orElse(null);
    }

    @Override
    public OAuth2Authorization findByToken(String token, OAuth2TokenType tokenType) {
        Assert.hasText(token, "token cannot be empty");
        Optional<RedisOAuth2Authorization> result;
        if (tokenType == null) {
            result = oAuth2AuthorizationRepository.findByState(token)
                    .or(() -> oAuth2AuthorizationRepository.findByAuthorizationCodeValue(token))
                    .or(() -> oAuth2AuthorizationRepository.findByAccessTokenValue(token))
                    .or(() -> oAuth2AuthorizationRepository.findByOidcIdTokenValue(token))
                    .or(() -> oAuth2AuthorizationRepository.findByRefreshTokenValue(token))
                    .or(() -> oAuth2AuthorizationRepository.findByUserCodeValue(token))
                    .or(() -> oAuth2AuthorizationRepository.findByDeviceCodeValue(token));
        } else switch (tokenType.getValue()) {
            case OAuth2ParameterNames.STATE -> result = oAuth2AuthorizationRepository.findByState(token);
            case OAuth2ParameterNames.CODE ->
                    result = oAuth2AuthorizationRepository.findByAuthorizationCodeValue(token);
            case OAuth2ParameterNames.ACCESS_TOKEN ->
                    result = oAuth2AuthorizationRepository.findByAccessTokenValue(token);
            case OAuth2ParameterNames.REFRESH_TOKEN ->
                    result = oAuth2AuthorizationRepository.findByRefreshTokenValue(token);
            case OidcParameterNames.ID_TOKEN -> result = oAuth2AuthorizationRepository.findByOidcIdTokenValue(token);
            case OAuth2ParameterNames.USER_CODE -> result = oAuth2AuthorizationRepository.findByUserCodeValue(token);
            case OAuth2ParameterNames.DEVICE_CODE ->
                    result = oAuth2AuthorizationRepository.findByDeviceCodeValue(token);
            default -> result = Optional.empty();
        }
        return result.map(this::toObject).orElse(null);
    }

    /**
     * 将redis中存储的类型转为框架所需的类型
     *
     * @param entity redis中存储的类型
     * @return 框架所需的类型
     */
    private OAuth2Authorization toObject(RedisOAuth2Authorization entity) {
        RegisteredClient registeredClient = Optional.ofNullable(registeredClientRepository.findById(entity.getId()))
                .orElseThrow(() -> new DataRetrievalFailureException(
                        "The RegisteredClient with id '" + entity.getRegisteredClientId()
                                + "' was not found in the RegisteredClientRepository."));

        OAuth2Authorization.Builder builder = OAuth2Authorization.withRegisteredClient(registeredClient)
                .id(entity.getId())
                .principalName(entity.getPrincipalName())
                .authorizationGrantType(AuthorizationUtils.resolveAuthorizationGrantType(entity.getAuthorizationGrantType()))
                .authorizedScopes(StringUtils.commaDelimitedListToSet(entity.getAuthorizedScopes()))
                .attributes(attributes -> attributes.putAll(authorizationUtils.parseMap(entity.getAttributes())));

        Optional.ofNullable(entity.getState()).ifPresent(state -> builder.attribute(OAuth2ParameterNames.STATE, state));
        Optional.ofNullable(entity.getAuthorizationCodeValue()).ifPresent(code -> {
            OAuth2AuthorizationCode authorizationCode = new OAuth2AuthorizationCode(
                    code,
                    entity.getAuthorizationCodeIssuedAt(),
                    entity.getAuthorizationCodeExpiresAt()
            );
            builder.token(authorizationCode, metadata -> metadata.putAll(authorizationUtils.parseMap(entity.getAuthorizationCodeMetadata())));
        });
        Optional.ofNullable(entity.getAccessTokenValue()).ifPresent(token -> {
            OAuth2AccessToken accessToken = new OAuth2AccessToken(
                    OAuth2AccessToken.TokenType.BEARER,
                    token,
                    entity.getAccessTokenIssuedAt(),
                    entity.getAccessTokenExpiresAt(),
                    StringUtils.commaDelimitedListToSet(entity.getAccessTokenScopes())
            );
            builder.token(accessToken, metadata -> metadata.putAll(authorizationUtils.parseMap(entity.getAccessTokenMetadata())));
        });
        Optional.ofNullable(entity.getRefreshTokenValue()).ifPresent(token -> {
            OAuth2RefreshToken refreshToken = new OAuth2RefreshToken(
                    token,
                    entity.getRefreshTokenIssuedAt(),
                    entity.getRefreshTokenExpiresAt()
            );
            builder.token(refreshToken, metadata -> metadata.putAll(authorizationUtils.parseMap(entity.getRefreshTokenMetadata())));
        });
        Optional.ofNullable(entity.getOidcIdTokenValue()).ifPresent(token -> {
            OidcIdToken oidcIdToken = new OidcIdToken(
                    token,
                    entity.getOidcIdTokenIssuedAt(),
                    entity.getOidcIdTokenExpiresAt(),
                    authorizationUtils.parseMap(entity.getOidcIdTokenClaims())
            );
            builder.token(oidcIdToken, metadata -> metadata.putAll(authorizationUtils.parseMap(entity.getOidcIdTokenMetadata())));
        });
        Optional.ofNullable(entity.getUserCodeValue()).ifPresent(code -> {
            OAuth2UserCode userCode = new OAuth2UserCode(
                    code,
                    entity.getUserCodeIssuedAt(),
                    entity.getUserCodeExpiresAt()
            );
            builder.token(userCode, metadata -> metadata.putAll(authorizationUtils.parseMap(entity.getUserCodeMetadata())));
        });
        Optional.ofNullable(entity.getDeviceCodeValue()).ifPresent(code -> {
            OAuth2DeviceCode deviceCode = new OAuth2DeviceCode(
                    code,
                    entity.getDeviceCodeIssuedAt(),
                    entity.getDeviceCodeExpiresAt()
            );
            builder.token(deviceCode, metadata -> metadata.putAll(authorizationUtils.parseMap(entity.getDeviceCodeMetadata())));
        });
        return builder.build();
    }

    private RedisOAuth2Authorization toEntity(OAuth2Authorization authorization) {
        RedisOAuth2Authorization entity = RedisOAuth2Authorization.builder()
                .id(authorization.getId())
                .registeredClientId(authorization.getRegisteredClientId())
                .principalName(authorization.getPrincipalName())
                .authorizationGrantType(authorization.getAuthorizationGrantType().getValue())
                .authorizedScopes(StringUtils.collectionToCommaDelimitedString(authorization.getAuthorizedScopes()))
                .attributes(StringUtils.collectionToCommaDelimitedString(authorization.getAttributes().keySet()))
                .state(authorization.getAttribute(OAuth2ParameterNames.STATE))
                .build();

        OAuth2Authorization.Token<OAuth2AuthorizationCode> authorizationCode =
                authorization.getToken(OAuth2AuthorizationCode.class);
        setTokenValues(
                authorizationCode,
                entity::setAuthorizationCodeValue,
                entity::setAuthorizationCodeIssuedAt,
                entity::setAuthorizationCodeExpiresAt,
                entity::setAuthorizationCodeMetadata
        );

        OAuth2Authorization.Token<OAuth2AccessToken> accessToken =
                authorization.getToken(OAuth2AccessToken.class);
        setTokenValues(
                accessToken,
                entity::setAccessTokenValue,
                entity::setAccessTokenIssuedAt,
                entity::setAccessTokenExpiresAt,
                entity::setAccessTokenMetadata
        );
        if (accessToken != null && accessToken.getToken().getScopes() != null) {
            entity.setAccessTokenScopes(StringUtils.collectionToDelimitedString(accessToken.getToken().getScopes(), ","));
        }

        OAuth2Authorization.Token<OAuth2RefreshToken> refreshToken =
                authorization.getToken(OAuth2RefreshToken.class);
        setTokenValues(
                refreshToken,
                entity::setRefreshTokenValue,
                entity::setRefreshTokenIssuedAt,
                entity::setRefreshTokenExpiresAt,
                entity::setRefreshTokenMetadata
        );

        OAuth2Authorization.Token<OidcIdToken> oidcIdToken =
                authorization.getToken(OidcIdToken.class);
        setTokenValues(
                oidcIdToken,
                entity::setOidcIdTokenValue,
                entity::setOidcIdTokenIssuedAt,
                entity::setOidcIdTokenExpiresAt,
                entity::setOidcIdTokenMetadata
        );
        if (oidcIdToken != null) {
            entity.setOidcIdTokenClaims(authorizationUtils.writeMap(oidcIdToken.getClaims()));
        }

        OAuth2Authorization.Token<OAuth2UserCode> userCode =
                authorization.getToken(OAuth2UserCode.class);
        setTokenValues(
                userCode,
                entity::setUserCodeValue,
                entity::setUserCodeIssuedAt,
                entity::setUserCodeExpiresAt,
                entity::setUserCodeMetadata
        );

        OAuth2Authorization.Token<OAuth2DeviceCode> deviceCode =
                authorization.getToken(OAuth2DeviceCode.class);
        setTokenValues(
                deviceCode,
                entity::setDeviceCodeValue,
                entity::setDeviceCodeIssuedAt,
                entity::setDeviceCodeExpiresAt,
                entity::setDeviceCodeMetadata
        );

        return entity;

    }

    /**
     * 设置token的值
     *
     * @param token              Token实例
     * @param tokenValueConsumer set方法
     * @param issuedAtConsumer   set方法
     * @param expiresAtConsumer  set方法
     * @param metadataConsumer   set方法
     */
    private void setTokenValues(
            OAuth2Authorization.Token<?> token,
            Consumer<String> tokenValueConsumer,
            Consumer<Instant> issuedAtConsumer,
            Consumer<Instant> expiresAtConsumer,
            Consumer<String> metadataConsumer) {
        if (token != null) {
            OAuth2Token oAuth2Token = token.getToken();
            tokenValueConsumer.accept(oAuth2Token.getTokenValue());
            issuedAtConsumer.accept(oAuth2Token.getIssuedAt());
            expiresAtConsumer.accept(oAuth2Token.getExpiresAt());
            metadataConsumer.accept(authorizationUtils.writeMap(token.getMetadata()));
        }
    }
}
