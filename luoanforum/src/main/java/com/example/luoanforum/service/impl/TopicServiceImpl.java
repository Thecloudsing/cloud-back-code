package com.example.luoanforum.service.impl;


import com.example.luoanforum.mapper.TopicMapper;
import com.example.luoanforum.pojo.TopicInformation;
import com.example.luoanforum.service.TopicService;
import com.example.luoanforum.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 落扶苏
 * @version 1.1
 */

@Service("topicService")
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicMapper topicMapper;

    @Value("${default-limit}")
    private int limit;
    private Integer topicCount = null;
    private Integer topicLastPage = null;

    @Override
    public String addDynamics(String author, String title, String content, String url, String picture_url) {
        if (StringUtil.isEmpty(url))
            url = "false";
        topicMapper.publishingNews(author,title,content,url,picture_url);
        getLastPage(true);
        return "success:";
    }

    @Override
    public String delDynamics(String topicId) {
        topicMapper.deleteDynamic(topicId);
        getLastPage(true);
        return "success";
    }

    @Override
    public List<TopicInformation> viewDynamicsAll(String page) {
        int tempPage = inspectPage(page);
        List<TopicInformation> topicInformations = topicMapper.viewDynamicAll((tempPage-1)*limit,limit);
        for (TopicInformation topicInformation: topicInformations) {
            topicInformation.setReplyInformationList(topicMapper.viewReply(topicInformation.getId()));
        }
        return topicInformations;
    }

    @Override
    public List<TopicInformation> viewDynamics(String author) {
        List<TopicInformation> topicInformations = topicMapper.viewDynamics(author);
        for (TopicInformation topicInformation: topicInformations)
            topicInformation.setReplyInformationList(topicMapper.viewReply(topicInformation.getId()));
        return topicInformations;
    }

    @Override
    public List<TopicInformation> singletViewDynamics(String topicId) {
        List<TopicInformation> topicInformations = topicMapper.singleViewDynamic(topicId);
        for (TopicInformation topicInformation: topicInformations)
            topicInformation.setReplyInformationList(topicMapper.viewReply(topicInformation.getId()));
        return topicInformations;
    }

    @Override
    public int getLastPage(boolean ...to) {
        boolean implement = to.length != 0 && to[0];
        topicCount = topicMapper.getTopicCount();
        if ((implement || ((topicLastPage == null || topicLastPage == 0)) && (topicCount != null) && topicCount != 0)) {
            topicLastPage = topicCount / limit;
            topicLastPage = topicLastPage != 0 && (topicCount % limit) == 0 ? topicLastPage : topicLastPage + 1;
        }
        return topicLastPage == null ? 0 : topicLastPage;
    }

    @Override
    public int inspectPage(String page) {
        getLastPage();
        int tempPage = 1;
        if (StringUtil.isEmpty(page)) return tempPage;
        try {
            tempPage = Integer.parseInt(page);
        } catch (Exception e) {return 1;}
        if (tempPage > topicLastPage) tempPage = topicLastPage;
        if (tempPage < 1) tempPage = 1;
        return tempPage;
    }
}
