package leetcode._0x3f_.math.b_combination_count.d_exclusion_principle.exclusion_principle_0004;

import code_generation.utils.IoUtil;

/**
 * 2930. 重新排列后包含指定子字符串的字符串数目
 * <p>
 * 给你一个整数n。
 * 如果一个字符串s只包含小写英文字母，且将 s的字符重新排列后，新字符串包含子字符串"leet" ，那么我们称字符串 s是一个 好字符串。
 * 比方说：
 * 字符串"lteer"是好字符串，因为重新排列后可以得到"leetr"。
 * "letl"不是好字符串，因为无法重新排列并得到子字符串"leet"。
 * 请你返回长度为 n的好字符串 总数目。
 * 由于答案可能很大，将答案对10^9 + 7取余后返回。
 * 子字符串是一个字符串中一段连续的字符序列。
 * <p>
 * 示例 1：
 * 输入：n = 4
 * 输出：12
 * 解释：总共有 12 个字符串重新排列后包含子字符串 "leet" ："eelt" ，"eetl" ，"elet" ，"elte" ，"etel" ，"etle" ，"leet" ，"lete" ，"ltee" ，"teel" ，"tele" 和 "tlee" 。
 * <p>
 * 示例 2：
 * 输入：n = 10
 * 输出：83943898
 * 解释：长度为 10 的字符串重新排列后包含子字符串 "leet" 的方案数为 526083947580 。所以答案为 526083947580 % (10^9 + 7) = 83943898 。
 * <p>
 * 提示：
 * 1 <= n <= 10^5
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/number-of-strings-which-can-be-rearranged-to-contain-substring
 * @title: 重新排列后包含指定子字符串的字符串数目
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "stringCount", "in.txt");
    }

    private static final int MOD = (int) 1e9 + 7;


    /**
     * U 全集                           26 ^ n
     * <p>
     * <p>
     * <p>
     * 至少一个满足条件
     * A 不包含 l                        25 ^ n
     * B 不包含 t                        25 ^ n
     * e 最多一个e
     * 只有一个e                    25 ^ (n -1)
     * 没有e                       25 ^ n
     * <p>
     * 25 ^ n + 25 ^ n + 25 ^ (n - 1 ) + 25 ^ n
     * 3 * 25 ^ n  + 25 ^ (n - 1 )
     * 3 * 25 * 25 ^ (n -1)  + 25 ^ (n - 1 )
     * 75 * (25 ^ n -1)  + 25 ^ (n - 1 )
     * (75 + n ) * ( 25 ^ (n - 1 ))
     * 至少两个不满足条件
     * AB 不包含 l 而且 t                  24 ^ n
     * AC 不包含 l 而且 (最多一个e)         24 ^ n +  24 ^ (n -1)
     * BC 不包含 l 而且 t (最多一个e)       24 ^ n +  24 ^ (n -1)
     * <p>
     * 24 ^ n + 24 ^ n + 24 ^ (n - 1) + 24 ^ n +  24 ^ (n -1)
     * 3 * 24 ^ n  +  24 ^ (n - 1) * 2
     * 3 * 24 * 24 ^ (n - 1)  +  24 ^ (n - 1) * 2
     * 72 * 24 ^ (n - 1)  +  24 ^ (n - 1) * 2
     * (72 + 2 * n) * (24 ^ (n - 1))
     * <p>
     * 三个不满足条件
     * ABC 不包含 l 而且 不包含 t 而且 t (最多一个e)  23 ^ n +  23 ^ (n -1)
     * 23 * 23 ^ (n - 1) +  23 ^ (n -1)
     * ( 23 + n) * (23 ^ (n - 1))
     * @param n
     * @return
     */

    public int stringCount(int n) {
        // return (int) ((pow(26, n) - (pow(25, n) * 3 + pow(25, n - 1)) + (3 * pow(24, n) + 2 * pow(24, n - 1)) + pow(23, n) + pow(23, n - 1)) % MOD + MOD) % MOD; // 保证结果非负
//        return 0;
        return (int) (((pow(26, n)
                - pow(25, n - 1) * (75 + n)
                + pow(24, n - 1) * (72 + n * 2)
                - pow(23, n - 1) * (23 + n)) % MOD + MOD) % MOD); // 保证结果非负

    }


    /**
     * 快速幂
     *
     * @param x val
     * @param n 幂的次数
     * @return result
     */
    public static long pow(long x, int n) {
        return pow(x, n, MOD);
    }


    // 10001010101
    public static long pow(long x, int n, int mod) {
        long res = 1;
        while (n > 0) {
            // 当前位数有1
            if ((n & 1) == 1) {
                res = res * x % mod;
            }
            x = x * x % mod;
            n >>= 1;
        }
        return res;
    }

}