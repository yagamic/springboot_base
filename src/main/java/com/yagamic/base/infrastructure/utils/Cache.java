package com.yagamic.base.infrastructure.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( { ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {
    /**
     * 缓存对象的class
     * @return
     */
    Class cacheObject();

    /**
     * 是否是列表
     * @return
     */
    boolean isList() default true;

    /**
     * 缓存的key
     * @return
     */
    String key();

    /**
     * 取缓存的参数的数量
     * @return
     */
    int paramsCount() default 1;

    /**
     * 缓存参数间的分隔符
     * @return
     */
    String separator() default "";

    /**
     * 超时时间
     * @return
     */
    long timeout() default 0;
}