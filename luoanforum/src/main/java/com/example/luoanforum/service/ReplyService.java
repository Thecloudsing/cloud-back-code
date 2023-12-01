package com.example.luoanforum.service;

/**
 * @author 落扶苏
 * @version 1.1
 */
public interface ReplyService {

    //添加回复
    void addReply(String uid, String content, String topicId, String respondent);


    //删除回复
    void delReply(String replyId);

}
