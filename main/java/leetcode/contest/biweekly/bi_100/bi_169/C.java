package leetcode.contest.biweekly.bi_100.bi_169;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/biweekly-contest-169/problems/longest-non-decreasing-subarray-after-replacing-at-most-one-element">替换至多一个元素后最长非递减子数组</a>
 * @title: 替换至多一个元素后最长非递减子数组
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class,"longestSubarray","C.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    public int longestSubarray(int[] a) {
        int n = a.length;
        int ans=0;
        int[] pre = new int[n],suf = new int[n];
        Arrays.fill(pre,1);
        Arrays.fill(suf,1);
        for (int i = 1; i < n; i++) {
            if(a[i]>=a[i-1])pre[i]=pre[i-1]+1;
        }
        for (int i = n-2; i >=0; i--) {
            if(a[i]<=a[i+1])suf[i]=suf[i+1]+1;
        }
        for (int i = 0; i < n; i++) {
            ans=Math.max(ans,Math.min(n,pre[i]+1));
            ans=Math.max(ans,Math.min(n,suf[i]+1));
            if(i>=1&&pre[i]!=pre[i-1]+1){
                if(i + 1 < n && a[i + 1] >= a[i - 1]){
                    ans=Math.max(ans,pre[i-1]+suf[i]);
                }
                if(i-2>=0&&a[i]>=a[i-2]){
                    ans=Math.max(ans,pre[i-1]+suf[i]);
                }
            }
        }
        return ans;
    }

  

}