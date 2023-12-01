package com.dreams.oauth2.controller;

import com.dreams.oauth2.constant.SecurityConstants;
import com.dreams.oauth2.domain.Result;
import com.dreams.oauth2.domain.response.Oauth2UserinfoResult;
import com.dreams.oauth2.domain.response.gitee.GetEmailResult;
import com.dreams.oauth2.exchange.GiteeExchange;
import com.dreams.oauth2.service.impl.IOauth2BasicUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Description:
 *
 * @author luoan
 * @since 2023/10/28
 */
@RestController
@RequiredArgsConstructor
public class UserController {
    private final GiteeExchange giteeExchange;

    private final IOauth2BasicUserService basicUserService;

    @GetMapping("/user")
    public Oauth2UserinfoResult user() {

        return basicUserService.getLoginUserInfo();
    }

    @GetMapping("/gitee/emails")
    public Result<List<GetEmailResult>> getGiteeEmails() {
        // 获取当前登录用户信息
        Oauth2UserinfoResult loginUserInfo = basicUserService.getLoginUserInfo();

        // 获取用户来源
        String sourceFrom = loginUserInfo.getSourceFrom();

        if (!Objects.equals(sourceFrom, SecurityConstants.THIRD_LOGIN_GITEE)) {
            return Result.fail("请使用Gitee登录后使用.");
        }

        if (loginUserInfo.getCredentialsExpiresAt().isBefore(LocalDateTime.now())) {
            return Result.fail("Gitee的token已经过期，请重新获取.");
        }

        return Result.success(giteeExchange.emails(loginUserInfo.getCredentials()));
    }
}
