package org.example.utils;

import org.example.service.ServiceException;

import java.lang.reflect.Field;
import java.util.Map;

public class Conversion {

    public static <T> T requestParams(Map<String, String[]> map, Class<T> tClass) {
        try {
            T t = tClass.newInstance();
            Field[] declaredFields = tClass.getDeclaredFields();
            for (Field field: declaredFields) {
                field.setAccessible(true);
                String[] strings = map.get(field.getName());
                if (strings == null)
                    continue;
                field.set(t, strings[0]);
            }
            return t;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ServiceException("params exception ---" + e.getMessage());
        }

    }
}
