package leetcode.contest.weekly.w_400.w_453;

import code_generation.utils.IoUtil;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-453/problems/count-the-number-of-computer-unlocking-permutations">统计计算机解锁顺序排列数</a>
 * @title: 统计计算机解锁顺序排列数
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class,"countPermutations","B.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    public int countPermutations(int[] a) {
        long ans = 1;
        int min = 0x3ffffff;
        int n = a.length;
        for (int i = 1; i < n; i++) {
            min = Math.min(min,a[i]);
            if(min<=a[0]) return 0;
            ans *= i;
            ans %= MOD;
        }
        return (int) ans;
    }

  

}