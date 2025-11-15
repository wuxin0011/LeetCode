package leetcode.contest.biweekly.bi_100.bi_169;

import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/biweekly-contest-169/problems/minimum-moves-to-equal-array-elements-iii">最小操作次数使数组元素相等 III</a>
 * @title: 最小操作次数使数组元素相等 III
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"minMoves","A.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    public int minMoves(int[] nums) {
        int mx=-0x3ffffff;
        Map<Integer,Integer> cnt= new HashMap<>();
        for(int x : nums){
            mx=Math.max(x,mx);
            cnt.merge(x,1,Integer::sum);
        }
        int ans=0;
        for(Map.Entry<Integer,Integer> item : cnt.entrySet()){
            int k=item.getKey();
            int v=item.getValue();
            ans+=(mx-k)*v;
        }

        return ans;
	}

  

}