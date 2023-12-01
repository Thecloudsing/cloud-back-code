package com.dreams.oauth2.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreams.oauth2.dao.IOauth2BasicUserDao;
import com.dreams.oauth2.domain.entity.Oauth2BasicUser;
import com.dreams.oauth2.domain.entity.Oauth2ThirdAccount;
import com.dreams.oauth2.mapper.Oauth2BasicUserMapper;
import org.springframework.stereotype.Repository;


/**
 * <p>
 * 基础用户信息表 服务实现类
 * </p>
 *
 * @author luoan
 */
@Repository
public class Oauth2BasicUserDaoImpl extends ServiceImpl<Oauth2BasicUserMapper, Oauth2BasicUser> implements IOauth2BasicUserDao {
    @Override
    public Integer saveByThirdAccount(Oauth2ThirdAccount thirdAccount) {
        Oauth2BasicUser basicUser = new Oauth2BasicUser();
        basicUser.setName(thirdAccount.getName());
        basicUser.setAvatarUrl(thirdAccount.getAvatarUrl());
        basicUser.setDeleted(Boolean.FALSE);
        basicUser.setSourceFrom(thirdAccount.getType());
        this.save(basicUser);
        return basicUser.getId();
    }
    @Override
    public Oauth2BasicUser getBasicUserByUsername(String username) {
        LambdaQueryWrapper<Oauth2BasicUser> wrapper = Wrappers.lambdaQuery(Oauth2BasicUser.class)
                .or(o -> o.eq(Oauth2BasicUser::getEmail, username))
                .or(o -> o.eq(Oauth2BasicUser::getMobile, username))
                .or(o -> o.eq(Oauth2BasicUser::getAccount, username));
        return baseMapper.selectOne(wrapper);
    }
}
