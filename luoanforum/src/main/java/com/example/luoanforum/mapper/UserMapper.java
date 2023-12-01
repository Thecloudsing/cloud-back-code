package com.example.luoanforum.mapper;

import com.example.luoanforum.pojo.UserInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author 落扶苏
 * @version 1.1
 */

@Mapper
@Repository
public interface UserMapper {
    @Update("update user_information set username = #{username} , age = #{age} , gender = #{gender} , birthday = #{birthday} where uid = #{uid}")
    //修改用户基本数据
    void userModificationInformation(@Param("uid") String uid,
                                     @Param("username")String username,
                                     @Param("age") String age,
                                     @Param("gender") String gender,
                                     @Param("birthday") String birthday);

    @Update("update user_information set email = #{email} where uid = #{uid}")
    //修改用户邮箱
    void userModificationEmail(@Param("uid") String uid, @Param("email") String email);

    @Update("update user_information set cell_phonenumber = #{cell} where uid = #{uid}")
    //修改用户手机号
    void userModificationSms(@Param("uid") String uid, @Param("cell") String cell);

    @Update("update user_information set headportrait_url = #{url} where uid = #{uid}")
    //修改用户头像地址
    void userModifiesAvatar(@Param("uid") String uid, @Param("url") String url);

    @Update("update user_information set fabulous = fabulous + 1 where uid = #{uid}")
    //用户点赞
    void userLikes(@Param("uid") String uid);

    @Select("select * from user_information where uid = #{uid}")
    //返回用户个人信息
    UserInformation returnUserInformation(@Param("uid") String uid);
}
