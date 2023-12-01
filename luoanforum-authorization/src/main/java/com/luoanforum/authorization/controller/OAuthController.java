package com.luoanforum.authorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.time.Duration;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/register/oauth", produces = "application/json;charset=UTF-8")
public class OAuthController {

    @Autowired
    private RegisteredClientRepository registeredClientRepository;

    @Autowired
    private OAuth2AuthorizationService authorizationService;

    @Autowired
    private OAuth2AuthorizationConsentService authorizationConsentService;


    @RequestMapping("/client")
    public Object index() {
        RegisteredClient build = RegisteredClient.withId(String.valueOf(UUID.randomUUID()))
                .clientId("oidc-client") // 客户端标识
                .clientSecret("{noop}secret") // 客户端密码
//                .clientIdIssuedAt(null) // 客户端标识发放时间
//                .clientSecretExpiresAt(null) // 客户端密码过期时间
                .clientName("OAuth2 Client") // 客户端名称
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC) // 请求头验证方式
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST) // 表单验证方式
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_JWT) // jwt 验证方式
//                .clientAuthenticationMethod(ClientAuthenticationMethod.PRIVATE_KEY_JWT) // 非对称加密方式
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE) // 授权码模式
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN) // 刷新令牌
//                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS) // 客户端模式
                .redirectUri("http://10.10.10.7:9900/callback") // 回调地址
                .postLogoutRedirectUri("http://localhost:9900/login") // 退出登录后重定向地址
                .scope(OidcScopes.OPENID) // OIDC 授权范围
//                .scope(OidcScopes.PROFILE) // OIDC 授权范围
//                .scope("message.read") // 自定义授权范围
//                .scope("message.write") // 自定义授权范围
//                .scope("all") // 自定义授权范围
                // 设置 Client 需要页面审核授权
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofDays(60 * 60 * 24 * 7)) // 7 天
                        .refreshTokenTimeToLive(Duration.ofDays(60 * 60 * 24 * 30)) // 30 天
                        .reuseRefreshTokens(true) // 是否重用刷新令牌
                        .build())
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();
        registeredClientRepository.save(build);
        return build;
    }

    @RequestMapping("/consent")
    public Object consent(String id, String username) {
        OAuth2AuthorizationConsent build = OAuth2AuthorizationConsent.withId("dbce278f-3ef0-47fe-8b09-306b8d0293b2","user")
//                .authority()
                .scope(OidcScopes.OPENID) // OIDC 授权范围
//                .scope(OidcScopes.PROFILE) // OIDC 授权范围
//                .scope("message.read") // 自定义授权范围
//                .scope("message.write") // 自定义授权范围
//                .scope("all") // 自定义授权范围
                .build();
        authorizationConsentService.save(build);
        return build;
    }

    @RequestMapping("/authorization")
    public Object authorization() {
        OAuth2Authorization authorization = OAuth2Authorization.withRegisteredClient(registeredClientRepository.findByClientId("oidc-client"))
                .principalName("user") // 用户名
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE) // 授权码模式
                .attribute("username", "user") // 用户名
                .attribute("password", "123456") // 密码
                .attribute("authorities", new GrantedAuthority[]{
                        new SimpleGrantedAuthority("ROLE_USER")
                })
                .build();
        authorizationService.save(authorization);
        return authorization;
    }

}
