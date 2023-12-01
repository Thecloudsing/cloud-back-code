package com.example.luoanforum.mapper;

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
public interface ManagementMapper {

    //查询账号是否存在
    @Select("select id from user_account where account = #{account}")
    String queryWhetherTheAccountIsRegistered(@Param("account") String account);
    @Select("select uid from user_information where email = #{email}")
    String queryWhetherTheEmailIsRegistered(@Param("email") String email);

    @Select("select uid from user_information where cell_phonenumber = #{sms}")
    String queryWhetherTheSmsIsRegistered(@Param("sms") String sms);


    //获取uid
    @Select("select uid from user_information where cell_phonenumber = #{sms}")
    String getSmsUid(@Param("sms") String sms);
    @Select("select uid from user_information where email = #{email}")
    String getEmailUid(@Param("email") String email);
    @Select("select id from user_account where account = #{account} and password = #{pwd}")
    String getAccountUid(@Param("account") String account, @Param("pwd") String pwd);
}
