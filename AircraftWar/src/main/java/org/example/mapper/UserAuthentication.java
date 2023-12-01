package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserAuthentication {

    @Select("select uid from user_authentication where account = #{account} and password = #{password}")
    Integer verification(@Param("account") String account,
                        @Param("password") String password);

    @Select("select uid from user_authentication where account = #{account}")
    Integer duplicateAccountChecking(String account);

    //根据旧密码改密码
    @Update("update user_authentication set password = #{new_password} where account = #{account} and password = #{old_password}")
    void updateAuthenticationPassword(@Param("account") String account,
                              @Param("old_password") String old_password,
                              @Param("new_password") String new_password);

    @Update("update user_authentication set password = #{new_password} where account = #{account}")
    void updateAuthenticationCode(@Param("account") String account,
                              @Param("new_password") String new_password);
    //忘记密码
    @Update("update user_authentication set password = #{new_password} where account = #{account}")
    void forget(@Param("account") String account,
                @Param("new_password") String password);

    @Insert("insert into user_authentication (uid, account, password) values (#{uid}, #{account}, #{password})")
    void addUser(@Param("uid") int uid,
                 @Param("account") String account,
                 @Param("password") String password);
}
