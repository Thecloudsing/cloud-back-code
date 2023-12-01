package com.example.luoanforum.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 落扶苏
 * @version 1.1
 */
public class JsonData {
    private Integer code = 404;
    private String message = "无法访问";
    private Map<String,Object> data;

    public JsonData() {
        this.data = new HashMap<>();
    }

    public JsonData(boolean status) {
        this();
        if (status) this.setSuccess();
        else this.setError(null,null);
    }

    public void setSuccess() {
        this.code = 200;
        this.message = "请求成功";
    }

    public void setError(Integer code, String error) {
        if (code != null) this.code = code; else this.code = 500;
        if (error != null) this.message = "无法解决的问题\n" + error;
        else this.message = "服务器出现无法解决的问题\n";
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void putData(String key, Object value) {
        this.data.put(key,value);
    }
}
