package leetcode._0x3f_.dp.base_line.b_LIS.LIS_DP_0000;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: wuxin0011
 * @Description:
 * @url:   <a href="https://leetcode.cn/problems/longest-increasing-subsequence">最长递增子序列</a>
 * @title: 最长递增子序列
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"lengthOfLIS","in.txt");
    }


    // https://leetcode.cn/problems/longest-increasing-subsequence/submissions/575958128/

    // 二分 + 贪心
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] g = new int[n];
        int size = 0;
        for (int num : nums) {
            // 二分查找 当前 num ，在 g 数组中的位置
            int j = lowerbound(g, size, num);
            // 贪心策略：如果后面遇到更小而且没有越界的应该替换当前位置内容，因为 从贪心角度来说前面越小 后面构成子序列一定 >= 不替换的
            if (j >= size) {
                g[size++] = num;
            } else {
                g[j] = num;
            }
        }
        // System.out.println(Arrays.toString(Arrays.copyOf(g,size)));
        return size;
    }

    @SuppressWarnings("all")
    static int lowerbound(int[] a,int size,int t) {
        int l = 0,r = size - 1;
        while(l <= r) {
            int mid = l + ((r - l)>>1);
            if(a[mid]>=t) {
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return l;
    }

  

}