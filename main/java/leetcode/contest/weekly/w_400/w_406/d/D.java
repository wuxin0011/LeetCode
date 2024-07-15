package leetcode.contest.weekly.w_400.w_406.d;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   https://leetcode.cn/contest/weekly-contest-406/problems/minimum-cost-for-cutting-cake-ii
 * @title: 切蛋糕的最小总开销 II
 */
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class,"minimumCost","D.txt");
    }


    public long minimumCost(int m, int n, int[] hor, int[] ver) {
        m--;
        n--;
        Arrays.sort(hor);
        Arrays.sort(ver);
        int r = m - 1, c = n - 1;
        int hcnt = 1, vcnt = 1;
        long ans = 0;
        while (r >= 0 && c >= 0) {
            if (hor[r] >= ver[c]) {
                ans += hor[r] * vcnt;
                hcnt++;
                r--;
            } else {
                ans += ver[c] * hcnt;
                vcnt++;
                c--;
            }
        }
        while (r >= 0) {
            ans += hor[r] * vcnt;
            hcnt++;
            r--;
        }
        while (c >= 0) {
            ans += ver[c] * hcnt;
            vcnt++;
            c--;
        }
        return ans;
    }

  

}