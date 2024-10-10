package leetcode.contest.biweekly.bi_100.bi_140.a;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   https://leetcode.cn/contest/biweekly-contest-140/problems/minimum-element-after-replacement-with-digit-sum
 * @title: 替换为数位和以后的最小元素
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"minElement","A.txt");
    }
     

    public int minElement(int[] nums) {
        int n = nums.length;
        int ans = 0x3fffff;
        for(int x : nums) {
            int cur = 0;
            while(x > 0) {
                cur += x % 10;
                x /= 10;
            }
            ans = Math.min(ans,cur);
        }
        return ans;
	}

  

}