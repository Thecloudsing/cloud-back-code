package com.example.luoanforum.controller;

import com.alibaba.fastjson.JSONObject;

import com.example.luoanforum.pojo.JsonData;
import com.example.luoanforum.pojo.ReplyInformation;
import com.example.luoanforum.pojo.SignRegisterJson;
import com.example.luoanforum.pojo.TopicInformation;
import com.example.luoanforum.service.ReplyService;
import com.example.luoanforum.service.TopicService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 落扶苏
 * @version 1.1
 */

@RestController
@RequestMapping(produces = {"text/html;charset=UTF-8;", "application/json;"})
public class ZoneController {

    @Value("${host}")
    private String host;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private TopicService topicService;

    @PostMapping("/addReply")
    public void addReply(@RequestBody ReplyInformation reply, HttpServletResponse response) throws IOException {
        String topic = reply.getTopic();
        String author = reply.getAuthor();
        String content = reply.getContent();
        String respondent = reply.getRespondent();
        replyService.addReply(author, content, topic, respondent);
        response.getWriter().println(new JsonData(true));
    }

    @PostMapping("/addDynamic")
    public void addDynamic(@RequestBody MultipartFile[] files,
                           @RequestParam("uid") String uid,
                           @RequestParam("title") String title,
                           @RequestParam("content") String content,
                           @RequestParam("author") String author,
                           @RequestParam("headportrait_url") String headportrait_url,
                           HttpServletResponse response) throws IOException {
//        String uid = topic.getUid();
//        String title = topic.getTitle();
//        String content = topic.getContent();
//        String author = topic.getAuthor();
//        String headportrait_url = topic.getHeadportrait_url();
        String picture_url = this.uploadFile(files, uid);
        picture_url = Objects.equals(picture_url,"") ? "[]" : picture_url;
        topicService.addDynamics(author,title,content,headportrait_url,picture_url);
        response.getWriter().println(new JsonData(true));
    }

    @GetMapping("/myDynamics/{author}")
    public void myDynamics(HttpServletResponse resp, @PathVariable String author) throws IOException {
        List<TopicInformation> topicInformations = topicService.viewDynamics(author);
//        String json = JSONObject.fromObject(topicInformations).toString();
        JsonData jsonData = new JsonData();
        jsonData.putData("topicInformation",topicInformations);
        String s = JSONObject.toJSON(jsonData).toString();
        jsonData.setSuccess();
        resp.getWriter().println(s);
    }

    @DeleteMapping("/delDynamic/{topicId}")
    public void delDynamic(@PathVariable String topicId, HttpServletResponse response) throws IOException {
        String s = topicService.delDynamics(topicId);
        response.getWriter().println(new JsonData(true));
    }

    @PostMapping("/xxx")
    public void xxx(@RequestBody SignRegisterJson signRegisterJson) {
        System.out.println(signRegisterJson);
    }

    @PostMapping("/yyy")
    public String yyy(@RequestBody String message) {
        System.out.println(message);
        return "1/xxx.html";
    }

    @PostMapping("/zzz")
    public String zzz(@RequestBody MultipartFile[] files, @RequestParam("uid") String uid) throws IOException {
        System.out.println(files);
//        String uid = "1";
        for (MultipartFile file : files) {
            InputStream inputStream = file.getInputStream();
            String fileName =  file.getOriginalFilename();
            File file1 = new File(basePath + "user\\" + uid + "\\" + fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(file1);
            IOUtils.copy(inputStream,fileOutputStream);
            fileOutputStream.flush();
            inputStream.close();
            fileOutputStream.close();
        }

//        return "static\\" + fileName;
        return "";
    }

    @Value("${static-base-path}")
    private String basePath;
    @Value("${separator}")
    public String separator;
    private String uploadFile(MultipartFile[] files, String uid) throws IOException {
        int i = 0;
        String[] strings = null;
        if (files != null && files.length != 0) {
            uid = uid == null ? "default" : uid;
            strings = new String[files.length];
            for (MultipartFile file : files) {
                InputStream inputStream = file.getInputStream();
                String fileName = file.getOriginalFilename();
                File outDirectory = new File(setPathSeparator(separator,basePath,"user",uid));
                if (!outDirectory.isDirectory()) outDirectory.mkdir();
                fileName = fileName == null ? file.getName().replaceAll("\\s*", "") : fileName.replaceAll("\\s*", "");
                File outFile = new File(outDirectory, fileName);
                FileOutputStream fileOutputStream = new FileOutputStream(outFile);
                IOUtils.copy(inputStream, fileOutputStream);
                fileOutputStream.flush();
                inputStream.close();
                fileOutputStream.close();
                strings[i] = setPathSeparator("/",host,"user",uid,fileName);
                i++;
            }
        }
        return strings == null ? "" : JSONObject.toJSON(strings).toString();
    }

    private final StringBuffer pathSeparator = new StringBuffer();

    private String setPathSeparator(String separator, String ...params) {
        int len = pathSeparator.length();
        if (len != 0) {
            pathSeparator.delete(0,len);
        }
        for (String param : params) {
            Pattern compile = Pattern.compile("^" + separator + "*(.*?)" + separator + "*$");
            Matcher matcher = compile.matcher(param);
            if (matcher.find()) pathSeparator.append(matcher.group(1));
            else pathSeparator.append(param);
            pathSeparator.append(separator);
        }
        len = pathSeparator.length();
        int index = -1;
        if ((index = pathSeparator.lastIndexOf(separator)) == len-1)
            pathSeparator.delete(index,len);
//        pathSeparator.delete(0,len);
        return pathSeparator.toString();
    }

}
