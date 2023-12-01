package com.dreams.oauth2.repository.impl;

import com.dreams.oauth2.domain.entity.security.RedisRegisteredClient;
import com.dreams.oauth2.repository.RedisClientRepository;
import com.dreams.oauth2.service.impl.RedisOAuth2AuthorizationService;
import com.dreams.oauth2.util.AuthorizationUtils;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * Description: 基于redis的客户端repository实现
 *
 * @author luoan
 * @since 2023/10/27
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class RedisRegisteredClientRepository implements RegisteredClientRepository {
    private final static AuthorizationUtils authorizationUtils =
            new AuthorizationUtils(RedisOAuth2AuthorizationService.class);
    private final PasswordEncoder passwordEncoder;
    private final RedisClientRepository redisClientRepository;

    @Override
    public void save(RegisteredClient registeredClient) {
        Assert.notNull(registeredClient, "registeredClient cannot be null");
        redisClientRepository.findByClientId(registeredClient.getClientId())
                .ifPresent(redisClientRepository::delete);
        redisClientRepository.save(toEntity(registeredClient));
    }

    @Override
    public RegisteredClient findById(String id) {
        Assert.hasText(id, "id cannot be empty");
        return redisClientRepository.findById(id)
                .map(this::toObject)
                .orElse(null);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Assert.hasText(clientId, "clientId cannot be empty");
        return redisClientRepository.findByClientId(clientId)
                .map(this::toObject)
                .orElse(null);
    }

    private RegisteredClient toObject(RedisRegisteredClient registeredClient) {
        Consumer<Set<ClientAuthenticationMethod>> clientAuthenticationMethodsConsumer
                = clientAuthenticationMethods ->
                StringUtils.commaDelimitedListToSet(registeredClient.getClientAuthenticationMethods())
                        .forEach(clientAuthenticationMethod ->
                                clientAuthenticationMethods.add(AuthorizationUtils.resolveClientAuthenticationMethod(clientAuthenticationMethod)));
        Consumer<Set<AuthorizationGrantType>> authorizationGrantTypesConsumer
                = authorizationGrantTypes ->
                StringUtils.commaDelimitedListToSet(registeredClient.getAuthorizationGrantTypes())
                        .forEach(authorizationGrantType ->
                                authorizationGrantTypes.add(AuthorizationUtils.resolveAuthorizationGrantType(authorizationGrantType)));
        Consumer<Set<String>> redirectUrisConsumer
                = redirectUris ->
                redirectUris.addAll(StringUtils.commaDelimitedListToSet(registeredClient.getRedirectUris()));
        Consumer<Set<String>> postLogoutRedirectUrisConsumer
                = postLogoutRedirectUris ->
                postLogoutRedirectUris.addAll(StringUtils.commaDelimitedListToSet(registeredClient.getPostLogoutRedirectUris()));
        Consumer<Set<String>> scopesConsumer
                = scopes ->
                scopes.addAll(StringUtils.commaDelimitedListToSet(registeredClient.getScopes()));
        return RegisteredClient.withId(registeredClient.getId())
                .clientId(registeredClient.getClientId())
                .clientIdIssuedAt(registeredClient.getClientIdIssuedAt())
                .clientSecret(registeredClient.getClientSecret())
                .clientSecretExpiresAt(registeredClient.getClientSecretExpiresAt())
                .clientName(registeredClient.getClientName())
                .clientAuthenticationMethods(clientAuthenticationMethodsConsumer)
                .authorizationGrantTypes(authorizationGrantTypesConsumer)
                .redirectUris(redirectUrisConsumer)
                .postLogoutRedirectUris(postLogoutRedirectUrisConsumer)
                .scopes(scopesConsumer)
                .clientSettings(ClientSettings.withSettings(authorizationUtils.parseMap(registeredClient.getClientSettings())).build())
                .tokenSettings(TokenSettings.withSettings(authorizationUtils.parseMap(registeredClient.getTokenSettings())).build())
                .build();

    }

    private RedisRegisteredClient toEntity(RegisteredClient registeredClient) {
        List<String> clientAuthenticationMethods = registeredClient.getClientAuthenticationMethods().stream()
                .map(ClientAuthenticationMethod::getValue)
                .toList();

        List<String> authorizationGrantTypes = registeredClient.getAuthorizationGrantTypes().stream()
                .map(AuthorizationGrantType::getValue)
                .toList();

        return RedisRegisteredClient.builder()
                .id(registeredClient.getId())
                .clientId(registeredClient.getClientId())
                .clientIdIssuedAt(registeredClient.getClientIdIssuedAt())
                .clientSecret(registeredClient.getClientSecret())
                .clientSecretExpiresAt(registeredClient.getClientSecretExpiresAt())
                .clientName(registeredClient.getClientName())
                .clientAuthenticationMethods(StringUtils.collectionToCommaDelimitedString(clientAuthenticationMethods))
                .authorizationGrantTypes(StringUtils.collectionToCommaDelimitedString(authorizationGrantTypes))
                .redirectUris(StringUtils.collectionToCommaDelimitedString(registeredClient.getRedirectUris()))
                .postLogoutRedirectUris(StringUtils.collectionToCommaDelimitedString(registeredClient.getPostLogoutRedirectUris()))
                .scopes(StringUtils.collectionToCommaDelimitedString(registeredClient.getScopes()))
                .clientSettings(authorizationUtils.writeMap(registeredClient.getClientSettings().getSettings()))
                .tokenSettings(authorizationUtils.writeMap(registeredClient.getTokenSettings().getSettings()))
                .build();
    }

    /**
     * 容器启动后初始化客户端
     * (不需要可删除)
     */
//    @PostConstruct
    public void initClients() {
        log.info("Initialize client information to Redis.");
        // 默认需要授权确认
        ClientSettings.Builder builder = ClientSettings.builder()
                .requireAuthorizationConsent(Boolean.TRUE);

        TokenSettings tokenSettings = TokenSettings.builder()
                // 自包含token(jwt)
                .accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
                // Access Token 存活时间：2小时
                .accessTokenTimeToLive(Duration.ofHours(2L))
                // 授权码存活时间：5分钟
                .authorizationCodeTimeToLive(Duration.ofMinutes(5L))
                // 设备码存活时间：5分钟
                .deviceCodeTimeToLive(Duration.ofMinutes(5L))
                // Refresh Token 存活时间：7天
                .refreshTokenTimeToLive(Duration.ofDays(7L))
                // 刷新 Access Token 后是否重用 Refresh Token
                .reuseRefreshTokens(Boolean.TRUE)
                // 设置 Id Token 加密方式
                .idTokenSignatureAlgorithm(SignatureAlgorithm.RS256)
                .build();

        // 正常授权码客户端
        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                // 客户端id
                .clientId("messaging-client")
                // 客户端名称
                .clientName("授权码")
                // 客户端秘钥，使用密码解析器加密
                .clientSecret(passwordEncoder.encode("123456"))
                // 客户端认证方式，基于请求头的认证
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                // 配置资源服务器使用该客户端获取授权时支持的方式
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                // 授权码模式回调地址，oauth2.1已改为精准匹配，不能只设置域名，并且屏蔽了localhost，本机使用127.0.0.1访问
                .redirectUri("http://127.0.0.1:8000/login/oauth2/code/messaging-client-oidc")
                .redirectUri("https://www.baidu.com")
                // 该客户端的授权范围，OPENID与PROFILE是IdToken的scope，获取授权时请求OPENID的scope时认证服务会返回IdToken
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                // 指定scope
                .scope("message.read")
                .scope("message.write")
                // 客户端设置，设置用户需要确认授权
                .clientSettings(builder.build())
                // token相关配置
                .tokenSettings(tokenSettings)
                .build();

        // 设备码授权客户端
        RegisteredClient deviceClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("device-message-client")
                .clientName("普通公共客户端")
                // 公共客户端
                .clientAuthenticationMethod(ClientAuthenticationMethod.NONE)
                // 设备码授权
                .authorizationGrantType(AuthorizationGrantType.DEVICE_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                // 指定scope
                .scope("message.read")
                .scope("message.write")
                // token相关配置
                .tokenSettings(tokenSettings)
                .build();

        // PKCE客户端
        RegisteredClient pkceClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("pkce-message-client")
                .clientName("PKCE流程")
                // 公共客户端
                .clientAuthenticationMethod(ClientAuthenticationMethod.NONE)
                // 设备码授权
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                // 授权码模式回调地址，oauth2.1已改为精准匹配，不能只设置域名，并且屏蔽了localhost，本机使用127.0.0.1访问
                .redirectUri("http://127.0.0.1:8000/login/oauth2/code/messaging-client-oidc")
                // 开启 PKCE 流程
                .clientSettings(builder.requireProofKey(Boolean.TRUE).build())
                // 指定scope
                .scope("message.read")
                .scope("message.write")
                // token相关配置
                .tokenSettings(tokenSettings)
                .build();

        // 初始化客户端
        this.save(registeredClient);
        this.save(deviceClient);
        this.save(pkceClient);
    }
}
