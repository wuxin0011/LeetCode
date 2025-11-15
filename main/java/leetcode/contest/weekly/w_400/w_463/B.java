package leetcode.contest.weekly.w_400.w_463;

import code_generation.utils.IoUtil;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-463/problems/xor-after-range-multiplication-queries-i">区间乘法查询后的异或 I</a>
 * @title: 区间乘法查询后的异或 I
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class,"xorAfterQueries","B.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    public int xorAfterQueries(int[] a, int[][] queries) {
        int ans = 0,n=a.length;
        for(int[] q : queries){
            int l = q[0],r = q[1],k = q[2],v = q[3];
            int i = l;
            while(i<=r&&i<n){
                a[i]  = (int)((1L * a[i] * v) % MOD);
                i+=k;
            }
        }
        for (int x : a) {
            ans ^= x;
        }
        return ans;
    }

  

}