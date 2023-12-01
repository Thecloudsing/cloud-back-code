package com.luoanforum.common.utils;

import com.luoanforum.common.exception.BusinessErrorEnum;
import com.luoanforum.common.exception.BusinessCustomException;
import com.luoanforum.common.exception.CommonErrorEnum;
import com.luoanforum.common.exception.ErrorEnum;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * 校验工具类
 */
public class AssertUtil {

    /**
     * 判断表达式是否为true，如果不为true，则抛出异常
     * @param expression 表达式
     * @param msg 异常信息
     */
    public static void isTrue(boolean expression, String msg) {
        if (!expression) {
            throwException(msg);
        }
    }

    /**
     * 判断表达式是否为true，如果不为true，则抛出异常
     * @param expression 表达式
     * @param errorEnum 异常枚举
     * @param args 异常信息参数
     */
    public static void isTrue(boolean expression, ErrorEnum errorEnum, Object... args) {
        if (!expression) {
            throwException(errorEnum, args);
        }
    }

    /**
     * 判断表达式是否为false，如果不为false，则抛出异常
     * @param expression 表达式
     * @param msg 异常信息
     */
    public static void isFalse(boolean expression, String msg) {
        if (expression) {
            throwException(msg);
        }
    }

    /**
     * 判断表达式是否为false，如果不为false，则抛出异常
     * @param expression 表达式
     * @param errorEnum 异常枚举
     * @param args 异常信息参数
     */
    public static void isFalse(boolean expression, ErrorEnum errorEnum, Object... args) {
        if (expression) {
            throwException(errorEnum, args);
        }
    }

    /**
     * 判断对象是否为空，如果为空，则抛出异常
     * @param obj 对象
     * @param msg 异常信息
     */
    public static void isNotEmpty(Object obj, String msg) {
        if (isEmpty(obj)) {
            throwException(msg);
        }
    }

    /**
     * 判断对象是否为空，如果为空，则抛出异常
     * @param obj 对象
     * @param errorEnum 异常枚举
     * @param args 异常信息参数
     */
    public static void isNotEmpty(Object obj, ErrorEnum errorEnum, Object... args) {
        if (isEmpty(obj)) {
            throwException(errorEnum, args);
        }
    }

    /**
     * 判断对象是否为空，如果不为空，则抛出异常
     * @param obj 对象
     * @param msg 异常信息
     */
    public static void isEmpty(Object obj, String msg) {
        if (!isEmpty(obj)) {
            throwException(msg);
        }
    }

    /**
     * 判断对象是否相等，如果不相等，则抛出异常
     * @param o1 对象1
     * @param o2 对象2
     * @param msg 异常信息
     */
    public static void equal(Object o1, Object o2, String msg) {
        if (!equal(o1, o2)) {
            throwException(msg);
        }
    }

    /**
     * 判断对象是否相等，如果相等，则抛出异常
     * @param o1 对象1
     * @param o2 对象2
     * @param msg 异常信息
     */
    public static void notEqual(Object o1, Object o2, String msg) {
        if (equal(o1, o2)) {
            throwException(msg);
        }
    }

    /**
     * 抛出异常
     * @param msg 异常信息
     */
    private static void throwException(String msg) {
        throwException(null, msg);
    }

    /**
     * 抛出异常
     * @param errorEnum 异常枚举
     * @param arg 异常信息参数
     */
    private static void throwException(ErrorEnum errorEnum, Object... arg) {
        if (Objects.isNull(errorEnum)) {
            errorEnum = BusinessErrorEnum.BUSINESS_ERROR;
        }
        final ErrorEnum error = errorEnum;
        throw new BusinessCustomException(new ErrorEnum() {
            @Override
            public Integer getErrorCode() {
                return error.getErrorCode();
            }
            @Override
            public String getErrorMsg() {
                return MessageFormat.format(error.getErrorMsg(), arg);
            }
        });
    }

    /**
     * 判断对象是否为空
     * @param obj 对象
     * @return null返回true，否则返回false
     */
    private static boolean isEmpty(Object obj) {
        return obj == null;
    }

    /**
     * 判断对象值是否相等
     * @param obj1 对象1
     * @param obj2 对象2
     * @return 相等返回true，否则返回false
     */
    private static boolean equal(Object obj1, Object obj2) {
        if (obj1 == null && obj2 == null) {
            return true;
        }
        if (obj1 != null) {
            return obj1.equals(obj2);
        }
        return false;
    }
}
