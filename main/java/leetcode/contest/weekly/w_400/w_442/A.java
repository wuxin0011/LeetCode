package leetcode.contest.weekly.w_400.w_442;

import code_generation.utils.IoUtil;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-442/problems/maximum-containers-on-a-ship">船上可以装载的最大集装箱数量</a>
 * @title: 船上可以装载的最大集装箱数量
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"maxContainers","A.txt");
    }
     

    public int maxContainers(int n, int w, int maxWeight) {
        int cnt = 0,tot = 0;
        for(int i = 0;i < n * n;i++) {
            tot += w;
            if(tot > maxWeight) break;
            cnt++;
        }
        return cnt;
	}

  

}