package com.dreams.oauth2.config;

import com.dreams.oauth2.exchange.GiteeExchange;
import com.dreams.oauth2.exchange.ProjectExchange;
import com.dreams.oauth2.property.CustomSecurityProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * Description: Http Interface注入ioc配置
 *
 * @author luoan
 * @since 2023/10/25
 */
@Configuration
@RequiredArgsConstructor
public class ExchangeBeanConfig {
    private final CustomSecurityProperties customSecurityProperties;

    /**
     * 注入ProjectExchange
     *
     * @return ProjectExchange
     */
    @Bean
    public ProjectExchange projectExchange() {
        WebClient webClient = WebClient
                .builder()
                .baseUrl(customSecurityProperties.getIssuerUrl())
                .build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build();
        return httpServiceProxyFactory.createClient(ProjectExchange.class);
    }

    /**
     * 注入Gitee Exchange
     *
     * @return GiteeExchange
     */
    @Bean
    public GiteeExchange giteeExchange() {
        String giteeUrl = "https://gitee.com";
        WebClient webClient = WebClient.builder().baseUrl(giteeUrl).build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build();
        return httpServiceProxyFactory.createClient(GiteeExchange.class);
    }
}
