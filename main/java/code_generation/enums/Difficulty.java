package code_generation.enums;

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
    HARD("困难"),

    /**
     * null
     */
    NULL("unknown");


    final String desc;

    Difficulty(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }
}
