package com.dreams.oauth2.service.impl;

import com.dreams.oauth2.dao.IOauth2ThirdAccountDao;
import com.dreams.oauth2.domain.security.BasicOAuth2User;
import com.dreams.oauth2.domain.security.BasicOidcUser;
import com.dreams.oauth2.strategy.context.Oauth2UserConverterContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Description: 自定义三方oidc登录用户信息服务
 *
 * @author luoan
 * @since 2023/10/27
 */
@Service
@RequiredArgsConstructor
public class CustomOidcUserService extends OidcUserService {
    private final IOauth2ThirdAccountDao thirdAccountDao;
    private final Oauth2UserConverterContext userConverterContext;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        // 获取三方用户信息
        OidcUser oidcUser = super.loadUser(userRequest);
        // 转为项目中的三方用户信息
        BasicOAuth2User basicOauth2User = userConverterContext.convert(userRequest, oidcUser);
        // 检查用户信息
        thirdAccountDao.checkAndSaveUser(basicOauth2User);

        // 重新生成oidcUser
        if (StringUtils.hasText(basicOauth2User.getNameAttributeKey())) {
            return new BasicOidcUser(oidcUser, oidcUser.getIdToken(), oidcUser.getUserInfo(), basicOauth2User.getNameAttributeKey());
        }
        return new BasicOidcUser(oidcUser, oidcUser.getIdToken(), oidcUser.getUserInfo());
    }
}
