package leetcode.top_interview_150.majority_element;

import java.util.HashMap;
import java.util.Map;

/**
 * 169. 多数元素
 * <p>
 * 给定一个大小为 n 的数组nums ，返回其中的多数元素。
 * 多数元素是指在数组中出现次数 大于⌊ n/2 ⌋的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * 示例1：
 * 输入：nums = [3,2,3]
 * 输出：3
 * <p>
 * 示例2：
 * 输入：nums = [2,2,1,1,1,2,2]
 * 输出：2
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 5 * 10^4
 * -10^9 <= nums[i] <= 10^9
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/majority-element
 * @title: 多数元素
 */
public class Solution {

    public static void main(String[] args) {
        // IoUtil.testUtil(Solution.class, "majorityElement", "in.txt");
        // IoUtil.testUtil(Solution.class, "majorityElement1", "in.txt");
        localCompare();

    }


    public static void localCompare() {
        Solution so = new Solution();

        boolean ok = true;

        int T = 1000;

        while (--T > 0) {
            int v = (int) (Math.random() * 100000);
            boolean isNeg = Math.random() > 0.5;
            if (isNeg) v = -v;
            int N = (int) (Math.random() * 100000);

            int mid = N >> 1;

            int L = mid + (int) (Math.random() * mid);
            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                if (i < L) {
                    arr[i] = v;
                } else {
                    arr[i] = (int) (Math.random() * 100000);
                }
            }

            if (so.majorityElement(arr) != so.majorityElement1(arr)) {
                ok = false;
                break;
            }
        }

        System.out.println(ok ? "ok" : "fail");
    }

    private static final int MOD = (int) 1e9 + 7;

    public int majorityElement(int[] nums) {
        int n = nums.length;
        int v = nums[0];
        int cnt = 1;
        for (int i = 1; i < n; i++) {
            if (v == nums[i]) {
                cnt++;
            } else {
                cnt--;
            }
            if (cnt < 0) {
                cnt = 1;
                v = nums[i];
            }
        }
        return v;
    }


    public int majorityElement1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int num : nums) {
            int cnt = map.getOrDefault(num, 0) + 1;
            if (cnt > n / 2) {
                return num;
            }
            map.put(num, cnt);
        }
        return 0;
    }

}