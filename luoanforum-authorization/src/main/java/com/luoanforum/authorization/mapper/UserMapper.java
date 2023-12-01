package com.luoanforum.authorization.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luoanforum.authorization.domain.entity.UserInfo;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<UserInfo> {
    @Select("select * from sys_user where username = #{username}")
    UserInfo findByUsername(String username);
}
