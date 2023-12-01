package com.dreams.oauth2.service.impl;

import com.dreams.oauth2.authorization.wechat.WechatUserRequestEntityConverter;
import com.dreams.oauth2.authorization.wechat.WechatUserResponseConverter;
import com.dreams.oauth2.dao.IOauth2BasicUserDao;
import com.dreams.oauth2.dao.IOauth2ThirdAccountDao;
import com.dreams.oauth2.domain.security.BasicOAuth2User;
import com.dreams.oauth2.exception.InvalidCaptchaException;
import com.dreams.oauth2.strategy.context.Oauth2UserConverterContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

/**
 * Description: 自定义三方oauth2登录获取用户信息服务
 *
 * @author luoan
 * @since 2023/10/27
 */

@Service
public class CustomOauth2UserService extends DefaultOAuth2UserService {
    private final IOauth2ThirdAccountDao thirdAccountDao;
    private final Oauth2UserConverterContext userConverterContext;

    public CustomOauth2UserService(
            IOauth2ThirdAccountDao thirdAccountDao,
            Oauth2UserConverterContext userConverterContext
    ) {
        this.thirdAccountDao = thirdAccountDao;
        this.userConverterContext = userConverterContext;
        // 初始化时添加微信用户信息请求处理，oidcUserService本质上是调用该类获取用户信息的，不用添加
        super.setRequestEntityConverter(new WechatUserRequestEntityConverter());
        // 设置用户信息转换器
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());
        List<HttpMessageConverter<?>> messageConverters = List.of(
                new StringHttpMessageConverter(),
                // 获取微信用户信息时使其支持“text/plain”
                new WechatUserResponseConverter(),
                new ResourceHttpMessageConverter(),
                new ByteArrayHttpMessageConverter(),
                new AllEncompassingFormHttpMessageConverter()
        );
        restTemplate.setMessageConverters(messageConverters);
        super.setRestOperations(restTemplate);
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        try {
            OAuth2User oAuth2User = super.loadUser(userRequest);

            // 转为项目中的三方用户信息
            BasicOAuth2User basicOAuth2User = userConverterContext.convert(userRequest, oAuth2User);

            // 检查并且保存用户信息
            thirdAccountDao.checkAndSaveUser(basicOAuth2User);

            return basicOAuth2User;
        } catch (Exception e) {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            if (requestAttributes == null) {
                throw new InvalidCaptchaException("Failed to get the current request.");
            }
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            request.getSession(Boolean.FALSE).setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, e);
            throw new AuthenticationServiceException(e.getMessage(), e);
        }
    }
}
