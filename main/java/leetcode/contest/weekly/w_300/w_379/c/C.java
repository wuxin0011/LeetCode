package leetcode.contest.weekly.w_300.w_379.c;

import code_generation.utils.IoUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: wuxin0011
 * @Description:
 * @url https://leetcode.cn/problems/maximum-size-of-a-set-after-removals/description/
 * @title
 */
public class C {
    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "maximumSetSize", "C.txt");
    }

    public int maximumSetSize(int[] nums1, int[] nums2) {
        Set<Integer> s1 = init(nums1);
        Set<Integer> s2 = init(nums2);
        int common = 0;
        for (int num : s1) {
            if (s2.contains(num)) common++;
        }
        int n1 = s1.size();
        int n2 = s2.size();
        int ans = n1 + n2 - common;

        int m = nums1.length >>> 1;
        if (n1 > m) {
            int mn = Math.min(n1 - m, common);
            ans -= n1 - mn - m;
            common -= mn;
        }

        if (n2 > m) {
            n2 -= Math.min(n2 - m, common);
            ans -= n2 - m;
        }

        return ans;
    }

    public Set<Integer> init(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        return set;
    }
}
