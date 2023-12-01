package org.example.pojo;

import org.example.service.ServiceException;

import java.util.Arrays;

public class MessageTemplateInformation {
    private static final String[] EncodeEnum = {"UTF-8","UTF-16","ISO-8859-1","GBK","ASCII"};
    private static final String[] TypeEnum = {"text","richText","markdown"};
    private static final String[] ApplyEnum = {"0", "1"};
    private String id;
    private String title;
    private String encode;
    private String context;
    private String type;
    private String apply;
    private String created_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
//        if (Arrays.stream(EncodeEnum).noneMatch((s) -> s.equals(type)))
//            throw new ServiceException(ServiceException.ILLEGAL_REQUEST_PARAMETER);
        this.encode = encode;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
//        if (Arrays.stream(TypeEnum).noneMatch((s) -> s.equals(type)))
//            throw new ServiceException(ServiceException.ILLEGAL_REQUEST_PARAMETER);
        this.type = type;
    }

    public String getApply() {
        return apply;
    }

    public void setApply(String apply) {
//        if (Arrays.stream(ApplyEnum).noneMatch((s) -> s.equals(type)))
//            throw new ServiceException(ServiceException.ILLEGAL_REQUEST_PARAMETER);
        this.apply = apply;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
