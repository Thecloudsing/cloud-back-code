package com.luoanforum.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Description: json工具类
 */
public class JsonUtils {
    private static final ObjectMapper jsonMapper = new ObjectMapper();

    /**
     * json字符串转对象
     * @param str json字符串
     * @param clz 对象类型
     * @return 对象
     * @param <T> 对象类型
     */

    public static <T> T toObj(String str, Class<T> clz) {
        try {
            return jsonMapper.readValue(str, clz);
        } catch (JsonProcessingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    /**
     * 对象转json字符串
     * @param t 对象
     * @return json字符串
     */
    public static String toStr(Object t) {
        try {
            return jsonMapper.writeValueAsString(t);
        } catch (Exception e) {
            throw new UnsupportedOperationException(e);
        }
    }
}
