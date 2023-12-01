package org.example.service;

import org.example.pojo.MessageTemplateInformation;

import java.util.List;

public interface MessageTemplateService extends CommonService {

    List<MessageTemplateInformation> selectMessage();
//    List<StudentInformation> selectLikeStudent();

    List<MessageTemplateInformation> addMessage(MessageTemplateInformation messageTemplateInformation);
    List<MessageTemplateInformation> updateMessage(MessageTemplateInformation messageTemplateInformation);
    List<MessageTemplateInformation> delMessage(int id);

}
