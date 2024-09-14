package leetcode.contest.weekly.w_400.w_414.c;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   https://leetcode.cn/contest/weekly-contest-414/problems/reach-end-of-array-with-max-score
 * @title: 到达数组末尾的最大得分
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class,"findMaximumScore","C.txt");
    }
     

    public long findMaximumScore(List<Integer> nums) {
        long ans = 0,mx = 0;
        for(int i = 0;i < nums.size()-1;i++) {
            mx = Math.max(mx,nums.get(i));
            ans += mx;
        }
        return ans;
	}

  

}