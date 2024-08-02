package leetcode._0x3f_.dichotomy.Dichotomy_0014;

import code_generation.utils.IoUtil;

/**
 * 1870. 准时到达的列车最小时速
 * <p>
 * 给你一个浮点数 hour ，表示你到达办公室可用的总通勤时间。要到达办公室，你必须按给定次序乘坐 n 趟列车。另给你一个长度为 n 的整数数组 dist ，其中 dist[i] 表示第 i 趟列车的行驶距离（单位是千米）。每趟列车均只能在整点发车，所以你可能需要在两趟列车之间等待一段时间。例如，第 1 趟列车需要 1.5 小时，那你必须再等待 0.5 小时，搭乘在第 2 小时发车的第 2 趟列车。返回能满足你准时到达办公室所要求全部列车的 最小正整数 时速（单位：千米每小时），如果无法准时到达，则返回 -1 。生成的测试用例保证答案不超过 107 ，且 hour 的 小数点后最多存在两位数字 。
 * <p>
 * 示例 1：输入：dist = [1,3,2], hour = 6输出：1解释：速度为 1 时：- 第 1 趟列车运行需要 1/1 = 1 小时。- 由于是在整数时间到达，可以立即换乘在第 1 小时发车的列车。第 2 趟列车运行需要 3/1 = 3 小时。- 由于是在整数时间到达，可以立即换乘在第 4 小时发车的列车。第 3 趟列车运行需要 2/1 = 2 小时。- 你将会恰好在第 6 小时到达。
 * <p>
 * 示例 2：输入：dist = [1,3,2], hour = 2.7输出：3解释：速度为 3 时：- 第 1 趟列车运行需要 1/3 = 0.33333 小时。- 由于不是在整数时间到达，故需要等待至第 1 小时才能搭乘列车。第 2 趟列车运行需要 3/3 = 1 小时。- 由于是在整数时间到达，可以立即换乘在第 2 小时发车的列车。第 3 趟列车运行需要 2/3 = 0.66667 小时。- 你将会在第 2.66667 小时到达。
 * <p>
 * 示例 3：输入：dist = [1,3,2], hour = 1.9输出：-1解释：不可能准时到达，因为第 3 趟列车最早是在第 2 小时发车。
 * <p>
 * 提示：
 * <p>
 * <p>
 * n == dist.length
 * 1 <= n <= 105
 * 1 <= dist[i] <= 105
 * 1 <= hour <= 109
 * hours 中，小数点后最多存在两位数字
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/minimum-speed-to-arrive-on-time
 * @title: minimum-speed-to-arrive-on-time
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "minSpeedOnTime", "in.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    //  [ 1 , 3 ,2 ] hour = 6
    //  1  1  1
    // ans = 3


    // [ 1 , 3 ,2 ] hour = 2.7
    //   (1+3-1)/3   1   0.666666 = > 2.7


    public int minSpeedOnTime(int[] dist, double hour) {

        int mx = 10000000;
        if (!check(dist, mx, hour)) {
            return -1;
        }
        int r = mx, l = 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (check(dist, mid, hour)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }


    public static boolean check(int[] dist, int speed, double hour) {
        double v = 0;
        for (int i = 0; i < dist.length; i++) {
            int d = dist[i];
            if (i == dist.length - 1) {
                // 最后一次不需要向上取整
                v += d * 1.0 / speed;
            } else {
                v += Math.ceil(d * 1.0 / speed);
                // 可以写着这样
                // v += d % speed == 0 ? d * 1.0 / speed   : ((int)(d/speed) + 1);
            }
            if (v > hour) {
                return false;
            }
        }
        return true;
    }


}