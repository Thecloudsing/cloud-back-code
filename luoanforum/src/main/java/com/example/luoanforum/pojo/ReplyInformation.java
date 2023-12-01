package com.example.luoanforum.pojo;

/**
 * @author 落扶苏
 * @version 1.1
 */
public class ReplyInformation {
//    id int primary key auto_increment,
//    content varchar(1500) not null default '',
//    replyDate timestamp not null default current_timestamp,
//    author int unsigned,
//    topic int unsigned
    private String id;
    private String content;
    private String replyDate;
    private String author;
    private String topic;
    private String respondent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate;
    }

    public String getRespondent() {
        return respondent;
    }

    public void setRespondent(String respondent) {
        this.respondent = respondent;
    }
}
