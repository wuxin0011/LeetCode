package leetcode._0x3f_.data_struct.pre_sum.hash.hash_0009;

import code_generation.utils.IoUtil;

import java.util.HashSet;
import java.util.Set;
/**
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/maximum-number-of-non-overlapping-subarrays-with-sum-equals-target
 * @title: 和为目标值且不重叠的非空子数组的最大数目
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "maxNonOverlapping", "in.txt");
    }

    // 贪心 + 前缀和

    public int maxNonOverlapping(int[] a, int t) {
        Set<Integer> set = new HashSet<>();
        int s = 0;
        int ans = 0;
        set.add(0);
        for (int v : a) {
            s += v;
            // s - (s-t) = t
            if (set.contains(s - t)) {
                set.clear(); // 贪心
                s = 0;
                ans++;
                set.add(0);
            } else {
                set.add(s);
            }

        }
        return ans;
    }


}