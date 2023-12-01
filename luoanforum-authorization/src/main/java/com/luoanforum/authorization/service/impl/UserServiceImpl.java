package com.luoanforum.authorization.service.impl;

import com.luoanforum.authorization.domain.entity.UserInfo;
import com.luoanforum.authorization.mapper.UserMapper;
import com.luoanforum.authorization.service.UserService;
import com.luoanforum.authorization.service.cache.UserCache;
import com.luoanforum.common.utils.JsonUtils;
import jakarta.annotation.Resource;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserCache userCache;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询数据库，查到对应的用户
        UserInfo user = userCache.getUserInfo(username);

        // ... 做一些异常处理，没有找到用户之类的
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // 根据用户ID，查询用户的角色
//        List<Role> roles = authenticationMapper.findRoleByUserId(myUser.getId());
        // 添加角色
        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (Role role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
        // 构建 Security 的 User 对象
        return new User(user.getUsername(), user.getPassword(), authorities);
    }


    @Override
    public Map<String, Object> getUserInfo(String username) {
        String userinfoString = JsonUtils.toStr(userCache.getUserInfo(username));
        return JsonUtils.toObj(userinfoString, HashMap.class);
    }
}
