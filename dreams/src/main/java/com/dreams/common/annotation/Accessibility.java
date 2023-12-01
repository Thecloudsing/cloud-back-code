package com.dreams.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 默认情况下，所有接口都需要登录才能访问 生效
 * Description: 放行接口
 *
 * @author luoan
 * @since 2023/10/19
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Accessibility {
}
