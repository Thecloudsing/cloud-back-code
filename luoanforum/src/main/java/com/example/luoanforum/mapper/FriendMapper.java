package com.example.luoanforum.mapper;

import com.example.luoanforum.pojo.UserInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 落扶苏
 * @version 1.1
 */

@Mapper
@Repository
public interface FriendMapper extends ManagementMapper {

    @Update("insert into friend (uid, fid) value (#{uid}, #{fid})")
    //添加朋友
    void addFriend(@Param("uid") String uid, @Param("fid") String fid);

    @Update("delete from friend where uid = #{uid} and fid = #{fid}")
    //删除朋友
    void deleteFriend(@Param("uid") String uid, @Param("fid") String fid);

    @Update("select * from user_information where uid = #{uid}")
    //返回朋友列表
    List<UserInformation> returnFriendList(@Param("uid") String uid);
}
