package com.dreams.oauth2.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dreams.oauth2.domain.entity.Oauth2BasicUser;
import com.dreams.oauth2.domain.entity.Oauth2ThirdAccount;


/**
 * <p>
 * 基础用户信息表 服务类
 * </p>
 *
 * @author luoan
 * @since 2023/10/25
 */
public interface IOauth2BasicUserDao extends IService<Oauth2BasicUser> {

    /**
     * 生成用户信息
     *
     * @param thirdAccount 三方用户信息
     * @return 用户id
     */
    Integer saveByThirdAccount(Oauth2ThirdAccount thirdAccount);
    /**
     * 在Security中“username”就代表了用户登录时输入的账号，在重写该方法时它可以代表以下内容：账号、手机号、邮箱、姓名等
     * “username”在数据库中不一定非要是一样的列，它可以是手机号、邮箱，也可以都是，最主要的目的就是根据输入的内容获取到对应的用户信息，如下方所示
     * 通过传入的账号信息查询对应的用户信息
     *
     * @param username 账号信息
     * @return 用户信息
     */
    Oauth2BasicUser getBasicUserByUsername(String username);

}
