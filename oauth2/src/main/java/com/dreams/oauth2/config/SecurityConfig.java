package com.dreams.oauth2.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.authorization.authentication.JwtClientAssertionAuthenticationProvider;
import org.springframework.security.oauth2.server.authorization.authentication.JwtClientAssertionDecoderFactory;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.token.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig {

    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

    //    @Bean
//    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
            throws Exception {

//        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer =
//                new OAuth2AuthorizationServerConfigurer();
//        http.apply(authorizationServerConfigurer);
//
//        authorizationServerConfigurer
//                // 用于管理新客户端和现有客户端的 Registered ClientRepository（必需）。
////                .registeredClientRepository(registeredClientRepository())
//                // 用于管理新授权和现有授权的 OAuth2AuthorizationService。
////                .authorizationService(authorizationService)
//                // OAuth2AuthorizationConsentService 用于管理新的和现有的授权同意。
////                .authorizationConsentService(authorizationConsentService)
//                // 授权服务器设置（必需），用于自定义 OAuth2 授权服务器的配置设置。
////                .authorizationServerSettings(authorizationServerSettings())
//                // OAuth2TokenGenerator，用于生成 OAuth2 授权服务器支持的令牌。
////                .tokenGenerator(tokenGenerator)
//                // OAuth2 授权端点的配置程序。
//                .authorizationEndpoint(authorizationEndpoint -> { })
//                // OAuth2 设备授权端点的配置程序。
//                .deviceAuthorizationEndpoint(deviceAuthorizationEndpoint -> { })
//                // OAuth2 设备验证端点的配置程序。
//                .deviceVerificationEndpoint(deviceVerificationEndpoint -> { })
//                // OAuth2 令牌端点的配置程序。
//                .tokenEndpoint(tokenEndpoint -> { })
//                // OAuth2 令牌侦测端点的配置程序。
//                .tokenIntrospectionEndpoint(tokenIntrospectionEndpoint -> { })
//                // OAuth2 令牌吊销端点的配置程序。
//                .tokenRevocationEndpoint(tokenRevocationEndpoint -> { })
//                // OAuth2 授权服务器元数据端点的配置程序。
//                .authorizationServerMetadataEndpoint(authorizationServerMetadataEndpoint -> { })
//                // // OAuth2 客户端身份验证的配置程序。
//                .clientAuthentication(clientAuthentication ->
//                        clientAuthentication
//                                // 将尝试从中提取客户端凭据时使用的（预处理器）添加到 的实例。
//                                // AuthenticationConverterHttpServletRequestOAuth2ClientAuthenticationToken
////                                .authenticationConverter(authenticationConverter)
//                                // 设置提供对默认和（可选）添加的访问，允许添加、删除或自定义特定
//                                // .ConsumerListAuthenticationConverterAuthenticationConverter
////                                .authenticationConverters(authenticationConvertersConsumer)
//                                // 添加用于验证 .AuthenticationProviderOAuth2ClientAuthenticationToken
////                                .authenticationProvider(authenticationProvider)
//                                // 设置提供对默认和（可选）添加的访问，允许添加、删除或自定义特定
//                                // .ConsumerListAuthenticationProviderAuthenticationProvider
//                                .authenticationProviders(configureJwtClientAssertionValidator())
//                                // （后处理器）用于处理成功的客户端身份验证并将 与 关联。
//                                // AuthenticationSuccessHandlerOAuth2ClientAuthenticationTokenSecurityContext
////                                .authenticationSuccessHandler(authenticationSuccessHandler)
//                                // 用于处理失败的客户端身份验证并返回 OAuth2Error 响应的（后处理器）。AuthenticationFailureHandler
////                                .errorResponseHandler(errorResponseHandler)
//                )
//                .oidc(oidc -> oidc
//                        // OpenID Connect 1.0 提供程序配置端点的配置程序。
//                        .providerConfigurationEndpoint(providerConfigurationEndpoint -> { })
//                        // OpenID Connect 1.0 注销端点的配置程序。
//                        .logoutEndpoint(logoutEndpoint -> { })
//                        // OpenID Connect 1.0 UserInfo 端点的配置程序。
//                        .userInfoEndpoint(userInfoEndpoint -> { })
//                        // OpenID Connect 1.0 客户端注册端点的配置程序。
//                        .clientRegistrationEndpoint(clientRegistrationEndpoint -> { })
//                );

//        return http.build();


//        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
//        http.apply(authorizationServerConfigurer)
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());    // Enable OpenID Connect 1.0
        http
                // Redirect to the login page when not authenticated from the
                // authorization endpoint
                .exceptionHandling((exceptions) -> exceptions
                        .defaultAuthenticationEntryPointFor(
                                new LoginUrlAuthenticationEntryPoint("/login"),
                                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
                        )
                )
                // Accept access tokens for User Info and/or Client Registration
                .oauth2ResourceServer((resourceServer) -> resourceServer
                        .jwt(Customizer.withDefaults()));

        return http.build();
    }

    //    @Bean
