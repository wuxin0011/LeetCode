package leetcode.ox3if.math.b_combination_count.d_exclusion_principle.exclusion_principle_0005;

import code_generation.utils.IoUtil;

/**
 * 2513. 最小化两个数组中的最大值
 * <p>
 * 给你两个数组arr1 和arr2，它们一开始都是空的。
 * 你需要往它们中添加正整数，使它们满足以下条件：
 * arr1包含uniqueCnt1个互不相同的正整数，每个整数都不能 被divisor1整除。
 * arr2包含uniqueCnt2个互不相同的正整数，每个整数都不能被divisor2整除。
 * arr1 和arr2中的元素互不相同。
 * 给你divisor1，divisor2，uniqueCnt1和uniqueCnt2，请你返回两个数组中最大元素的最小值。
 * <p>
 * 示例 1：
 * 输入：divisor1 = 2, divisor2 = 7, uniqueCnt1 = 1, uniqueCnt2 = 3
 * 输出：4
 * 解释：
 * 我们可以把前 4 个自然数划分到 arr1 和 arr2 中。
 * arr1 = [1] 和 arr2 = [2,3,4] 。
 * 可以看出两个数组都满足条件。
 * 最大值是 4 ，所以返回 4 。
 * <p>
 * 示例 2：
 * 输入：divisor1 = 3, divisor2 = 5, uniqueCnt1 = 2, uniqueCnt2 = 1
 * 输出：3
 * 解释：
 * arr1 = [1,2] 和 arr2 = [3] 满足所有条件。
 * 最大值是 3 ，所以返回 3 。
 * <p>
 * 示例 3：
 * 输入：divisor1 = 2, divisor2 = 4, uniqueCnt1 = 8, uniqueCnt2 = 2
 * 输出：15
 * 解释：
 * 最终数组为 arr1 = [1,3,5,7,9,11,13,15] 和 arr2 = [2,6] 。
 * 上述方案是满足所有条件的最优解。
 * <p>
 * 提示：
 * 2 <= divisor1, divisor2 <= 10^5
 * 1 <= uniqueCnt1, uniqueCnt2 < 10^9
 * 2 <= uniqueCnt1 + uniqueCnt2 <= 10^9
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/minimize-the-maximum-of-two-arrays
 * @title: 最小化两个数组中的最大值
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "minimizeSet", "in.txt");
    }


    /**
     * U
     * <p>
     * d1 = 4　d2 = 6
     * <p>
     * arr1 : 1  2 3    5 6 7 9 10 11  13  ...
     * arr2 : 1  2 3     5  7 8 9 10 11  13  ...
     * <p>
     * arr1 独占 6 ：的倍数　而且不能是　LCM（４，６）　的倍数            = > x / d2 - x / lcm
     * arr2 独占 4 ：的倍数　而且不能是　LCM（４，６）　的倍数            = > x / d2 - x / lcm
     * common     :   不是4的倍数也不是6的倍数                        = > x / d2 + x / d1  - x / lcm
     *
     * @param d1
     * @param d2
     * @param u1
     * @param u2
     * @return
     */
//        为什么上界是　r = (u1 + u2) * 2
//        不妨设　ｄ１　＝＝　ｄ２　而且　ｄ１　为偶数
//        那么只能选择　(u1 + u2) * 2
    public int minimizeSet(int d1, int d2, int u1, int u2) {
        long lcm = (long) d1 / gcd(d1, d2) * d2;

        long l = 0, r = (u1 + u2) * 2L;
        while (l + 1 < r) {
            long x = l + ((r - l) >> 1);
            if (check(x, d1, d2, u1, u2, lcm)) {
                r = x;
            } else {
                l = x;
            }
        }

        return (int)r;
    }

    public static boolean check(long x, int d1, int d2, int u1, int u2, long lcm) {
        long l1 = Math.max(0, u1 - (x / d2 - x / lcm));
        long l2 = Math.max(0, u2 - (x / d1 - x / lcm));
        long common = x / d1 + x / d2 - x / lcm;
        return x - common - l1 - l2 >= 0;
    }


    public static int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }


}