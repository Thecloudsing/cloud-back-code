package com.example.luoanforum.util;//package com.luoan.util;
//
//import com.alibaba.fastjson.JSONObject;
//import com.luoan.pojo.TopicInformation;
//import com.luoan.service.ReplyService;
//import com.luoan.service.TopicService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author 落扶苏
// * @version 1.1
// */
//
//@Controller
//public class ZoneController {
//
//    @Autowired
//    private ReplyService replyService;
//    @Autowired
//    private TopicService topicService;
//
//    public void addReply(HttpServletRequest req, HttpServletResponse resp) {
//        String topicId = req.getParameter("topicId");
//        String author = req.getParameter("author");
//        String content = req.getParameter("content");
//        String respondent = req.getParameter("respondent");
//        replyService.addReply(author, content, topicId, respondent);
//    }
//
//    public void addDynamic(HttpServletRequest req, HttpServletResponse resp) {
//        String title = req.getParameter("title");
//        String content = req.getParameter("content");
//        String author = req.getParameter("author");
//        String headPortrait_url = req.getParameter("headPortrait_url");
//        topicService.addDynamics(author,title,content,headPortrait_url);
//    }
//
//    public void MyDynamics(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String author = req.getParameter("author");
//        List<TopicInformation> topicInformations = topicService.viewDynamics(author);
////        String json = JSONObject.fromObject(topicInformations).toString();
//        Map<String,Object> json = new HashMap<>();
//        json.put("topicInformation",topicInformations);
//        String s = JSONObject.toJSON(json).toString();
//        resp.getWriter().println(s);
//    }
//
//    public void delDynamic(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String topicId = req.getParameter("topicId");
//        String s = topicService.delDynamics(topicId);
//        resp.getWriter().println(s);
//    }
//}
