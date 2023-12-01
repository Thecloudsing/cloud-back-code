package com.example.luoanforum.mapper;


import com.example.luoanforum.pojo.AccountPwdCookie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


/**
 * @author 落扶苏
 * @version 1.1
 */

@Mapper
@Repository
public interface LoginMapper extends ManagementMapper {
    //List<PersonalInformation> selectPersonAll();

    @Select("select id from user_account where account = #{account} and password = #{password}")
    String accountQueryUserExistence(@Param("account") String user, @Param("password") String password);


    @Select("select account from user_account where id = #{uid} and password = #{password}")
    String otherQueryUserExistence(@Param("uid") String uid, @Param("password") String password);

    @Select("select * from user_account where id = #{uid}")
    AccountPwdCookie passwordQueryUserExistenceById(@Param("uid") String uid);

    @Select("select* from user_account where account = #{account}")
    AccountPwdCookie passwordQueryUserExistenceByAccount(@Param("account") String account);


}
