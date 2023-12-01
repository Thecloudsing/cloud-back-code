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
public interface ReplyMapper {
    @Update("delete from reply where topic = #{topicId}")
    //删除动态连带删除回复
    void deleteAllDynamicReplies(@Param("topicId") String topicId);

    @Update("delete from reply where id = #{replyId}")
    //删除回复
    void deleteReply(@Param("replyId") String replyId);

    @Update("insert into reply (author, content, topic, respondent) value (#{author}, #{content}, #{topicId}, #{respondent})")
    //添加回复
    void addReply(@Param("author") String author, @Param("content") String content, @Param("topicId") String topicId, @Param("respondent") String respondent);
}
