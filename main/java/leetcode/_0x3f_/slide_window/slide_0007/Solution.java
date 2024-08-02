package leetcode._0x3f_.slide_window.slide_0007;

import code_generation.utils.IoUtil;

/**
 * 1052. 爱生气的书店老板
 * <p>
 * 有一个书店老板，他的书店开了n分钟。每分钟都有一些顾客进入这家商店。给定一个长度为 n 的整数数组 customers ，其中 customers[i] 是在第 i 分钟开始时进入商店的顾客数量，所有这些顾客在第 i 分钟结束后离开。在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。当书店老板生气时，那一分钟的顾客就会不满意，若老板不生气则顾客是满意的。书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续minutes分钟不生气，但却只能使用一次。请你返回 这一天营业下来，最多有多少客户能够感到满意 。
 * <p>
 * 示例 1：输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], minutes = 3输出：16解释：书店老板在最后 3 分钟保持冷静。感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 * <p>
 * 示例 2：输入：customers = [1], grumpy = [0], minutes = 1输出：1
 * <p>
 * 提示：
 * <p>
 * <p>
 * n == customers.length == grumpy.length
 * 1 <= minutes <= n <= 2 * 104
 * 0 <= customers[i] <= 1000
 * grumpy[i] == 0 or 1
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/grumpy-bookstore-owner
 * @title: grumpy-bookstore-owner
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "maxSatisfied", "in.txt");
    }


    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {

        int tot = 0, ans = 0, n = customers.length;
        for (int i = 0; i < n; i++) {
            if (grumpy[i] > 0) continue;
            tot += customers[i];
        }
        for (int i = 0,v = 0 ; i < n; i++) {
            if (i >= minutes && grumpy[i - minutes] > 0) {
                v -= customers[i - minutes];
            }
            if (grumpy[i] > 0) {
                v += customers[i];
            }
            if (i >= minutes - 1) {
                ans = Math.max(v, ans);
            }
        }
        return ans + tot;
    }


}