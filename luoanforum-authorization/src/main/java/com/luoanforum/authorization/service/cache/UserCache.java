package com.luoanforum.authorization.service.cache;

import com.luoanforum.authorization.constant.RedisKey;
import com.luoanforum.authorization.domain.entity.UserInfo;
import com.luoanforum.authorization.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserCache {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private RedisKey redisKey;

    @Cacheable(value = "user", key = "redisKey.getRedisKey('user',#username)")
    public UserInfo getUserInfo(String username) {
        return userMapper.findByUsername(username);
    }

    @CacheEvict(value = "user", key = "redisKey.getRedisKey('user',#username)")
    public void removeUserInfo(String username) {}

}
