package com.dreams.oauth2.strategy.context;

import com.dreams.oauth2.domain.security.BasicOAuth2User;
import com.dreams.oauth2.strategy.Oauth2UserConverterStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

/**
 * 三方oauth2登录获取的用户信息转换处理
 *
 * @author luoan
 */
@Component
@RequiredArgsConstructor
public class Oauth2UserConverterContext {

    /**
     * 注入所有实例，map的key是实例在ioc中的名字
     * 这里通过构造器注入所有Oauth2UserConverterStrategy的实例，实例名之前在编写时已经通过
     * {@link Component }注解指定过bean的名字，可以根据给定bean名称从map中获取对应的实例(如果存在)
     */
    private final Map<String, Oauth2UserConverterStrategy> userConverterStrategyMap;

    /**
     * 获取转换器实例
     *
     * @param loginType 三方登录方式
     * @return 转换器实例 {@link Oauth2UserConverterStrategy}
     */
    public Oauth2UserConverterStrategy getInstance(String loginType) {
        if (ObjectUtils.isEmpty(loginType)) {
            throw new UnsupportedOperationException("登录方式不能为空.");
        }
        Oauth2UserConverterStrategy userConverterStrategy = userConverterStrategyMap.get(loginType);
        if (userConverterStrategy == null) {
            throw new UnsupportedOperationException("不支持[" + loginType + "]登录方式获取用户信息转换器");
        }
        return userConverterStrategy;
    }

    /**
     * 根据登录方式获取转换器实例，使用转换器获取用户信息
     *
     * @param userRequest 获取三方用户信息入参
     * @param oAuth2User  三方登录获取到的认证信息
     * @return {@link BasicOAuth2User}
     */
    public BasicOAuth2User convert(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        // 获取三方登录配置的registrationId，这里将他当做登录方式
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // 获取三方账号字段
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint()
                .getUserNameAttributeName();
        // 转换用户信息
        BasicOAuth2User basicOauth2User = this.getInstance(registrationId).convert(oAuth2User);
        // 设置三方账号字段
        basicOauth2User.setNameAttributeKey(userNameAttributeName);
        // 获取AccessToken
        OAuth2AccessToken accessToken = userRequest.getAccessToken();
        // 设置token
        basicOauth2User.setCredentials(accessToken.getTokenValue());
        // 设置账号的方式
        basicOauth2User.setType(registrationId);
        Instant expiresAt = accessToken.getExpiresAt();
        if (expiresAt != null) {
            LocalDateTime tokenExpiresAt = expiresAt.atZone(ZoneId.of("UTC")).toLocalDateTime();
            // token过期时间
            basicOauth2User.setCredentialsExpiresAt(tokenExpiresAt);
        }
        return basicOauth2User;
    }

}
