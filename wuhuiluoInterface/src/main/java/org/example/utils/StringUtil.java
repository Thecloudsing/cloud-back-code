package org.example.utils;

import java.lang.reflect.Field;
import java.util.Arrays;

public class StringUtil {
    //判断字符串是否为null或者""
    public static boolean isEmpty(String ...strings) {
        for (String str : strings) {
             if (str == null || "".equals(str))
                 return true;
        }
        return false;
    }
    public static boolean isNotEmpty(String ...strings){
        return !isEmpty(strings);
    }
    public static boolean isEmptyMatch(Object obj, String ...match) {
        return !isNotEmptyMatch(obj, match);
    }

    public static boolean isNotEmptyMatch(Object obj, String ...match) {
        if (obj ==  null) return false;
        Field[] declaredFields = obj.getClass().getDeclaredFields();

        try {
            for (Field field: declaredFields) {
                field.setAccessible(true);
                Object o = field.get(obj);

                if (Arrays.stream(match).anyMatch(s -> s.equals(field.getName())))
                    if (o instanceof String && o.equals("")) {
                        return false;
                    }
            }
        } catch (IllegalAccessException e) {
            return false;
        }
        return true;
    }

    public static boolean isEmptyFilters(Object obj, String ...filters) {
        return !isNotEmptyFilters(obj, filters);
    }

    public static boolean isNotEmptyFilters(Object obj, String ...filters) {
        if (obj ==  null) return false;
        Field[] declaredFields = obj.getClass().getDeclaredFields();

        try {
            for (Field field: declaredFields) {
                field.setAccessible(true);
                Object o = field.get(obj);

                if (Arrays.stream(filters).anyMatch(s -> s.equals(field.getName())))
                    continue;
                if (!(o instanceof String) && o != null) {
                   continue;
                }
                if (o != null && o.equals(""))
                    return false;
            }
        } catch (IllegalAccessException e) {
            return false;
        }
        return true;
    }
}
