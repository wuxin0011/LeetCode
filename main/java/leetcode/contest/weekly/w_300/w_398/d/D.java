package leetcode.contest.weekly.w_300.w_398.d;

import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 100298. 到达第 K 级台阶的方案数
 *
 * 给你有一个 非负整数k。
 * 有一个无限长度的台阶，最低一层编号为 0 。
 * 虎老师有一个整数jump，一开始值为 0 。虎老师从台阶 1 开始，虎老师可以使用 任意次操作，目标是到达第k 级台阶。假设虎老师位于台阶 i ，一次 操作 中，虎老师可以：
 * 	向下走一级到i - 1，但该操作不能连续使用，如果在台阶第 0 级也不能使用。
 * 	向上走到台阶i + 2jump处，然后jump变为jump + 1。
 * 请你返回虎老师到达台阶 k处的总方案数。
 * 注意，虎老师可能到达台阶 k处后，通过一些操作重新回到台阶 k处，这视为不同的方案。
 *
 * 示例 1：
 * 输入：k = 0
 * 输出：2
 * 解释：
 * 2 种到达台阶 0 的方案为：
 * 	虎老师从台阶1 开始。
 *
 * 		执行第一种操作，从台阶 1 向下走到台阶 0 。
 *
 *
 * 	虎老师从台阶 1 开始。
 *
 * 		执行第一种操作，从台阶 1 向下走到台阶 0 。
 * 		执行第二种操作，向上走 20级台阶到台阶 1 。
 * 		执行第一种操作，从台阶 1 向下走到台阶 0 。
 *
 *
 *
 * 示例 2：
 * 输入：k = 1
 * 输出：4
 * 解释：
 * 4 种到达台阶 1 的方案为：
 * 	虎老师从台阶 1 开始，已经到达台阶 1 。
 * 	虎老师从台阶 1 开始。
 *
 * 		执行第一种操作，从台阶 1 向下走到台阶 0 。
 * 		执行第二种操作，向上走 20级台阶到台阶 1 。
 *
 *
 * 	虎老师从台阶 1 开始。
 *
 * 		执行第二种操作，向上走 20级台阶到台阶 2 。
 * 		执行第一种操作，向下走 1 级台阶到台阶 1 。
 *
 *
 * 	虎老师从台阶 1 开始。
 *
 * 		执行第一种操作，从台阶 1 向下走到台阶 0 。
 * 		执行第二种操作，向上走20级台阶到台阶 1 。
 * 		执行第一种操作，向下走 1 级台阶到台阶 0 。
 * 		执行第二种操作，向上走 21级台阶到台阶 2 。
 * 		执行第一种操作，向下走1 级台阶到台阶 1 。
 *
 *
 *
 * 提示：
 * 	0 <= k <= 10^9
 *
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-398/problems/find-number-of-ways-to-reach-the-k-th-stair
 * @title: 到达第 K 级台阶的方案数
 */
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "waysToReachStair", "D.txt");
    }

    Map<String, Integer> map;

    public int waysToReachStair(int k) {
        map = new HashMap<>();
        return f(1, k, 0, 0);
    }

    public int f(int i, int k, int jump, int use) {
        if (i > k + 1) { // 按照题目信息 大于 k +1 不可能回到 K
            return 0;
        }
        String key = buildKey(i, jump, use);
        if (map.containsKey(key)) {
            return map.get(key);
        }
        int ans = i == k ? 1 : 0;
        ans += f(i + (1 << jump), k, jump + 1, 0);
        if (use == 0 && i > 0) {
            ans += f(i - 1, k, jump, 1);
        }
        map.put(key, ans);
        return ans;
    }

    public String buildKey(int i, int j, int u) {
        return ":" + i + ":" + j + ":" + u;
    }


}