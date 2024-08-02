package leetcode._0x3f_.dichotomy.Dichotomy_0000;

import code_generation.utils.IoUtil;

/**
 *
 * LCR 073. 爱吃香蕉的狒狒
 *
 * 狒狒喜欢吃香蕉。这里有N堆香蕉，第 i 堆中有piles[i]根香蕉。警卫已经离开了，将在H小时后回来。狒狒可以决定她吃香蕉的速度K（单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉，下一个小时才会开始吃另一堆的香蕉。狒狒喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
 *
 * 示例 1：输入: piles = [3,6,7,11], H = 8输出: 4
 *
 * 示例2：输入: piles = [30,11,23,4,20], H = 5输出: 30
 *
 * 示例3：输入: piles = [30,11,23,4,20], H = 6输出: 23
 *
 * 提示：
 *
 *
 * 	1 <= piles.length <= 10^4
 * 	piles.length <= H <= 10^9
 * 	1 <= piles[i] <= 10^9
 *
 *
 *
 *
 * 注意：本题与主站 875题相同：https://leetcode-cn.com/problems/koko-eating-bananas/
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/nZZqjQ
 * @title: nZZqjQ
 */
public class Dichotomy {

    public static void main(String[] args) {
        IoUtil.testUtil(Dichotomy.class, "minEatingSpeed", "in.txt");
    }


    public int minEatingSpeed(int[] piles, int h) {
        int mx = 0;
        for (int p : piles) {
            if (mx < p) {
                mx = p;
            }
        }
        int l = 0, r = mx;
        int ans = 0;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (check(piles, mid, h)) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    public static boolean check(int[] piles, int v, int h) {
        int cnt = 0;
        for (int p : piles) {
            cnt += (long) ((p + v - 1) / v);
        }
        return cnt <= h;
    }

}