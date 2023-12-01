package com.dreams.oauth2.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dreams.oauth2.domain.entity.Oauth2ThirdAccount;
import com.dreams.oauth2.domain.security.BasicOAuth2User;


/**
 * <p>
 * 三方登录账户信息表 服务类
 * </p>
 *
 * @author luoan
 * @since 2023/10/25
 */
public interface IOauth2ThirdAccountDao extends IService<Oauth2ThirdAccount> {

    /**
     * 检查是否存在该用户信息，不存在则保存，暂时不做关联基础用户信息，由前端引导完善/关联基础用户信息
     *
     * @param basicOauth2User 用户信息
     */
    void checkAndSaveUser(BasicOAuth2User basicOauth2User);

}
