package code_generation.enums;

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
    PREFIX_AND("前缀和"),
    MAP("图"),
    Topology_Map("拓扑图"),
    SEGMENT_TREE("线段树"),
    GEOMETRY("几何"),
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
    POINTER_TWO("双指针"),
    PREFIX_SUF("前后缀"),
    SLIDE_WINDOW("滑动窗口"),
    UNION("并查集"),
    TRIE("前缀树"),
    MEMO("记忆化搜索"),
    SHORTEST_PATH("最短路"),
    TREE_ARRAY("树状数组"),
    HASH_FUNCTION("哈希函数"),
    UNKNOWN("unknown");



    ;



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
