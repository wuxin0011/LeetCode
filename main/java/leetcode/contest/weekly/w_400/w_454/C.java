package leetcode.contest.weekly.w_400.w_454;

import code_generation.utils.IoUtil;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-454/problems/maximum-product-of-first-and-last-elements-of-a-subsequence">子序列首尾元素的最大乘积</a>
 * @title: 子序列首尾元素的最大乘积
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class,"maximumProduct","C.txt");
    }
    private static final int MOD = (int)1e9 + 7;

    public long maximumProduct(int[] a, int m) {
        int n = a.length;
        int[] premax = new int[n];
        int[] sufmax = new int[n];
        int[] premin = new int[n];
        int[] sufmin = new int[n];
        for(int i = 0;i < n;i++) {
            if(i==0) {
                premax[i] = a[i];
                premin[i] = a[i];
            }else{
                premax[i] = Math.max(premax[i-1],a[i]);
                premin[i] = Math.min(premin[i-1],a[i]);
            }
        }
        for(int i = n - 1;i >= 0;i--) {
            if(i==n - 1) {
                sufmax[i] = a[i];
                sufmin[i] = a[i];
            }else{
                sufmax[i] = Math.max(sufmax[i+1],a[i]);
                sufmin[i] = Math.min(sufmin[i+1],a[i]);
            }
        }
        long ans = Long.MIN_VALUE;
        for(int i = 0;i < n;i++) {
            if(i>=m - 1) {
                ans = Math.max(ans,1L * premax[i - m + 1] * sufmax[i]);
                ans = Math.max(ans,1L * premin[i - m + 1] * sufmin[i]);
            }
        }
        return ans;
    }

  

}