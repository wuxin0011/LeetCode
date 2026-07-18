package leetcode.contest.biweekly.bi_100.bi_187;

import code_generation.utils.IoUtil;
import code_generation.annotation.TestCaseGroup;
import java.util.*;
/**
 * @author: qitongwei
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/biweekly-contest-187/problems/minimum-adjacent-swaps-to-partition-array">划分数组的最少相邻交换次数</a>
 * @title: 划分数组的最少相邻交换次数
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class,"minAdjacentSwaps","C.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    
    public int minAdjacentSwaps(int[] a, int x, int y) {
        int n = a.length;
        int cnt2 = 0,cnt3 = 0;
        long ans = 0;
        for(int i = 0;i < n;i++){
            int v = a[i];
            if(v < x){
                ans += cnt2 + cnt3;
                ans %= MOD;
            }else if(x <= v && v <= y){
                ans += cnt3;
                ans %= MOD;
                cnt2++;
            }else{
                cnt3++;
            }
        }
        return (int)(ans % MOD);
	}

  

}