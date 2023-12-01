package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.FriendLeaderboard;

import java.util.List;


public interface FriendList {
//    @Select("select friend_interface.id, username, integral from friend_interface, user_interface where user_interface.id = #{id} and fid = user_interface.id")
//    List<FriendLeaderboard> getFriendList(@Param("id") int id);

    @Select("select username, integral from user_interface order by integral desc")
    List<FriendLeaderboard> getFriendList();

    @Insert("insert into friend_interface (uid, fid) values (#{uid}, #{fid})")
    void addFriend(@Param("uid") int uid,
                   @Param("fid") int fid);

    @Delete("delete from friend_interface where uid = #{uid} and fid = #{fid}")
    void delFriend(@Param("uid") int uid,
                   @Param("fid") int fid);

}
