package com.wuxin.utils.Bean;

/**
 * @author: wuxin0011
 * @Description: 题目难易度
 */
public enum Difficulty {

    /**
     * 简单
     */
    SIMPLE("简单"),

    /**
     * 中等
     */
    MEDIUM("中等"),


    /**
     * 困难
     */
    HARD("困难");

    String desc;

    Difficulty(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }
}
