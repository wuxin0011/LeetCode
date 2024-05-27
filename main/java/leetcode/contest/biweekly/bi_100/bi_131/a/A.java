package leetcode.contest.biweekly.bi_100.bi_131.a;

import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wuxin0011
 * @Description:
 * @url
 * @title
 */
public class A {
    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "duplicateNumbersXOR", "A.txt");
    }

    public int duplicateNumbersXOR(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();
        boolean find = false;
        int ans = 0;
        for (int num : nums) {
            int cnt = map.getOrDefault(num, 0) + 1;
            map.put(num, cnt);
            if (cnt >= 2) {
                find = true;
                ans = num;
            }
        }
        if (!find) {
            return 0;
        }
        int val = ans;
        for (Map.Entry<Integer, Integer> item : map.entrySet()) {
            if (item.getValue() >= 2 && item.getKey() != val) {
                ans ^= item.getKey();
            }
        }
        return ans;
    }
}
