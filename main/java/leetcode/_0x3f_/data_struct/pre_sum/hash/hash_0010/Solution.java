package leetcode._0x3f_.data_struct.pre_sum.hash.hash_0010;

import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/find-longest-subarray-lcci
 * @title: 字母与数字
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "findLongestSubarray", "in.txt");
    }


    public String[] findLongestSubarray(String[] ss) {
        int n = ss.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + (isNumber(ss[i].charAt(0)) ? 1 : -1);
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int l = 0, mx = 0;
        for (int r = 0; r < s.length; r++) {
            if (map.containsKey(s[r])) {
                int left = map.get(s[r]);
                if (r - left > mx) {
                    mx = r - left;
                    l = left;
                }
            } else {
                map.put(s[r], r);
            }
        }
        String[] ans = new String[mx];
        System.arraycopy(ss, l, ans, 0,ans.length);
        return ans;
    }

    public static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }


}