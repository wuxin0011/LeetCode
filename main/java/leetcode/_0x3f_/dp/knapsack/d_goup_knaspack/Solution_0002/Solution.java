package leetcode._0x3f_.dp.knapsack.d_goup_knaspack.Solution_0002;

import code_generation.annotation.Description;
import code_generation.enums.Difficulty;
import code_generation.enums.Tag;
import code_generation.enums.Type;
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
 * @url: <a href="https://leetcode.cn/problems/maximum-value-of-k-coins-from-piles">maximum-value-of-k-coins-from-piles</a>
 * @title: maximum-value-of-k-coins-from-piles
 */

@Description(value = "分组背包 + 前缀和", diff = Difficulty.HARD, types = Type.DB,tag = Tag.ARRAY)
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "maxValueOfCoins", "in.txt");
    }


    // 经过转换后就是01的分组背包
    // 对于每个桶来说如果取到第I个面币必定会取到第I-1个面币
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        if (k == 0) {
            return 0;
        }
        int cur = 0, sums = 0;
        for (List<Integer> coinList : piles) {
            int n = coinList.size();
            // 预处理成前缀和
            for (int i = 1; i < n; i++) {
                coinList.set(i, coinList.get(i - 1) + coinList.get(i));
            }
            cur += n;
            sums += coinList.get(n - 1);
        }
        // 如果K大于所有桶的数量 就会全部获取到直接返回
        if (k >= cur) {
            return sums;
        }

        this.piles = piles;
        memo = new Integer[piles.size() + 1][k + 1];
        return dfs(0, k);
    }


    Integer[][] memo;
    List<List<Integer>> piles;


    public int dfs(int i, int rest) {
        if (i >= piles.size() || rest == 0) {
            return 0;
        }
        if (memo[i][rest] != null) {
            return memo[i][rest];
        }
        int ans = 0;
        // 有可能桶打大小不满足
        int curCoinsSize = Math.min(piles.get(i).size(), rest);
        // 不选
        ans = Math.max(ans, dfs(i + 1, rest));
        for (int j = 1; j <= curCoinsSize; j++) {
            int index = j - 1;
            ans = Math.max(ans, piles.get(i).get(index) + dfs(i + 1, rest - j));
        }
        return memo[i][rest] = ans;
    }
}