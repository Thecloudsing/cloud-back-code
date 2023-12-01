package com.example.luoanforum.service;

import com.example.luoanforum.pojo.TopicInformation;

import java.util.List;

/**
 * @author 落扶苏
 * @version 1.1
 */
public interface TopicService {
    //添加动态
    String addDynamics(String author, String title, String content, String url, String picture_url);

    //删除动态 +验证
    String delDynamics(String topicId);

    //实现查看用户动态把动态和回复拼装
    //List<TopicInformation>
    List<TopicInformation> viewDynamicsAll(String page);

    //查看所有动态
    List<TopicInformation> viewDynamics(String author);

    //查看单条动态
    List<TopicInformation> singletViewDynamics(String topicId);

    int getLastPage(boolean ...to);
    int inspectPage(String page);
}
