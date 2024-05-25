package leetcode.contest.weekly.w_300.w_379.c;

import code_generation.utils.IoUtil;

import java.util.HashSet;
import java.util.Set;
/**
 * 3002. 移除后集合的最多元素数
 * <p>
 * 给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，它们的长度都是偶数 n 。
 * 你必须从 nums1 中移除 n / 2 个元素，同时从 nums2 中也移除 n / 2 个元素。移除之后，你将 nums1 和 nums2 中剩下的元素插入到集合 s 中。
 * 返回集合 s可能的 最多 包含多少元素。
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,2,1,2], nums2 = [1,1,1,1]
 * 输出：2
 * 解释：从 nums1 和 nums2 中移除两个 1 。移除后，数组变为 nums1 = [2,2] 和 nums2 = [1,1] 。因此，s = {1,2} 。
 * 可以证明，在移除之后，集合 s 最多可以包含 2 个元素。
 * <p>
 * 示例 2：
 * 输入：nums1 = [1,2,3,4,5,6], nums2 = [2,3,2,3,2,3]
 * 输出：5
 * 解释：从 nums1 中移除 2、3 和 6 ，同时从 nums2 中移除两个 3 和一个 2 。移除后，数组变为 nums1 = [1,4,5] 和 nums2 = [2,3,2] 。因此，s = {1,2,3,4,5} 。
 * 可以证明，在移除之后，集合 s 最多可以包含 5 个元素。
 * <p>
 * 示例 3：
 * 输入：nums1 = [1,1,2,2,3,3], nums2 = [4,4,5,5,6,6]
 * 输出：6
 * 解释：从 nums1 中移除 1、2 和 3 ，同时从 nums2 中移除 4、5 和 6 。移除后，数组变为 nums1 = [1,2,3] 和 nums2 = [4,5,6] 。因此，s = {1,2,3,4,5,6} 。
 * 可以证明，在移除之后，集合 s 最多可以包含 6 个元素。
 * <p>
 * 提示：
 * n == nums1.length == nums2.length
 * 1 <= n <= 2 * 10^4
 * n是偶数。
 * 1 <= nums1[i], nums2[i] <= 10^9
 *
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-379/problems/maximum-size-of-a-set-after-removals
 * @title: 移除后集合的最多元素数
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class,"maximumSetSize","C.txt");
    }


    public int maximumSetSize(int[] nums1, int[] nums2) {

        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for (int num : nums1) {
            set1.add(num);
        }

        for (int num : nums2) {
            set2.add(num);
        }

        // 交集
        int common = 0;
        for (int v : set1) {
            if (set2.contains(v)) common++;
        }

        int n1 = set1.size();
        int n2 = set2.size();
        int m = nums1.length >> 1;

        // 容斥原理
        int ans = n1 + n2 - common;

        if (n1 > m) {
            int mn = Math.min(common, n1 - m);
            n1 -= mn;
            common -= mn;
            ans -= (n1 - m);
        }


        if (n2 > m) {
            int mn = Math.min(n2 - m, common);
            n2 -= mn;
            ans -= (n2 - m);
        }

        return ans;
    }

  

}