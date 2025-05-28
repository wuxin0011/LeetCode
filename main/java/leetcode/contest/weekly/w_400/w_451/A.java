package leetcode.contest.weekly.w_400.w_451;

import code_generation.utils.IoUtil;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-451/problems/find-minimum-log-transportation-cost">木材运输的最小成本</a>
 * @title: 木材运输的最小成本
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"minCuttingCost","A.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    public long minCuttingCost(int n, int m, int k) {
        if(n<=k && m <= k)return 0;
        long ans=0;
        if(n>k)ans += 1L*(n - k) * k;
        if(m>k)ans += 1L*(m - k) * k;
        return ans;
	}

  

}