package org.example.service.impl;

import org.example.DAObase.dao.MessageTemplateBasicDAO;
import org.example.pojo.MessageTemplateInformation;
import org.example.service.CommonAbstract;
import org.example.service.MessageTemplateService;
import org.example.service.ServiceException;
import org.example.utils.StringUtil;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MessageTemplateServiceImpl extends CommonAbstract implements MessageTemplateService {

    private MessageTemplateBasicDAO messageTemplateBasicDAO;

    @Override
    public int getTotalCount() {
        if (pageCount == null || loop)
//            pageCount = like == null ?
//                    messageTemplateBasicDAO.getCount() :
//                     messageTemplateBasicDAO.getCount(" where title like '%" + like + "%'");

            if (like == null)
                pageCount = messageTemplateBasicDAO.getCount();
            else {
                AtomicReference<String> encode = new AtomicReference<>();
                AtomicReference<String> type = new AtomicReference<>();
                try {
                    callbackParams((paramMap -> {
                        if (paramMap.get("encode") == null) throw new RuntimeException();
                        if (paramMap.get("type") == null) throw new RuntimeException();
                        encode.set(paramMap.get("encode"));
                        type.set(paramMap.get("type"));
                    }));
                } catch (Exception e) {
                    throw new ServiceException(ServiceException.ILLEGAL_REQUEST_PARAMETER);
                }
                pageCount = messageTemplateBasicDAO.getCount(" where title like '%" + like + "%' " +
                        "and encode = '" + encode.get() +"' and type = '" + type.get() + "'");
            }
        return pageCount;
    }

    @Override
    public List<MessageTemplateInformation> selectMessage() {
        if (like == null)
            return messageTemplateBasicDAO.selMessage(currentPage, limit);
        AtomicReference<String> encode = new AtomicReference<>();
        AtomicReference<String> type = new AtomicReference<>();
        try {
            callbackParams((paramMap -> {
                if (paramMap.get("encode") == null) throw new RuntimeException();
                if (paramMap.get("type") == null) throw new RuntimeException();
                encode.set(paramMap.get("encode"));
                type.set(paramMap.get("type"));
            }));
        } catch (Exception e) {
            throw new ServiceException(ServiceException.ILLEGAL_REQUEST_PARAMETER);
        }
        return messageTemplateBasicDAO.selLikeMessage(like, encode.get(), type.get(), currentPage, limit);
    }

    @Override
    public List<MessageTemplateInformation> addMessage(MessageTemplateInformation messageTemplateInformation) {
        if (StringUtil.isEmptyFilters(messageTemplateInformation, "id", "created_at"))
            throw new ServiceException(ServiceException.REQUEST_PARAMETER_NULL_VALUE);
        messageTemplateBasicDAO.addMessage(messageTemplateInformation);
        return selectMessage();
    }

    @Override
    public List<MessageTemplateInformation> updateMessage(MessageTemplateInformation messageTemplateInformation) {
        if (StringUtil.isEmptyFilters(messageTemplateInformation, "created_at"))
            throw new ServiceException(ServiceException.REQUEST_PARAMETER_NULL_VALUE);
        messageTemplateBasicDAO.updateMessage(messageTemplateInformation);
        return selectMessage();
    }

    @Override
    public List<MessageTemplateInformation> delMessage(int id) {
        messageTemplateBasicDAO.delMessage(id);
        return selectMessage();
    }
}
