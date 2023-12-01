package com.dreams.oauth2.strategy;


import com.dreams.oauth2.domain.security.BasicOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 * oauth2 三方登录获取到的用户信息转换策略接口
 *
 * @author luoan
 */
public interface Oauth2UserConverterStrategy {

    /**
     * 将oauth2登录的认证信息转为 {@link BasicOAuth2User}
     *
     * @param oAuth2User oauth2登录获取的用户信息
     * @return 项目中的用户信息
     */
    BasicOAuth2User convert(OAuth2User oAuth2User);

}
