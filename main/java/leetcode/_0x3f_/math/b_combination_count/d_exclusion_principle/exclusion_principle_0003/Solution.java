package leetcode._0x3f_.math.b_combination_count.d_exclusion_principle.exclusion_principle_0003;

import code_generation.utils.IoUtil;

/**
 * 2929. 给小朋友们分糖果 II
 * <p>
 * 给你两个正整数n 和limit。
 * 请你将 n颗糖果分给 3位小朋友，确保没有任何小朋友得到超过 limit颗糖果，请你返回满足此条件下的总方案数。
 * <p>
 * 示例 1：
 * 输入：n = 5, limit = 2
 * 输出：3
 * 解释：总共有 3 种方法分配 5 颗糖果，且每位小朋友的糖果数不超过 2 ：(1, 2, 2) ，(2, 1, 2) 和 (2, 2, 1) 。
 * <p>
 * 示例 2：
 * 输入：n = 3, limit = 3
 * 输出：10
 * 解释：总共有 10 种方法分配 3 颗糖果，且每位小朋友的糖果数不超过 3 ：(0, 0, 3) ，(0, 1, 2) ，(0, 2, 1) ，(0, 3, 0) ，(1, 0, 2) ，(1, 1, 1) ，(1, 2, 0) ，(2, 0, 1) ，(2, 1, 0) 和 (3, 0, 0) 。
 * <p>
 * 提示：
 * 1 <= n <= 10^6
 * 1 <= limit <= 10^6
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/distribute-candies-among-children-ii
 * @title: distribute-candies-among-children-ii
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "distributeCandies", "in.txt");
    }


    /**
     * 容斥原理 => 满足要求 = 全集 - 不满足一要求的集合
     * <p>
     * 本题:
     * U 为全集                          => Cn2 ( n+ 2)
     * A 至少一个孩子超过 limit            => Cn2 3 * (n + 2 - 1 * (limit + 1))
     * B 两个孩子超过 limit               => Cn2 3 * (n + 2 - 2 * (limit + 1))
     * C 三个孩子超过 limit               => Cn2 1 * (n + 2 - 3 * (limit + 1))
     * <p>
     * ans = U - ( |A| + |B| + |C| - |AnB| - |AnC| - |BnC| + |AnBnC| )
     */


    public static long C2(int n) {
        if (n < 1) {
            return 0;
        }
        return (long) n * (n - 1) / 2;
    }
    // 为什么需要 n + 2
    //  假设 1 1 1 为三个孩子 | 为挡板
    // 那么挡板插入范围为 C2(n+2)

    public long distributeCandies(int n, int limit) {

        return C2(n + 2) - 3L * C2(n + 2 - (limit + 1)) + 3L * C2(n + 2 - 2 * (limit + 1)) - C2(n + 2 - 3 * (limit + 1));
    }


}