package com.wuxin.utils.Bean;

/**
 * @author: wuxin0011
 * @Description: 题目类型
 */


public enum Type {
    ARRAY("数组"),
    HASHTABLE("哈希表"),
    RECURSION("递归"),
    STRING("字符串"),
    DB("动态规划"),
    DOUBLE_POINT("双指针"),
    SORT("排序"),
    BITWISE_OPERATION("位运算"),
    BINARY_SEARCH("二分查找"),
    SINGLE_STACK("单调栈"),
    GREEDY("贪心"),
    MATRIX("矩阵"),
    DATABASE("数据库"),
    ITERATOR("迭代器"),
    GAME("博弈"),
    Prefix_and("前缀和"),
    MAP("图"),
    Topology_Map("拓扑图"),
    Segment_tree("线段树"),
    geometry("几何"),
    MATH("数学"),
    MEMORY_BASE_SEARCH("记忆化搜索"),
    TREE("树"),
    BINARY_TREE("二叉树"),
    BALANCED_TREE("平衡树"),
    DEPTH_FIRST_TRAVERSAL("深度优先遍历"),
    WIDTH_FIRST_TRAVERSAL("宽度优先遍历"),
    BREADHT_FIRST_TRAVERSAL("平衡树"),
    PARTITION("分治"),
    STACK("栈"),
    QUEUE("队列"),
    PILE_UP("堆"),
    PRIORITY_QUEUE("优先队列"),
    BACKTRACk("回溯"),
    A("哈希表");


    final String type;

    private Type(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }


    @Override
    public String toString() {
        return this.getType();
    }
}
