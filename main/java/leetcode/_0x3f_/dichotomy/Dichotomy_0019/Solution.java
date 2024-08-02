package leetcode._0x3f_.dichotomy.Dichotomy_0019;

import code_generation.utils.IoUtil;

import java.util.List;

/**
 * 2861. 最大合金数
 * <p>
 * 假设你是一家合金制造公司的老板，你的公司使用多种金属来制造合金。现在共有 n 种不同类型的金属可以使用，并且你可以使用 k 台机器来制造合金。每台机器都需要特定数量的每种金属来创建合金。对于第 i 台机器而言，创建合金需要 composition[i][j] 份 j 类型金属。最初，你拥有 stock[i] 份 i 类型金属，而每购入一份 i 类型金属需要花费 cost[i] 的金钱。给你整数 n、k、budget，下标从 1 开始的二维数组 composition，两个下标从 1 开始的数组 stock 和 cost，请你在预算不超过 budget 金钱的前提下，最大化 公司制造合金的数量。所有合金都需要由同一台机器制造。返回公司可以制造的最大合金数。
 * <p>
 * 示例 1：输入：n = 3, k = 2, budget = 15, composition = [[1,1,1],[1,1,10]], stock = [0,0,0], cost = [1,2,3]输出：2解释：最优的方法是使用第 1 台机器来制造合金。要想制造 2 份合金，我们需要购买：- 2 份第 1 类金属。- 2 份第 2 类金属。- 2 份第 3 类金属。总共需要 2 * 1 + 2 * 2 + 2 * 3 = 12 的金钱，小于等于预算 15 。注意，我们最开始时候没有任何一类金属，所以必须买齐所有需要的金属。可以证明在
 * <p>
 * 示例条件下最多可以制造 2 份合金。
 * <p>
 * 示例 2：输入：n = 3, k = 2, budget = 15, composition = [[1,1,1],[1,1,10]], stock = [0,0,100], cost = [1,2,3]输出：5解释：最优的方法是使用第 2 台机器来制造合金。 要想制造 5 份合金，我们需要购买： - 5 份第 1 类金属。- 5 份第 2 类金属。 - 0 份第 3 类金属。 总共需要 5 * 1 + 5 * 2 + 0 * 3 = 15 的金钱，小于等于预算 15 。 可以证明在
 * <p>
 * 示例条件下最多可以制造 5 份合金。
 * <p>
 * 示例 3：输入：n = 2, k = 3, budget = 10, composition = [[2,1],[1,2],[1,1]], stock = [1,1], cost = [5,5]输出：2解释：最优的方法是使用第 3 台机器来制造合金。要想制造 2 份合金，我们需要购买：- 1 份第 1 类金属。- 1 份第 2 类金属。总共需要 1 * 5 + 1 * 5 = 10 的金钱，小于等于预算 10 。可以证明在
 * <p>
 * 示例条件下最多可以制造 2 份合金。
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= n, k <= 100
 * 0 <= budget <= 108
 * composition.length == k
 * composition[i].length == n
 * 1 <= composition[i][j] <= 100
 * stock.length == cost.length == n
 * 0 <= stock[i] <= 108
 * 1 <= cost[i] <= 100
 * <p>
 * <p>
 * 所有合金都需要由同一台机器制造
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/maximum-number-of-alloys
 * @title: 最大合金数
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "maxNumberOfAlloys", "in.txt");
        IoUtil.testUtil(Solution.class, "maxNumberOfAlloys2", "in.txt");
    }


    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        int max = 0;
        for (Integer s : stock) {
            if(s>max){
                max = s;
            }
        }
        int rest = Integer.MAX_VALUE;
        int ans = 0;
        for (List<Integer> com : composition) {
            long l = 1, r = budget / n + max; // 为什么要 + max ？ 因为至少位1 因此最大数量位 money / n * 1 + max
            int temp_rest = Integer.MAX_VALUE;
            while (l <= r) {
                long mid = l + ((r - l) >> 1);
                int[] check = check(mid, budget, com, stock, cost);
                if (check[0] != -1 && check[1] <= temp_rest) {
                    temp_rest = check[1];
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            if (r >= ans && rest > temp_rest) {
                ans = (int) r;
            }
        }
        return ans;
    }


    public static int[] check(long count, int budget, List<Integer> com, List<Integer> stock, List<Integer> cost) {
        // 最大化就是合理利用 stock  剩余尽可能小
        int n = stock.size();
        long sum = 0;
        int rest = 0;
        for (int i = 0; i < n; i++) {
            long need = com.get(i) * count;
            if (stock.get(i) - need >= 0) {
                rest += stock.get(i) - need;
            } else {
                sum += (long) (need - stock.get(i)) * cost.get(i);
                // 方案不可行
                if (sum > budget) {
                    return new int[]{-1, -1};
                }
            }
        }
        return new int[]{(int) sum, rest};
    }


    public int maxNumberOfAlloys2(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        int[] stock_array = stock.stream().mapToInt(Integer::intValue).toArray();
        int[] cost_array = cost.stream().mapToInt(Integer::intValue).toArray();
        int ans = 0;
        for (List<Integer> com : composition) {
            int[] com_array = com.stream().mapToInt(Integer::intValue).toArray();
            int l = 1, r = Integer.MAX_VALUE;
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                if (check2(mid, budget, com_array, stock_array, cost_array)) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            ans = Math.max(ans,r);
        }
        return ans;
    }


    public static boolean check2(int count, int budget, int[] com, int[] stock, int[] cost) {
        // 最大化就是合理利用 stock  剩余尽可能小
        int n = stock.length;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            long need = (long) com[i] * count;
            if (stock[i] - need >= 0) {
                continue;
            }
            sum += (need - stock[i]) * cost[i];
            // 方案不可行
            if (sum > budget) {
                return false;
            }
        }
        return true;
    }


}