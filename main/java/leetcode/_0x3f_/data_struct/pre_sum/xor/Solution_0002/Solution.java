package leetcode._0x3f_.data_struct.pre_sum.xor.Solution_0002;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/find-longest-awesome-substring
 * @title: 找出最长的超赞子字符串
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "longestAwesome", "in.txt");
    }


    public int longestAwesome(String s) {
        int[] a = s.chars().map(x -> x - '0').toArray();
        int n = a.length;
        int mask = 0;
        int[] map = new int[1 << 10];
        Arrays.fill(map, -2);
        map[0] = -1;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            mask ^= 1 << a[i];
            if (map[mask] != -2) {
                ans = Math.max(ans, i - map[mask]);
            }
            for (int j = 0; j < 10; j++) {
                int x = mask ^ (1 << j);
                if (map[x] != -2) {
                    ans = Math.max(ans, i - map[x]);
                    break;
                }
            }

            if (map[mask] == -2) {
                map[mask] = i;
            }
        }
        return ans;
    }


}