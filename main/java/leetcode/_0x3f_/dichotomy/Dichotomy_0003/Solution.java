package leetcode._0x3f_.dichotomy.Dichotomy_0003;

import code_generation.utils.IoUtil;

/**
 *
 *
 * 2141. 同时运行 N 台电脑的最长时间
 *
 * 你有n台电脑。给你整数n和一个下标从 0开始的整数数组batteries，其中第i个电池可以让一台电脑 运行batteries[i]分钟。你想使用这些电池让全部n台电脑 同时运行。一开始，你可以给每台电脑连接 至多一个电池。然后在任意整数时刻，你都可以将一台电脑与它的电池断开连接，并连接另一个电池，你可以进行这个操作 任意次。新连接的电池可以是一个全新的电池，也可以是别的电脑用过的电池。断开连接和连接新的电池不会花费任何时间。注意，你不能给电池充电。请你返回你可以让 n台电脑同时运行的 最长分钟数。
 *
 * 示例 1：输入：n = 2, batteries = [3,3,3]输出：4解释：一开始，将第一台电脑与电池 0 连接，第二台电脑与电池 1 连接。2 分钟后，将第二台电脑与电池 1 断开连接，并连接电池 2 。注意，电池 0 还可以供电 1 分钟。在第 3 分钟结尾，你需要将第一台电脑与电池 0 断开连接，然后连接电池 1 。在第 4 分钟结尾，电池 1 也被耗尽，第一台电脑无法继续运行。我们最多能同时让两台电脑同时运行 4 分钟，所以我们返回 4 。
 *
 * 示例 2：输入：n = 2, batteries = [1,1,1,1]输出：2解释：一开始，将第一台电脑与电池 0 连接，第二台电脑与电池 2 连接。一分钟后，电池 0 和电池 2 同时耗尽，所以你需要将它们断开连接，并将电池 1 和第一台电脑连接，电池 3 和第二台电脑连接。1 分钟后，电池 1 和电池 3 也耗尽了，所以两台电脑都无法继续运行。我们最多能让两台电脑同时运行 2 分钟，所以我们返回 2 。
 *
 * 提示：
 *
 *
 * 	1 <= n <= batteries.length <= 105
 * 	1 <= batteries[i] <= 109
 *
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/maximum-running-time-of-n-computers
 * @title: maximum-running-time-of-n-computers
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "maxRunTime", "in.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public long maxRunTime(int n, int[] batteries) {
        long sum = 0;
        long mx = 0;
        for (int battery : batteries) {
            sum += battery;
            if (battery > mx) {
                mx = battery;
            }
        }
        long l = 0, r = sum,ans = 0;
        // 最大值小于和 能够满足条件
        if (mx * n <= sum) {
            return sum / n;
        }
        while (l <= r) {
            long mid = l + ((r - l) >> 1);
            if (check(batteries, mid, n)) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }


    public static boolean check(int[] batteries, long t, int n) {
        long sum = 0;
        for (int battery : batteries) {
            if (battery > t) {
                n--;
            }else{
                sum += battery;
            }
            // 如果遇到 tot >= n*t 就OK
            if(sum>=n*t){
                return true;
            }

        }
        return false;
    }

}