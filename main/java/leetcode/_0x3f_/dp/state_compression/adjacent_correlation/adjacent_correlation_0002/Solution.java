package leetcode._0x3f_.dp.state_compression.adjacent_correlation.adjacent_correlation_0002;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/minimum-incompatibility
 * @title: 最小不兼容性
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "minimumIncompatibility", "in.txt");
    }


    // 先找能够划分 m = n / k 长度为 的集合 记 g
    // g0 记为 g 的补集 g0 | g 就是全集 U
    // https://leetcode.cn/problems/minimum-incompatibility/submissions/582323547/

    public int minimumIncompatibility(int[] nums, int k) {
        int n = nums.length;
        if (n % k != 0) {
            return -1;
        }
        int inf = 1 << 30;
        int m = n / k;
        int S = 1 << n; // 全集
        int[] g = new int[S];
        Arrays.fill(g,-1);
        for (int i = 0; i < S; i++) {
            if (Integer.bitCount(i) != m) {
                continue;
            }
            int set = 0, mx = 0, mi = inf;
            // 找出构成长度为m的集合记录 max - min
            for (int j = 0; j < n; j++) {
                int x = nums[j];
                if ((i >> j & 1) == 1) {
                    // 题目要求每个长度为m中不能有重复数字
                    if ((set >> x & 1) == 1) {
                        break;
                    }

                    set |= 1 << x;
                    mx = Math.max(mx,x);
                    mi = Math.min(mi,x);
                }
            }
            if (Integer.bitCount(set) == m) {
                g[i] = mx - mi;
            }
        }


        int[] g0 = new int[S];
        Arrays.fill(g0,inf);
        g0[0] = 0;
        for(int i = 0;i < S;i++) {
            if(g0[i] == inf)continue;
            int set = 0,mask = 0;
            for(int j = 0;j < n;j++) {
                int x = nums[j];
                if((i >> j & 1) == 0 && (set >> x & 1 ) == 0) {
                    set  |= 1 << x;
                    mask |= 1 << j;
                }
            }

            // 和 g[i] 构成集合的 g0 集合大小不能小于 m
            if(Integer.bitCount(mask) < m) {
                continue;
            }
            // 子集 的枚举
            for(int j  = mask;j > 0;j = (j - 1) & mask) {
                if(g[j] != -1) {
                    g0[i | j] = Math.min(g0[i | j],g0[i] + g[j]);
                }
            }


        }



        return g0[S - 1] != inf ? g0[S-1] : -1;
    }


}