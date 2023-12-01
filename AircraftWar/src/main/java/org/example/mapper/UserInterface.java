package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserInterface {
    @Select("select * from user_interface where id = #{uid}")
    org.example.pojo.UserInterface  getUser_id(@Param("uid") int uid);
    @Select("select * from user_interface where uuid = #{uuid}")
    org.example.pojo.UserInterface  getUser_uuid(@Param("uuid") String uuid);


    //@Update("update user_interface set integral = integral + #{integral} where id = #{uid}")
    @Update("update user_interface set integral = case when ((integral + (#{integral})) < 0) then 0 else integral + (#{integral}) end where id = #{uid}")
    void setIntegral(@Param("uid") int uid,
                     @Param("integral") int integral);

    @Insert("insert into user_interface (uuid, username) values (#{uuid}, #{username})")
    void addUser(@Param("uuid") String uuid,
                 @Param("username") String name);

}
