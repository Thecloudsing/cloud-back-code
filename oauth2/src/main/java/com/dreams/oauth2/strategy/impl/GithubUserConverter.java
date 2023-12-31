package com.dreams.oauth2.strategy.impl;

import com.dreams.oauth2.constant.SecurityConstants;
import com.dreams.oauth2.domain.security.BasicOAuth2User;
import com.dreams.oauth2.strategy.Oauth2UserConverterStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;


/**
 * 转换通过Github登录的用户信息
 *
 * @author luoan
 */
@RequiredArgsConstructor
@Component(SecurityConstants.THIRD_LOGIN_GITHUB)
public class GithubUserConverter implements Oauth2UserConverterStrategy {

    protected static final String LOGIN_TYPE = SecurityConstants.THIRD_LOGIN_GITHUB;
    private final GiteeUserConverter userConverter;

    @Override
    public BasicOAuth2User convert(OAuth2User oAuth2User) {
        // github与gitee目前所取字段一致，直接调用gitee的解析
        BasicOAuth2User basicOauth2User = userConverter.convert(oAuth2User);
        // 提取location
        Object location = oAuth2User.getAttributes().get("location");
        basicOauth2User.setLocation(location + "");
        // 设置登录类型
        basicOauth2User.setType(LOGIN_TYPE);
        return basicOauth2User;
    }
}
