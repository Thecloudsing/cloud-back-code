package com.dreams.oauth2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dreams.oauth2.constant.SecurityConstants;
import com.dreams.oauth2.dao.IOauth2BasicUserDao;
import com.dreams.oauth2.domain.entity.*;
import com.dreams.oauth2.domain.response.Oauth2UserinfoResult;
import com.dreams.oauth2.domain.security.CustomGrantedAuthority;
import com.dreams.oauth2.mapper.Oauth2ThirdAccountMapper;
import com.dreams.oauth2.mapper.SysAuthorityMapper;
import com.dreams.oauth2.mapper.SysRoleAuthorityMapper;
import com.dreams.oauth2.mapper.SysUserRoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author luoan
 * @since 2023/10/27
 */
@Service
@RequiredArgsConstructor
public class IOauth2BasicUserService implements UserDetailsService {

    private final IOauth2BasicUserDao basicUserDao;
    private final SysUserRoleMapper sysUserRoleMapper;
    private final SysAuthorityMapper sysAuthorityMapper;
    private final SysRoleAuthorityMapper sysRoleAuthorityMapper;
    private final Oauth2ThirdAccountMapper thirdAccountMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 在Security中“username”就代表了用户登录时输入的账号，在重写该方法时它可以代表以下内容：账号、手机号、邮箱、姓名等
        // “username”在数据库中不一定非要是一样的列，它可以是手机号、邮箱，也可以都是，最主要的目的就是根据输入的内容获取到对应的用户信息，如下方所示
        // 通过传入的账号信息查询对应的用户信息
        Oauth2BasicUser basicUser = basicUserDao.getBasicUserByUsername(username);
        if (basicUser == null) {
            throw new UsernameNotFoundException("账号不存在");
        }

        // 通过用户角色关联表查询对应的角色
        List<SysUserRole> userRoles = sysUserRoleMapper.selectList(Wrappers.lambdaQuery(SysUserRole.class).eq(SysUserRole::getUserId, basicUser.getId()));
        List<Integer> rolesId = Optional.ofNullable(userRoles).orElse(Collections.emptyList()).stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        if (ObjectUtils.isEmpty(rolesId)) {
            return basicUser;
        }
        // 通过角色菜单关联表查出对应的菜单
        List<SysRoleAuthority> roleMenus = sysRoleAuthorityMapper.selectList(Wrappers.lambdaQuery(SysRoleAuthority.class).in(SysRoleAuthority::getRoleId, rolesId));
        List<Integer> menusId = Optional.ofNullable(roleMenus).orElse(Collections.emptyList()).stream().map(SysRoleAuthority::getAuthorityId).collect(Collectors.toList());
        if (ObjectUtils.isEmpty(menusId)) {
            return basicUser;
        }

        // 根据菜单ID查出菜单
        List<SysAuthority> menus = sysAuthorityMapper.selectBatchIds(menusId);
        Set<CustomGrantedAuthority> authorities = Optional.ofNullable(menus).orElse(Collections.emptyList()).stream().map(SysAuthority::getAuthority).map(CustomGrantedAuthority::new).collect(Collectors.toSet());
        basicUser.setAuthorities(authorities);
        return basicUser;
    }

    /**
     * 生成用户信息
     *
     * @param thirdAccount 三方用户信息
     * @return 用户id
     */
    public Integer saveByThirdAccount(Oauth2ThirdAccount thirdAccount) {
        Oauth2BasicUser basicUser = new Oauth2BasicUser();
        basicUser.setName(thirdAccount.getName());
        basicUser.setAvatarUrl(thirdAccount.getAvatarUrl());
        basicUser.setDeleted(Boolean.FALSE);
        basicUser.setSourceFrom(thirdAccount.getType());
        basicUserDao.save(basicUser);
        return basicUser.getId();
    }

    /**
     * 获取当前登录用户信息
     *
     * @return 用户信息
     */
    public Oauth2UserinfoResult getLoginUserInfo() {
        Oauth2UserinfoResult result = new Oauth2UserinfoResult();

        // 获取当前认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 其它非token方式获取用户信息
        if (!(authentication instanceof JwtAuthenticationToken jwtAuthenticationToken)) {
            BeanUtils.copyProperties(authentication.getPrincipal(), result);
            result.setSub(authentication.getName());
            return result;
        }

        // 获取jwt解析内容
        Jwt token = jwtAuthenticationToken.getToken();

        // 获取当前登录类型
        String loginType = token.getClaim(SecurityConstants.OAUTH_LOGIN_TYPE);
        // 获取用户唯一Id
        String uniqueId = token.getClaimAsString(SecurityConstants.TOKEN_UNIQUE_ID);
        // 基础用户信息id
        Integer basicUserId = null;

        // 获取Token中的权限列表
        List<String> claimAsStringList = token.getClaimAsStringList(SecurityConstants.AUTHORITIES_KEY);

        // 如果登录类型不为空则代表是三方登录，获取三方用户信息
        if (!ObjectUtils.isEmpty(loginType)) {
            // 根据三方登录类型与三方用户的唯一Id查询用户信息
            LambdaQueryWrapper<Oauth2ThirdAccount> wrapper = Wrappers.lambdaQuery(Oauth2ThirdAccount.class)
                    .eq(Oauth2ThirdAccount::getUniqueId, uniqueId)
                    .eq(Oauth2ThirdAccount::getType, loginType);
            Oauth2ThirdAccount oauth2ThirdAccount = thirdAccountMapper.selectOne(wrapper);
            if (oauth2ThirdAccount != null) {
                basicUserId = oauth2ThirdAccount.getUserId();
                // 复制三方用户信息
                BeanUtils.copyProperties(oauth2ThirdAccount, result);
            }
        } else {
            // 为空则代表是使用当前框架提供的登录接口登录的，转为基础用户信息
            basicUserId = Integer.parseInt(uniqueId);
        }

        if (basicUserId == null) {
            // 如果用户id为空，代表获取三方用户信息失败
            result.setSub(authentication.getName());
            return result;
        }

        // 查询基础用户信息
        Oauth2BasicUser basicUser = basicUserDao.getById(basicUserId);
        if (basicUser != null) {
            BeanUtils.copyProperties(basicUser, result);
        }

        // 填充权限信息
        if (!ObjectUtils.isEmpty(claimAsStringList)) {
            Set<CustomGrantedAuthority> authorities = claimAsStringList.stream()
                    .map(CustomGrantedAuthority::new)
                    .collect(Collectors.toSet());
            // 否则设置为token中获取的
            result.setAuthorities(authorities);
        }

        result.setSub(authentication.getName());
        return result;
    }
}
