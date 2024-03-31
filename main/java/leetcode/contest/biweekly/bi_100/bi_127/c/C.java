package leetcode.contest.biweekly.bi_100.bi_127.c;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 * @url
 * @title
 */
public class C {
    public static void main(String[] args) {

        IoUtil.testUtil(C.class, IoUtil.DEFAULT_METHOD_NAME, "C.txt");

    }

    public int minimumSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        int left = 0; // 左指针
        int ans = n + 1; // 初始化最小长度为数组长度加一
        int v = 0; // 记录窗口中元素的按位或结果
        int[] h = new int[32];

        for (int right = 0; right < n; right++) {
            v = init(h, nums[right], true);
            while (left <= right && v >= k) {
                ans = Math.min(ans,right - left + 1);
                v = init(h, nums[left], false);
                left++;
            }
        }

        return ans == n + 1 ? -1 : ans;
    }

    public static int init(int[] h, int v, boolean add) {
        char[] cs = new char[32];
        Arrays.fill(cs, '0');
        for (int i = cs.length - 1; i >= 0; i--) {
            cs[i] = (v & 1) == 1 ? '1' : '0';
            if (cs[i] == '1') {
                if (add) {
                    h[i]++;
                } else {
                    h[i]--;
                }
            }
            v >>>= 1;
        }
        int tot = 0;
        for (int i = 31; i >= 0; i--) {
            if (h[i] > 0) {
                tot |= (1 << (31 - i));
            }
        }
        return tot;
    }


}
