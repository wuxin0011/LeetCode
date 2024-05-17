package leetcode.ox3if.data_struct.pre_sum.base.Solution_0003;

import code_generation.utils.IoUtil;

/**
 * 2438. 二的幂数组中查询范围内的乘积
 * <p>
 * 给你一个正整数n，你需要找到一个下标从0开始的数组powers，它包含 最少数目的 2的幂，且它们的和为n。
 * powers数组是非递减顺序的。根据前面描述，构造powers数组的方法是唯一的。
 * 同时给你一个下标从 0开始的二维整数数组queries，其中queries[i] = [lefti, righti]，其中queries[i]表示请你求出满足lefti <= j <= righti的所有powers[j]的乘积。
 * 请你返回一个数组answers，长度与queries的长度相同，其中answers[i]是第i个查询的答案。由于查询的结果可能非常大，请你将每个answers[i]都对10^9 + 7取余。
 * <p>
 * 示例 1：
 * 输入：n = 15, queries = [[0,1],[2,2],[0,3]]
 * 输出：[2,4,64]
 * 解释：
 * 对于 n = 15 ，得到 powers = [1,2,4,8] 。没法得到元素数目更少的数组。
 * 第 1 个查询的答案：powers[0] * powers[1] = 1 * 2 = 2 。
 * 第 2 个查询的答案：powers[2] = 4 。
 * 第 3 个查询的答案：powers[0] * powers[1] * powers[2] * powers[3] = 1 * 2 * 4 * 8 = 64 。
 * 每个答案对 10^9 + 7 得到的结果都相同，所以返回 [2,4,64] 。
 * <p>
 * 示例 2：
 * 输入：n = 2, queries = [[0,0]]
 * 输出：[2]
 * 解释：
 * 对于 n = 2, powers = [2] 。
 * 唯一一个查询的答案是 powers[0] = 2 。答案对 10^9 + 7 取余后结果相同，所以返回 [2] 。
 * <p>
 * 提示：
 * 1 <= n <= 10^9
 * 1 <= queries.length <= 10^5
 * 0 <= starti <= endi < powers.length
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/range-product-queries-of-powers
 * @title: 二的幂数组中查询范围内的乘积
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "productQueries", "in.txt");
    }

    private static final int MOD = (int) 1e9 + 7;


    public int[] productQueries(int n, int[][] queries) {
        int[] power = buildPower(n);
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = calc(power, queries[i][0], queries[i][1]);
        }
        return ans;
    }


    public static int calc(int[] power, int l, int r) {
        long ans = 1;
        for (int i = l; i <= r; i++) {
            ans = ans * power[i] % MOD;
        }
        return (int) ans;
    }

    public static int[] buildPower(int n) {
        int[] tot = new int[32];
        int cnt = 0;
        for (int i = 0; i < 32; i++) {
            if ((n >> (31 - i) & 1) == 1) {
                tot[31 - i] = 1 << (31 - i);
                cnt++;
            }
        }
        int[] h = new int[cnt];
        int i = 0;
        int k = 0;
        while (i < 32 && k < cnt) {
            if (tot[i] == 0) {
                i++;
                continue;
            }
            h[k++] = tot[i];
            i++;
        }
        return h;
    }


}