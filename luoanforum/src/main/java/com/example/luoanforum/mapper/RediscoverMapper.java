package com.example.luoanforum.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author 落扶苏
 * @version 1.1
 */

@Mapper
@Repository
public interface RediscoverMapper extends ManagementMapper {
    @Update("update user_account set password = #{newPwd} where id = #{uid}")
    //修改密码
    void changePassword(@Param("uid") String uid, @Param("newPwd") String newPwd);
}
