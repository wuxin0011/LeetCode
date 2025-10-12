package leetcode.contest.weekly.w_400.w_471;

import code_generation.utils.IoUtil;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-471/problems/sum-of-elements-with-frequency-divisible-by-k">出现次数能被 K 整除的元素总和</a>
 * @title: 出现次数能被 K 整除的元素总和
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"sumDivisibleByK","A.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    public int sumDivisibleByK(int[] a, int k) {
        int[] cnt = new int[101];
        for(int x : a) cnt[x]++;
        int ans = 0;
        for(int i = 0;i<=100;i++) {
            if(cnt[i]==0)continue;
            if(cnt[i]%k != 0)continue;
            ans += cnt[i] * i;
        }
        return ans;
	}

  

}