package leetcode.dichotomy.Dichotomy_0000;

import code_generation.utils.IoUtil;

/**
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