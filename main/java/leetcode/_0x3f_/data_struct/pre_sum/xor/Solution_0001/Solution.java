package leetcode._0x3f_.data_struct.pre_sum.xor.Solution_0001;

import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/find-the-longest-substring-containing-vowels-in-even-counts
 * @title: 每个元音包含偶数次的最长子字符串
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "findTheLongestSubstring", "in.txt");
    }

    public int findTheLongestSubstring0(String s) {
        // 观察发现sums数组可以省去 可以一边计算前缀和一边计算答案 由于下标关系 需要保存 map.put(0,-1)
        int n = s.length();
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int ans = 0;
        int sums = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            sums = sums ^ (isVol(c) ? c : 0);
            if (map.containsKey(sums)) {
                ans = Math.max(ans, i - map.get(sums));
            } else {
                map.put(sums, i);
            }
        }
        return ans;
    }


    // 根据异或 a ^ b = 0 ===> a == b
    // 要想长度最大 记录最早下标
    public int findTheLongestSubstring(String s) {
        int n = s.length();
        int[] sums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            sums[i + 1] = sums[i] ^ (isVol(c) ? c : 0);
        }
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int i = 0; i <= n; i++) {
            if (map.containsKey(sums[i])) {
                ans = Math.max(ans, i - map.get(sums[i]));
            } else {
                map.put(sums[i], i);
            }
        }
        return ans;
    }

    public static boolean isVol(char c) {
        if ('A' <= c && c <= 'Z') {
            c = (char) (c - 'A' + 'a');
        }
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }


}