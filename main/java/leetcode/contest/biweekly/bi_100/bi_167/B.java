package leetcode.contest.biweekly.bi_100.bi_167;

import code_generation.utils.IoUtil;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/biweekly-contest-167/problems/longest-fibonacci-subarray">最长斐波那契子数组</a>
 * @title: 最长斐波那契子数组
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "longestSubarray", "B.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public int longestSubarray(int[] a) {
        int n = a.length;
        int ans = Math.min(n,2);
        for(int i = 2;i<n;){
            if(a[i] != a[i-2] + a[i-1]){
                i++;
                continue;
            }
            int st = i;
            for(;i < n && a[i] == a[i-1] + a[i-2];i++){

            }
            ans = Math.max(ans,i - st + 2);
        }
        return ans;
    }


}