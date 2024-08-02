package leetcode._0x3f_.dp.state_compression.adjacent_unrelated.adjacent_unrelated_0000;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 526. 优美的排列
 *
 * 假设有从 1 到 n 的 n 个整数。
 * 用这些整数构造一个数组 perm（下标从 1 开始），只要满足下述条件 之一 ，该数组就是一个 优美的排列 ：
 * 	perm[i] 能够被 i 整除
 * 	i 能够被 perm[i] 整除
 * 给你一个整数 n ，返回可以构造的 优美排列 的 数量 。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 * 解释：
 * 第 1 个优美的排列是 [1,2]：
 *     - perm[1] = 1 能被 i = 1 整除
 *     - perm[2] = 2 能被 i = 2 整除
 * 第 2 个优美的排列是 [2,1]:
 *     - perm[1] = 2 能被 i = 1 整除
 *     - i = 2 能被 perm[2] = 1 整除
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 *
 * 提示：
 * 	1 <= n <= 15
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/beautiful-arrangement
 * @title: 优美的排列
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "countArrangement", "in.txt");
    }

    List<List<Integer>> path = new ArrayList<>();


    int vis = 0;

    int ans = 0;

    int n;

    List<Integer> L = new ArrayList<>();

    public int countArrangement(int n) {
        this.n = n;
        dfs(0);
//        for (List<Integer> integers : path) {
//            System.out.println(integers);
//            check(integers);
//        }
        return ans;
    }

    public void dfs(int size) {
        // 递归边界
        if (size == n) { // 0 -> n - 1
            ans++;
            // path.add(new ArrayList<>(L));
            return;
        }
        // perm[i]
        for (int k = 1; k <= n; k++) {
            if ((vis >> k & 1) == 1) {
                continue;
            }
            if (check(k, size + 1)) {
                vis |= 1 << k;
                // L.add(k);
                dfs(size + 1);
                //  L.remove(L.size() - 1);
                vis &= ~(1 << k);
            }
        }


    }

    public static boolean check(int val, int i) {
        if (i == 0 || val == 0) {
            return false;
        }
        return val % i == 0 || i % val == 0;
    }

    public static void check(List<Integer> p) {
        boolean ok = true;
        for (int i = 0; i < p.size(); i++) {
            if (!check(p.get(i), i + 1)) {
                ok = false;
                break;
            }
        }
        System.out.println(ok ? " success " : "error");
    }


}