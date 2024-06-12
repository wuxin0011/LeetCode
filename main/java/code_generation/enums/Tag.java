package code_generation.enums;

/**
 * @author: wuxin0011
 * @Description: 题目分类
 */
public enum Tag {

    /**
     * 字符串
     */
    STRING("字符串"),

    /**
     * 其他
     */
    OTHER("其他"),

    /**
     * 单链表
     */
    LINKLIST("单链表"),

    /**
     * 数组
     */
    ARRAY("数组"),


    /**
     * 树
     */
    TREE("树"),


    /**
     * 图
     */
    GRAPHICAl("图"),


    /**
     * 空标签
     */
    NULL("unknown");


    // TODO 补充其他类型


    private String tag;


    Tag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return this.tag;
    }


}
