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
public interface RegisterMapper extends ManagementMapper {

    @Update("insert into user_account (account, password) value (#{account}, #{pwd})")
    //注册账号
    void registerAccount(@Param("account") String account, @Param("pwd") String pwd);


    @Update("insert into user_information (uid, username, email, cell_phonenumber, headportrait_url) value (#{uid}, #{username}, #{email}, #{sms}, #{headportrait_url})")
    //注册用户
    void registerUser(@Param("uid") String uid,
                      @Param("username") String username,
                      @Param("email") String email,
                      @Param("sms") String sms,
                      @Param("headportrait_url") String url);
}
