package com.dreams.oauth2.config;

import com.dreams.oauth2.authorization.basic.BasicAccessTokenResponseClient;
import com.dreams.oauth2.authorization.basic.BasicAuthorizationRequestResolver;
import com.dreams.oauth2.authorization.handler.LoginFailureHandler;
import com.dreams.oauth2.authorization.handler.LoginSuccessHandler;
import com.dreams.oauth2.property.CustomSecurityProperties;
import com.dreams.oauth2.repository.impl.RedisSecurityContextRepository;
import com.dreams.oauth2.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * Description:
 *
 * @author luoan
 * @since 2023/10/25
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class ResourceConfig {
    private final CorsFilter corsFilter;
    private final CustomSecurityProperties customSecurityProperties;
    private final BasicAccessTokenResponseClient accessTokenResponseClient;
    private final RedisSecurityContextRepository redisSecurityContextRepository;
    private final BasicAuthorizationRequestResolver authorizationRequestResolver;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // 添加基础的认证配置
        SecurityUtils.applyBasicSecurity(http, corsFilter, customSecurityProperties, redisSecurityContextRepository);

        String[] ignore_uri = customSecurityProperties.getIgnoreUriList().toArray(String[]::new);
        System.out.println(Arrays.toString(ignore_uri));
        http.authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(ignore_uri).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> {
                    formLogin.loginPage(customSecurityProperties.getSignInPageUri());
                    if (UrlUtils.isAbsoluteUrl(customSecurityProperties.getSignInPageUri())) {
                        formLogin.successHandler(new LoginSuccessHandler());
                        formLogin.failureHandler(new LoginFailureHandler());
                    }
                });
        // oauth2
        http.oauth2Login(oauth2Login ->
                oauth2Login.loginPage(customSecurityProperties.getSignInPageUri())
                        .authorizationEndpoint(authorizationEndpointConfig -> authorizationEndpointConfig
                                .authorizationRequestResolver(authorizationRequestResolver)
                        )
                        .tokenEndpoint(tokenEndpointConfig -> tokenEndpointConfig
                                .accessTokenResponseClient(accessTokenResponseClient)
                        )
        );

        return http.build();
    }
}
