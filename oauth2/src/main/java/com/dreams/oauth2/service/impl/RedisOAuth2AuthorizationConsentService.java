package com.dreams.oauth2.service.impl;

import com.dreams.oauth2.domain.entity.security.RedisAuthorizationConsent;
import com.dreams.oauth2.repository.RedisAuthorizationConsentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Description: 基于redis的授权确认服务实现
 *
 * @author luoan
 * @since 2023/10/27
 */
@Service
@RequiredArgsConstructor
public class RedisOAuth2AuthorizationConsentService implements OAuth2AuthorizationConsentService {
    private final RegisteredClientRepository registeredClientRepository;
    private final RedisAuthorizationConsentRepository authorizationConsentRepository;

    @Override
    public void save(OAuth2AuthorizationConsent authorizationConsent) {
        Assert.notNull(authorizationConsent, "authorizationConsent cannot be null");

        // 如果存在就先删除
        remove(authorizationConsent);
        // 保存
        RedisAuthorizationConsent entity = toEntity(authorizationConsent);
        entity.setId(UUID.randomUUID().toString());
        authorizationConsentRepository.save(entity);
    }

    @Override
    public void remove(OAuth2AuthorizationConsent authorizationConsent) {
        Assert.notNull(authorizationConsent, "authorizationConsent cannot be null");
        // 如果存在就删除
        authorizationConsentRepository.findByRegisteredClientIdAndPrincipalName(
                        authorizationConsent.getRegisteredClientId(), authorizationConsent.getPrincipalName())
                .ifPresent(existingConsent -> authorizationConsentRepository.deleteById(existingConsent.getId()));
    }

    @Override
    public OAuth2AuthorizationConsent findById(String registeredClientId, String principalName) {
        Assert.hasText(registeredClientId, "registeredClientId cannot be empty");
        Assert.hasText(principalName, "principalName cannot be empty");
        return authorizationConsentRepository.findByRegisteredClientIdAndPrincipalName(registeredClientId, principalName)
                .map(this::toObject)
                .orElse(null);
    }

    private OAuth2AuthorizationConsent toObject(RedisAuthorizationConsent authorizationConsent) {
        String registeredClientId = authorizationConsent.getRegisteredClientId();
        RegisteredClient registeredClient = Optional.ofNullable(registeredClientRepository.findById(registeredClientId))
                .orElseThrow(() -> new DataRetrievalFailureException(
                        "The RegisteredClient with id '" + registeredClientId + "' was not found in the RegisteredClientRepository.")
                );
        OAuth2AuthorizationConsent.Builder builder =
                OAuth2AuthorizationConsent.withId(authorizationConsent.getId(), authorizationConsent.getPrincipalName());

        Optional.ofNullable(authorizationConsent.getAuthorities())
                .map(StringUtils::commaDelimitedListToSet)
                .ifPresent(authorities -> authorities.forEach(authority -> builder.authority(new SimpleGrantedAuthority(authority))));

        return builder.build();
    }

    private RedisAuthorizationConsent toEntity(OAuth2AuthorizationConsent authorizationConsent) {
        Set<String> authorities = authorizationConsent.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        return RedisAuthorizationConsent.builder()
                .id(authorizationConsent.getRegisteredClientId())
                .principalName(authorizationConsent.getPrincipalName())
                .registeredClientId(authorizationConsent.getRegisteredClientId())
                .authorities(StringUtils.collectionToCommaDelimitedString(authorities))
                .build();
    }
}