//    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .anyRequest().authenticated()
                )
                // Form login handles the redirect to the login page from the
                // authorization server filter chain
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

//    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("user")
                .password("123456")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(userDetails);
    }

//    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("oidc-client")
                .clientSecret("{noop}secret")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("http://127.0.0.1:8080/callback")
                .postLogoutRedirectUri("http://127.0.0.1:8080/")
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();

        return new InMemoryRegisteredClientRepository(oidcClient);
    }

//    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

//    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

//    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder()
                .issuer("https://localhost:9000")
                .authorizationEndpoint("/oauth2/authorize")
                .deviceAuthorizationEndpoint("/oauth2/device_authorization")
                .deviceVerificationEndpoint("/oauth2/device_verification")
                .tokenEndpoint("/oauth2/token")
                .tokenIntrospectionEndpoint("/oauth2/introspect")
                .tokenRevocationEndpoint("/oauth2/revoke")
                .jwkSetEndpoint("/oauth2/jwks")
                .oidcLogoutEndpoint("/connect/logout")
                .oidcUserInfoEndpoint("/connect/userinfo")
                .oidcClientRegistrationEndpoint("/connect/register")
                .build();
    }

//    @Bean
    public OAuth2TokenGenerator<?> tokenGenerator() {
        JwtEncoder jwtEncoder = new NimbusJwtEncoder(jwkSource());
        JwtGenerator jwtGenerator = new JwtGenerator(jwtEncoder);
        OAuth2AccessTokenGenerator accessTokenGenerator = new OAuth2AccessTokenGenerator();
        OAuth2RefreshTokenGenerator refreshTokenGenerator = new OAuth2RefreshTokenGenerator();
        return new DelegatingOAuth2TokenGenerator(
                jwtGenerator, accessTokenGenerator, refreshTokenGenerator);
    }


    private Consumer<List<AuthenticationProvider>> configureJwtClientAssertionValidator() {
        return (authenticationProviders) ->
                authenticationProviders.forEach((authenticationProvider) -> {
                    if (authenticationProvider instanceof JwtClientAssertionAuthenticationProvider) {
                        // Customize JwtClientAssertionDecoderFactory
                        JwtClientAssertionDecoderFactory jwtDecoderFactory = new JwtClientAssertionDecoderFactory();
                        Function<RegisteredClient, OAuth2TokenValidator<Jwt>> jwtValidatorFactory = (registeredClient) ->
                                new DelegatingOAuth2TokenValidator<>(
                                        // Use default validators
                                        JwtClientAssertionDecoderFactory.DEFAULT_JWT_VALIDATOR_FACTORY.apply(registeredClient),
                                        // Add custom validator
                                        new JwtClaimValidator<>("claim", "value"::equals));
                        jwtDecoderFactory.setJwtValidatorFactory(jwtValidatorFactory);

                        ((JwtClientAssertionAuthenticationProvider) authenticationProvider)
                                .setJwtDecoderFactory(jwtDecoderFactory);
                    }
                });
    }

    //    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    //    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

}

