package leetcode.contest.weekly.w_400.w_443;

import code_generation.utils.IoUtil;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-443/problems/minimum-cost-to-reach-every-position">到达每个位置的最小费用</a>
 * @title: 到达每个位置的最小费用
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"minCosts","A.txt");
    }
     

    public int[] minCosts(int[] cost) {
        for(int i = 1;i < cost.length;i++) {
            cost[i] = Math.min(cost[i-1],cost[i]);
        }
        return cost;
	}

  

}