package leetcode.contest.biweekly.bi_100.bi_134.d;

import code_generation.utils.IoUtil;

/**
 * 3209. 子数组按位与值为 K 的数目
 * <p>
 * <p>
 * 给你一个整数数组nums和一个整数k，请你返回nums中有多少个子数组满足：子数组中所有元素按位AND的结果为 k。
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,1], k = 1
 * 输出：6
 * 解释：
 * 所有子数组都只含有元素 1 。
 * <p>
 * 示例 2：
 * 输入：nums = [1,1,2], k = 1
 * 输出：3
 * 解释：
 * 按位AND值为 1 的子数组包括：[1,1,2], [1,1,2], [1,1,2]。
 * <p>
 * 示例 3：
 * 输入：nums = [1,2,3], k = 2
 * 输出：2
 * 解释：
 * 按位AND值为 2 的子数组包括：[1,2,3], [1,2,3]。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i], k <= 10^9
 *
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/biweekly-contest-134/problems/number-of-subarrays-with-and-value-of-k
 * @title: 子数组按位与值为 K 的数目
 */
public class D {


    static class s0 {
        public static void main(String[] args) {
            IoUtil.testUtil(s0.class, "countSubarrays", "D.txt");
        }

        // 双指针
        public long countSubarrays(int[] a, int k) {
            int n = a.length;
            long ans = 0;
            int l = 0, r = 0;
            for (int i = 0; i < n; i++) {
                int x = a[i];
                for (int j = i - 1; j >= 0; j--) {
                    if ((a[j] & x) == a[j]) {
                        break;
                    }
                    a[j] &= x;
                }
                while (l <= i && a[l] < k) {
                    l++;
                }
                while (r <= i && a[r] <= k) {
                    r++;
                }
                // (l,r]
                ans += r - l;
            }

            return ans;
        }


    }

    // 二分
    static class s1 {
        public static void main(String[] args) {
            IoUtil.testUtil(s1.class, "countSubarrays", "D.txt");
        }


        public long countSubarrays(int[] a, int k) {
            int n = a.length;
            long ans = 0;
            for (int i = 0; i < n; i++) {
                int x = a[i];
                for (int j = i - 1; j >= 0; j--) {
                    if ((a[j] & x) == a[j]) {
                        break;
                    }
                    a[j] &= x;
                }
                // 二分 k 的第一个位置和最后一个位置
                int l = lower_bound(a, k, 0, i);
                int r = lower_bound(a, k + 1, 0, i);
                // [l,r)
                ans += r - l;
            }

            return ans;
        }

        public static int lower_bound(int[] a, int target, int l, int r) {
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                if (a[mid] >= target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            return l;
        }

    }

}