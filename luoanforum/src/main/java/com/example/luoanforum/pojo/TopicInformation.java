package com.example.luoanforum.pojo;

import com.alibaba.fastjson.JSONArray;

import java.util.List;

/**
 * @author 落扶苏
 * @version 1.1
 */
public class TopicInformation {
//    id int primary key auto_increment,
//    title varchar(255),
//    content varchar(1500) not null default '',
//    topicDate timestamp not null default current_timestamp,
//    author int unsigned
    private  String id;
    private String uid;
    private  String title;
    private  String content;
    private  String topicDate;
    private  String author;
    private String[] pictureUrl;
    private String headportraitUrl;
    private List<ReplyInformation> replyInformationList;

    public String[] getPicture_url() {
        return pictureUrl;
    }

    public void setPictureUrl(String picture_url) {
//        String s = JSONArray.fromObject(strings).toString();
        List<String> urls = JSONArray.parseArray(picture_url, String.class);
        String[] picture_urls = new String[urls.size()];
        for (int i = 0; i < urls.size(); i++) {
            picture_urls[i] = urls.get(i);
        }
        this.pictureUrl = picture_urls;
    }

    public List<ReplyInformation> getReplyInformationList() {
        return replyInformationList;
    }

    public void setReplyInformationList(List<ReplyInformation> replyInformationList) {
        this.replyInformationList = replyInformationList;
    }

    public String getHeadportrait_url() {
        return headportraitUrl;
    }

    public void setHeadportraitUrl(String headportrait_url) {
        this.headportraitUrl = headportrait_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTopicDate() {
        return topicDate;
    }

    public void setTopicDate(String topicDate) {
        this.topicDate = topicDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String  author) {
        this.author = author;
    }
}
