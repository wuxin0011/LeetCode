package leetcode.contest.weekly.w_300.w_399.c;

import code_generation.utils.IoUtil;

import java.util.HashMap;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-399/problems/find-the-number-of-good-pairs-ii
 * @title: 优质数对的总数 II
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "numberOfPairs", "C.txt");
    }


    public long numberOfPairs(int[] nums1, int[] nums2, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int val : nums1) {
            if (val % k != 0) {
                continue;
            }
            val /= k;
            // 枚举一半因子就可以了 另外一半通过 val / i 获得 注意  i * i == val 需要特判
            for (int i = 1; i * i <= val; i++) {
                if (val % i != 0) {
                    continue;
                }
                map.put(i, map.getOrDefault(i, 0) + 1);
                if (i * i < val) {
                    map.put(val / i, map.getOrDefault(val / i, 0) + 1);
                }
            }
        }
        long ans = 0;
        for (int val : nums2) {
            ans += map.getOrDefault(val, 0);
        }
        return ans;
    }


}