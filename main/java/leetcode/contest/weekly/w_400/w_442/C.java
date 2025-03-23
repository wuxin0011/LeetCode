package leetcode.contest.weekly.w_400.w_442;

import code_generation.utils.IoUtil;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-442/problems/find-the-minimum-amount-of-time-to-brew-potions">酿造药水需要的最少总时间</a>
 * @title: 酿造药水需要的最少总时间
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class,"minTime","C.txt");
    }


    public long minTime(int[] a, int[] b) {
        int n = a.length;
        long[] s = new long[n];
        for(int m : b){
            long t = 0;
            for(int i = 0;i < n;i++) {
                t = Math.max(t,s[i]) + m * a[i];
            }
            s[n - 1] = t;
            for(int i = n - 2;i >= 0;i--) {
                t -= a[i + 1] * m;
                s[i] = t;
            }
        }
        return s[n - 1];
    }

  

}