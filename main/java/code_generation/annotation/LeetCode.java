package code_generation.annotation;

/**
 * @author: wuxin0011
 * @Description:
 */
public @interface LeetCode {
    /**
     * 源类
     * @return null
     */
    Class<?> src();

    /**
     * 需要执行的方法名
     * @return ""
     */
    String methodName() default "test";

    /**
     * 案例输入文件
     * @return ""
     */
    String fileName() default "in.txt";

    /**
     * 是否开启长文本解析
     * @return false
     */
    boolean openLongContent() default false;

    /**
     * 是否严格解析
     * @return false
     */
    boolean isStrict() default false;
}
