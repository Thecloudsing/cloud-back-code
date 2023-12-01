package com.example.luoanforum.mapper;

import com.example.luoanforum.pojo.ReplyInformation;
import com.example.luoanforum.pojo.TopicInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 落扶苏
 * @version 1.1
 */

@Mapper
@Repository
public interface TopicMapper {
    @Update("insert into topic (title,content,headportrait_url,author,picture_url) value (#{title},#{content},#{url},#{author},#{picture_url})")
    //发布动态
    void publishingNews(@Param("author") String author,
                        @Param("title") String title,
                        @Param("content") String content,
                        @Param("url") String url,
                        @Param("picture_url") String picture_url);

    @Update("delete from topic where id = #{topicId}")
    //删除动态
    void deleteDynamic(@Param("topicId") String topicId);

    @Select("select * from topic where author = #{author}")
    //查看用户动态
    List<TopicInformation> viewDynamics(@Param("author") String author);

    @Select("select * from topic where id = #{topicId}")
    //查看单条动态
    List<TopicInformation> singleViewDynamic(@Param("topicId") String topicId);

    @Select("select * from topic limit #{page}, #{limit}")
    //查看所有
    List<TopicInformation> viewDynamicAll(int page, int limit);

    @Select("select * from reply where topic = #{topicId}")
    //查看回复
    List<ReplyInformation> viewReply(@Param("topicId") String topicId);

    @Select("select count(*) from topic")
    Integer getTopicCount();
}
