package org.example.DAObase.dao.impl;

import org.example.DAObase.dao.CommonBasicAbstract;
import org.example.DAObase.dao.MessageTemplateBasicDAO;
import org.example.pojo.MessageTemplateInformation;

import java.util.List;

public class MessageTemplateBasicDAOImpl extends CommonBasicAbstract implements MessageTemplateBasicDAO {

    public MessageTemplateBasicDAOImpl() {
        super();
        super.setTableName("message_template");
    }
    @Override
    public void addMessage(MessageTemplateInformation messageTemplateInformation) {
        super.getDbUtils().updateRecords("insert into message_template (title, encode, `context`, type, apply) " +
                        "values (?, ?, ?, ?, ?)",
                messageTemplateInformation.getTitle(),
                messageTemplateInformation.getEncode(),
                messageTemplateInformation.getContext(),
                messageTemplateInformation.getType(),
                messageTemplateInformation.getApply());
    }

    @Override
    public void delMessage(int id) {
        super.getDbUtils().updateRecords("delete from message_template where id = ?", id);
    }

    @Override
    public void updateMessage(MessageTemplateInformation messageTemplateInformation) {
        super.getDbUtils().updateRecords("update message_template set title = ?, " +
                        "encode = ?, " +
                        "`context` = ?, " +
                        "type = ?, " +
                        "apply = ? " +
                        "where id = ?",
                messageTemplateInformation.getTitle(),
                messageTemplateInformation.getEncode(),
                messageTemplateInformation.getContext(),
                messageTemplateInformation.getType(),
                messageTemplateInformation.getApply(),
                messageTemplateInformation.getId());
    }

    @Override
    public List<MessageTemplateInformation> selMessage(int page, int limit) {
        page = (page-1)*limit;
        return super.getDbUtils().queryMultipleRowsOfRecords(MessageTemplateInformation.class,
                "select * from message_template limit ? , ?", page, limit);
    }

    @Override
    public List<MessageTemplateInformation> selLikeMessage(String like, String encode, String type, int page, int limit) {
        page = (page-1)*limit;
        return super.getDbUtils().queryMultipleRowsOfRecords(MessageTemplateInformation.class,
                "select * from message_template where title like '%" + like + "%' and encode = ? and type = ? limit ? , ?", encode, type, page, limit);
    }
}
