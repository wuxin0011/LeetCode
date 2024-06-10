package code_generation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: wuxin0011
 * @see: leetcode.base
 * @Description: 由于测试时需要 有时候不知道测试几个案例 比如有时候数据过多 希望测试 1-2 个用例 比如测试第五组案例[5,5] 可以用这个注解实现功能 [start,end]
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TestCaseGroup {

    /**
     * 从那个组数据开始评测
     *
     * @return 默认 1
     */
    int start() default 1;


    /**
     * 从哪个数据结束
     *
     * @return 默认 0x3f3f3f 这个数据范围应该是够了🤣
     */
    int end() default 0x3f3f3f;


    /**
     * 是否生效
     *
     * @return 默认生效
     */
    boolean use() default true;
}
