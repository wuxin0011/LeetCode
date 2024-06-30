package leetcode.contest.weekly.w_400.w_404.c;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 100358. 找出有效子序列的最大长度 II
 * <p>
 * 给你一个整数数组nums和一个 正整数k。
 * nums的一个子序列 sub的长度为 x，如果其满足以下条件，则称其为 有效子序列：
 * (sub[0] + sub[1]) % k == (sub[1] + sub[2]) % k == ... == (sub[x - 2] + sub[x - 1]) % k
 * 返回 nums的 最长有效子序列的长度。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,4,5], k = 2
 * 输出：5
 * 解释：
 * 最长有效子序列是[1, 2, 3, 4, 5]。
 * <p>
 * 示例 2：
 * 输入：nums = [1,4,2,3,1,4], k = 3
 * 输出：4
 * 解释：
 * 最长有效子序列是[1, 4, 1, 4]。
 * <p>
 * 提示：
 * 2 <= nums.length <= 10^3
 * 1 <= nums[i] <= 10^7
 * 1 <= k <= 10^3
 *
 * @author: wuxin0011
 * @Description:
 * @url https://leetcode.cn/problems/find-the-maximum-length-of-valid-subsequence-ii/description/
 * @title
 */
public class C {
    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "maximumLength", "C.txt");
    }


    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        if (n <= 2) return n;
        this.k = k;
        map = new int[k + 1];
        Arrays.fill(map, -1);
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (map[num % k] == -1) {
                map[num % k] = i;
            }
        }
        this.nums = nums;
        for (int v = 0; v < k; v++) {
            ans = Math.max(ans, f(v));
            if (ans == n) {
                return ans;
            }
        }
        return ans;
    }
    int ans = 0;
    int[] nums;
    int k;
    int[] map;
    public int f(int v) {
        int x = 0;
        if (v == 0) {
            x = Math.max(x, help(v, 0, 0));
            for (int i = 1; i < k; i++) {
                if (map[i] == -1 || map[k - i] == -1) {
                    continue;
                }
                x = Math.max(x, help(v, k - i, i));
            }
        } else {
            // 2
            // 4 3 k - v + 1,k - v
            // 3 4
            for (int i = 0; i <= v; i++) {
                // 0 + 2
                // 2 + 0
                // 1 + 1
                if (map[i] == -1 || map[v - i] == -1) {
                    continue;
                }

                x = Math.max(x, help(v, i, v - i));
            }
            // (7) % 5 == 2
            for (int i = v; i < k; i++) {
                if (map[i] == -1 || map[(k - i + v) % k] == -1) {
                    continue;
                }
                x = Math.max(x, help(v, i, k - i + v));
            }
        }

        return x;
    }


    public int help(int t, int a, int b) {
        int l = map[a % k];
        int n = nums.length;
        if (l == -1 || (n - l < ans)) {
            return 0;
        }
        int r = map[b % k] > l ? map[b % k] : find(l + 1, b);
        if (r == n || (n - r < ans)) {
            return 0;
        }
        int len = 1;
        while (r < n) {
            if ((nums[l] + nums[r]) % k == t) {
                l = r;
                len++;
            }
            r++;
        }
        return len;
    }

    public int find(int st, int v) {
        int n = nums.length;
        int l = st;
        while (l < n && nums[l] % k != v) {
            l++;
        }
        return l;
    }
}
