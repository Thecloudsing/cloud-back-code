package com.example.luoanforum.service.impl;


import com.example.luoanforum.mapper.ReplyMapper;
import com.example.luoanforum.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @author 落扶苏
 * @version 1.1
 */
@Service("replyService")
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public void addReply(String author, String content, String topicId, String respondent) {
        replyMapper.addReply(author,content,topicId,respondent);
    }

    @Override
    public void delReply(String replyId) {
        replyMapper.deleteReply(replyId);
    }
}
