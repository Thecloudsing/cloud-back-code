package org.example.controller;

import com.alibaba.fastjson.JSONObject;
import org.example.annotation.UserLoginToken;
import org.example.pojo.MessageTemplateInformation;
import org.example.pojo.MessageTemplateInformation;
import org.example.service.MessageTemplateService;
import org.example.utils.Conversion;
import org.example.utils.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MessageTemplateController {
    private MessageTemplateService messageTemplateService;

    private final Class<MessageTemplateInformation> m = MessageTemplateInformation.class;
    private Result<List<MessageTemplateInformation>> result;

    @UserLoginToken
    public void addMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MessageTemplateInformation messageTemplateInformation = Conversion.requestParams(request.getParameterMap(), m);
        result = Result.wrapResult(request, true, messageTemplateService, () -> Result.auto(messageTemplateService.addMessage(messageTemplateInformation)));
        response.getWriter().println(JSONObject.toJSONString(result));
    }

    @UserLoginToken
    public void delMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        result = Result.wrapResult(request, false, messageTemplateService, () -> Result.auto(messageTemplateService.delMessage(Integer.parseInt(id))));
        response.getWriter().println(JSONObject.toJSONString(result));
    }

    @UserLoginToken
    public void updateMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MessageTemplateInformation messageTemplateInformation = Conversion.requestParams(request.getParameterMap(), m);
        result = Result.wrapResult(request, false, messageTemplateService, () -> Result.auto(messageTemplateService.updateMessage(messageTemplateInformation)));
        response.getWriter().println(JSONObject.toJSONString(result));
    }

    @UserLoginToken
    public void selMessageList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        messageTemplateService.setLikeOtherParams(Conversion.requestParams(request.getParameterMap(), m));
        result = Result.wrapResult(request, false, messageTemplateService, () -> Result.auto(messageTemplateService.selectMessage()));
        response.getWriter().println(JSONObject.toJSONString(result));
    }
}
