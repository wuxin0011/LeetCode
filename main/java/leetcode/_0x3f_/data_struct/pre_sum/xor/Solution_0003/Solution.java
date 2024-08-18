package leetcode._0x3f_.data_struct.pre_sum.xor.Solution_0003;

import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/number-of-wonderful-substrings
 * @title: 最美子字符串的数目
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "wonderfulSubstrings", "in.txt");
    }


    public long wonderfulSubstrings(String word) {
        int[] a = word.chars().map(x -> x - 'a' + 1).toArray();
        Map<Integer, Long> cnts = new HashMap<>();
        long ans = 0;
        int mask = 0;
        cnts.put(0, 1L);
        for (int k : a) {
            mask ^= 1 << k;
            ans += cnts.getOrDefault(mask, 0L);
            for (int j = 1; j <= 26; j++) {
                ans += cnts.getOrDefault(mask ^ (1 << j), 0L);
            }
            cnts.put(mask, cnts.getOrDefault(mask, 0L) + 1);
        }
        return ans;
    }


}