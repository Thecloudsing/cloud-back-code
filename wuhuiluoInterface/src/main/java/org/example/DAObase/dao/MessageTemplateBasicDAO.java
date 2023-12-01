package org.example.DAObase.dao;

import org.example.pojo.MessageTemplateInformation;

import java.util.List;

public interface MessageTemplateBasicDAO extends CommonBasicDAO {

    void addMessage(MessageTemplateInformation messageTemplateInformation);
    void delMessage(int id);
    void updateMessage(MessageTemplateInformation messageTemplateInformation);
    List<MessageTemplateInformation> selMessage(int page, int limit);
    List<MessageTemplateInformation> selLikeMessage(String like, String encode, String type, int page, int limit);
}
