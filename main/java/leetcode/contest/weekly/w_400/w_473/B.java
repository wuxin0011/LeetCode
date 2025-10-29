package leetcode.contest.weekly.w_400.w_473;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-473/problems/maximum-alternating-sum-of-squares">最大交替平方和</a>
 * @title: 最大交替平方和
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class,"maxAlternatingSum","B.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    public long maxAlternatingSum(int[] a) {
        int n = a.length;
        long[] b = new long[n];
        for(int i = 0;i<n;i++) {
            b[i] = 1L * a[i] * a[i];
        }
        Arrays.sort(b);
        long ans = 0;
        int cnt = n % 2 == 0 ? n / 2 : (n / 2 + 1);
        for(int i = n - 1;i >= 0;i--,cnt--){
            if(cnt > 0) ans += b[i];
            else ans -= b[i];
        }
        return ans;
	}

  

}