package leetcode.solution.Solution_0004;

import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/tuple-with-same-product
 * @title: tuple-with-same-product
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "tupleSameProduct", "in.txt");
    }


    public int tupleSameProduct(int[] nums) {
        int cnt = 0;
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int a = 0; a < n; a++) {
            for (int b = a + 1; b < n; b++) {
                int k = nums[a] * nums[b];
                map.put(k, map.getOrDefault(k, 0) + 1);
            }
        }
        for (Map.Entry<Integer, Integer> item : map.entrySet()) {
            int v = item.getValue();
            if (v > 1) {
                cnt += v * (v - 1) / 2;
            }
        }
        return cnt << 3;
    }


}