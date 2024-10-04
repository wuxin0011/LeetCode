package leetcode._0x3f_.dp.knapsack.d_goup_knaspack.Solution_0002;

import code_generation.utils.IoUtil;

import java.util.List;

/**
 * 2218. 从栈中取出 K 个硬币的最大面值和
 * <p>
 * 一张桌子上总共有 n个硬币 栈。
 * 每个栈有 正整数个带面值的硬币。
 * 每一次操作中，你可以从任意一个栈的 顶部取出 1 个硬币，从栈中移除它，并放入你的钱包里。
 * 给你一个列表piles，其中piles[i]是一个整数数组，分别表示第 i个栈里 从顶到底的硬币面值。同时给你一个正整数k，请你返回在恰好进行k次操作的前提下，你钱包里硬币面值之和最大为多少。
 * <p>
 * 示例 1：
 * 输入：piles = [[1,100,3],[7,8,9]], k = 2
 * 输出：10^1
 * 解释：
 * 上图展示了几种选择 k 个硬币的不同方法。
 * 我们可以得到的最大面值为 10^1 。
 * <p>
 * 示例 2：
 * 输入：piles = [[100],[100],[100],[100],[100],[100],[1,1,1,1,1,1,700]], k = 7
 * 输出：706
 * 解释：
 * 如果我们所有硬币都从最后一个栈中取，可以得到最大面值和。
 * <p>
 * 提示：
 * n == piles.length
 * 1 <= n <= 1000
 * 1 <= piles[i][j] <= 10^5
 * 1 <= k <= sum(piles[i].length) <= 2000
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/maximum-value-of-k-coins-from-piles
 * @title: maximum-value-of-k-coins-from-piles
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "maxValueOfCoins", "in.txt");
    }


    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        this.piles = piles;
        sk = new int[piles.size()];
        int s = 0, tot = 0;
        for (int i = 0; i < sk.length; i++) {
            sk[i] = piles.get(i).size();
            for (Integer x : piles.get(i)) {
                tot += x;
            }
            s += sk[i];
        }
        if (s == k) {
            return tot;
        }
        a = new int[sk.length];
        memo = new Integer[k + 1];
        long st = System.currentTimeMillis();
        int ans = dfs(k);
        long ed = System.currentTimeMillis();
        System.out.println("cost time = " + (ed - st) + " ms");
        return ans;
    }

    int[] sk;
    int[] a;


    List<List<Integer>> piles;

    Integer[] memo;


    int dfs(int j) {
        if (j == 0) {
            return 0;
        }
        if (memo[j] != null) {
            return memo[j];
        }
        int ans = 0;
        for (int i = 0; i < sk.length; i++) {
            if (a[i] + 1 <= sk[i]) {
                a[i]++;
                ans = Math.max(dfs(j - 1) + piles.get(i).get(a[i] - 1), ans);
//                if (j - 1 == 0) {
//                    // System.out.println("ans = " + ans);
//                }
                a[i]--;
            }

        }
//        return memo[j] = ans;
        return ans;

    }


}