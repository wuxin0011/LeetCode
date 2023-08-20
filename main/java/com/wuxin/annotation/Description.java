package com.wuxin.annotation;

import com.wuxin.utils.Bean.Difficulty;
import com.wuxin.utils.Bean.Tag;
import com.wuxin.utils.Bean.Type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: wuxin0011
 * @Description: 描述该类信息
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Description {

    /**
     * 题目描述 简介
     */
    String value() default "";


    /**
     * 题目地址
     */
    String url() default "https://leetcode.cn/";


    /**
     * 题目标签类型
     */
    Tag tag() default Tag.OTHER;

    /**
     * 自定义题目类型
     */
    String customTag() default "";


    /**
     * 题目难度
     */
    Difficulty diff() default Difficulty.SIMPLE;


    /**
     * 题目标签 关键字 思想
     */
    Type[] types() default {};


    /**
     * 自定义 题目标签 关键字 思想
     */
    String[] customType() default {};


}
