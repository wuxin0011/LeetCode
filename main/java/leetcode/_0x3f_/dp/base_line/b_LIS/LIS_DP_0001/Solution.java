package leetcode._0x3f_.dp.base_line.b_LIS.LIS_DP_0001;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: wuxin0011
 * @Description:
 * @url:   <a href="https://leetcode.cn/problems/number-of-longest-increasing-subsequence">最长递增子序列的个数</a>
 * @title: 最长递增子序列的个数
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"findNumberOfLIS","in.txt");
    }
     

    // 这题有点构造题味道
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] g = new int[n],f = new int[n];
        Arrays.fill(f,1);
        Arrays.fill(g,1);

        int mx = 0;
        for (int i = 0; i < n; i++) {
            for(int j = 0;j < i;j++) {
                if(nums[j] >= nums[i]) continue;
                if(f[i] < f[j] + 1) {
                    f[i] = f[j] + 1;
                    g[i] = g[j];
                }else if(f[i] == f[j] + 1) {
                    g[i] += g[j];
                }
            }
            mx = Math.max(f[i],mx);
        }
        int ans = 0;
        for(int i = 0;i < n;i++) {
            if(f[i] == mx) {
                ans += g[i];
            }
        }
        return ans;
    }

  

}