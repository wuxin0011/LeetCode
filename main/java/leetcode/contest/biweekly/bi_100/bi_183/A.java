package leetcode.contest.biweekly.bi_100.bi_183;

import code_generation.utils.IoUtil;
import code_generation.annotation.TestCaseGroup;
import java.util.*;
/**
 * @author: qitongwei
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/biweekly-contest-183/problems/minimum-swaps-to-move-zeros-to-end">将 0 移到末尾的最少交换次数</a>
 * @title: 将 0 移到末尾的最少交换次数
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"minimumSwaps","A.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    
    public int minimumSwaps(int[] a) {
        int n = a.length;
        int ans = 0;
        for(int l = 0,r = n - 1;l < r;){
            while(l < r && a[l] != 0)l++;
            while(l<r && a[r] == 0)r--;
            if(l < r && a[l] == 0 && a[r] != 0) {
                l++;r--;
                ans++;
            }else{
                break;
            }
        }
        return ans;
	}

  

}