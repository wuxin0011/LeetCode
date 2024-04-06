package code_generation.contest;

import code_generation.utils.StringUtils;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * @author: wuxin0011
 * @Description: 代码块解析结果
 */
public class ParseCodeInfo {

    public final static String ConstructorClass = "__ConstructorClass__";


    // 原始字符串
    // 可能有其他添加补充
    // 因此需要保留原始字符串
    private String origin;

    // 匹配到的类名
    // 如果是构造类这个类名可能会使用到
    private String className;


    // 匹配到的方法名
    // 对于非构造类的
    // 这个方法名为后续反射调用
    private String methodName;


    // 匹配到除了类在在内的所有方法
    // example
    // class Solution {
    //   public void f1(){}
    //   public void f2(){}
    // }

    // 期望匹配结果是
    // public f1(){} public void f2(){}
    private String method;


    // 是否是构造类 通过类名来判断或者自定义实现
    // 当 noConstructorName 不为空 以 这个为表标准
    private boolean isConstructor;


    // 如果不是构造类的方法名
    // 期望方法名
    // 使用数组可能会有其他情况
    private String[] noConstructorNames;

    public ParseCodeInfo(String... noConstructorNames) {
        this.noConstructorNames = noConstructorNames;
    }


    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
        if (this.noConstructorNames != null && this.noConstructorNames.length > 0) {
            this.isConstructor = true; //  是构造类 ？
            for (String name : noConstructorNames) {
                if (!StringUtils.isEmpty(name) && name.equals(className)) {
                    this.isConstructor = false; // 不是构造类
                    break;
                }
            }
        } else {
            this.isConstructor = false; // 默认
        }
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = isConstructor ? ConstructorClass :  methodName;

    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public boolean isConstructor() {
        return isConstructor;
    }

    public void setConstructor(boolean constructor) {
        isConstructor = constructor;
    }

    public String[] getNoConstructorName() {
        return noConstructorNames;
    }

    public void setNoConstructorName(String... noConstructorNames) {
        this.noConstructorNames = noConstructorNames;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ParseCodeInfo.class.getSimpleName() + "[", "]")
                // .add("origin='" + origin + "'")
                .add("className='" + className + "'")
                .add("methodName='" + methodName + "'")
                .add("method='" + method + "'")
                .add("isConstructor=" + isConstructor)
                .add("noConstructorNames='" + Arrays.toString(noConstructorNames) + "'")
                .toString();
    }
}
