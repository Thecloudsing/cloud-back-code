package org.example.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StreamData implements Serializable {
    private final long serialVersionUID = 145436432613L;

    private String msg;
    private int code;
    private final Map<String,Object> data = new HashMap<>();

    public StreamData(String status) {
        String[] split = status.split(":");
        if (split[0].equals("success"))
            this.code = StatusCode.SUCCESS.code;
        else if (split[0].equals("error"))
            this.code = StatusCode.ERROR.code;
        else
            this.code = StatusCode.OTHER.code;
        this.msg = split[1];
    }

    public StreamData() {
        not_found();
    }

    public StreamData(StatusCode statusCode, String msg) {
        this.code = statusCode.code;
        this.msg = msg;
    }

    public StreamData(String name, List<?> list) {
        if (list == null)
            not_found();
        else {
            addData(name,list);
            this.code = StatusCode.SUCCESS.code;
            this.msg = "获取成功";
        }
    }

    private void not_found() {
        this.code = StatusCode.NOT_FOUND.code;
        this.msg = "输入参数不合法";
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void addData(String name, Object obj) {
        this.data.put(name,obj);
    }
}
