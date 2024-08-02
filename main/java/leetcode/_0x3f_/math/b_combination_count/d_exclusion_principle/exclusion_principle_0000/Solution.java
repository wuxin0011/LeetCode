package leetcode._0x3f_.math.b_combination_count.d_exclusion_principle.exclusion_principle_0000;

import code_generation.utils.IoUtil;

/**
 * 2652. 倍数求和
 * <p>
 * 给你一个正整数 n ，请你计算在 [1，n] 范围内能被 3、5、7 整除的所有整数之和。
 * 返回一个整数，用于表示给定范围内所有满足约束条件的数字之和。
 * <p>
 * 示例 1：
 * 输入：n = 7
 * 输出：21
 * 解释：在 [1, 7] 范围内能被 3、5、7 整除的所有整数分别是 3、5、6、7 。数字之和为 21。
 * <p>
 * 示例 2：
 * 输入：n = 10
 * 输出：40
 * 解释：在 [1, 10] 范围内能被 3、5、7 整除的所有整数分别是 3、5、6、7、9、10 。数字之和为 40。
 * <p>
 * 示例 3：
 * 输入：n = 9
 * 输出：30
 * 解释：在 [1, 9] 范围内能被 3、5、7 整除的所有整数分别是 3、5、6、7、9 。数字之和为 30。
 * <p>
 * 提示：
 * 1 <= n <= 10^3
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/sum-multiples
 * @title: 倍数求和
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "sumOfMultiples", "in.txt");
        // IoUtil.testUtil(Solution.class, "sumOfMultiples1", "in.txt");
        // IoUtil.testUtil(Solution.class, "sumOfMultiples2", "in.txt");
    }


    // 等差数列求和公式
    // Sn=n(a1+an)/2

    public static int S(int n, int m) {
        int k = n / m;
        return k * (k + 1) / 2 * m;
    }


//    public static int S(int n, int m) {
//        int k = n / m;
//        int d = m;
//        int a1 = m;
//        int an = (k - 1) * d + a1;
//        return (a1 + an) / 2 * k;
//    }

    public int sumOfMultiples(int n) {
        return S(n, 3) + S(n, 5) + S(n, 7) - S(n, 15) - S(n, 21) - S(n, 35) + S(n, 105);
    }


    // 分析
    // 题目要求 3 5 7
    // 能被 3 A
    // 能被 5 B
    // 能被 7 C


    public int sumOfMultiples1(int n) {
        // AnB AnC BnC AnBnC
        // 15  21  35  105
        int u = 0, ab = 0, ac = 0, bc = 0, abc = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0) {
                u += i;
            }
            if (i % 5 == 0) {
                u += i;
            }
            if (i % 7 == 0) {
                u += i;
            }
            if (i % 15 == 0) {
                ab += i;
            }
            if (i % 21 == 0) {
                ac += i;
            }
            if (i % 35 == 0) {
                bc += i;
            }
            if (i % 105 == 0) {
                abc += i;
            }
        }

        return u - ab - ac - bc + abc;
    }


    public int sumOfMultiples2(int n) {
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 || i % 5 == 0 || i % 7 == 0) {
                ans += i;
            }
        }
        return ans;
    }


}