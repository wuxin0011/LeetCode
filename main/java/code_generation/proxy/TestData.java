package code_generation.proxy;

import code_generation.annotation.Description;
import code_generation.annotation.TestCaseGroup;
import code_generation.utils.ReflectUtils;

import java.lang.reflect.Method;

/**
 * @author: wuxin0011
 * @Description: 封装测试信息
 */
public class TestData {

    /**
     * 题目信息
     */
    public String info;

    /**
     * 测试数据组
     */
    public int[] testCaseGroup;

    /**
     * 测试方法
     */
    Method method;

    /**
     * 原类文件
     */
    Class<?> src;


    /**
     * 目标类
     */
    Class<?> origin;

    public TestData(Class<?> src, Class<?> origin) {
        this(null, src, src);
    }

    public TestData(Class<?> src) {
        this(null, src, src);
    }

    public TestData(Method method, Class<?> origin, Class<?> src) {
        this.method = method;
        this.origin = origin;
        this.src = src;
        this.process();
    }

    public void process() {
        this.info = this.getDescInfo();
        this.testCaseGroup = this.getTestCaseInfo();
    }

    public String getDescInfo() {
        Description declaredAnnotation = method != null ? method.getDeclaredAnnotation(Description.class) : null;
        if (declaredAnnotation != null) {
            return ReflectUtils.getDescriptionInfo(declaredAnnotation);
        }
        declaredAnnotation = origin != null ? origin.getDeclaredAnnotation(Description.class) : null;
        if (declaredAnnotation != null) {
            return ReflectUtils.getDescriptionInfo(declaredAnnotation);
        }
        declaredAnnotation = src != null ? src.getDeclaredAnnotation(Description.class) : null;
        if (declaredAnnotation != null) {
            return ReflectUtils.getDescriptionInfo(declaredAnnotation);
        }
        return "";
    }

    public int[] getTestCaseInfo() {
        TestCaseGroup declaredAnnotation = method != null ? method.getDeclaredAnnotation(TestCaseGroup.class) : null;
        if (declaredAnnotation != null) {
            return ReflectUtils.getTestCaseInfo(declaredAnnotation);
        }
        declaredAnnotation = origin != null ? origin.getDeclaredAnnotation(TestCaseGroup.class) : null;
        if (declaredAnnotation != null) {
            return ReflectUtils.getTestCaseInfo(declaredAnnotation);
        }
        declaredAnnotation = src != null ? src.getDeclaredAnnotation(TestCaseGroup.class) : null;
        if (declaredAnnotation != null) {
            return ReflectUtils.getTestCaseInfo(declaredAnnotation);
        }
        return new int[]{1, 0x3fffff};
    }

}
