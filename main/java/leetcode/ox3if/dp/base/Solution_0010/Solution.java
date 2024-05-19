package leetcode.ox3if.dp.base.Solution_0010;

import code_generation.utils.IoUtil;

/**
 * 2606. 找到最大开销的子字符串
 * <p>
 * 给你一个字符串s，一个字符互不相同的字符串chars和一个长度与 chars相同的整数数组vals。
 * 子字符串的开销是一个子字符串中所有字符对应价值之和。空字符串的开销是 0。
 * 字符的价值定义如下：
 * 如果字符不在字符串chars中，那么它的价值是它在字母表中的位置（下标从 1开始）。
 * <p>
 * 比方说，'a'的价值为1，'b'的价值为2，以此类推，'z'的价值为26。
 * <p>
 * <p>
 * 否则，如果这个字符在 chars中的位置为 i，那么它的价值就是vals[i]。
 * 请你返回字符串 s的所有子字符串中的最大开销。
 * <p>
 * 示例 1：
 * 输入：s = "adaa", chars = "d", vals = [-1000]
 * 输出：2
 * 解释：字符 "a" 和 "d" 的价值分别为 1 和 -1000 。
 * 最大开销子字符串是 "aa" ，它的开销为 1 + 1 = 2 。
 * 2 是最大开销。
 * <p>
 * 示例 2：
 * 输入：s = "abc", chars = "abc", vals = [-1,-1,-1]
 * 输出：0
 * 解释：字符 "a" ，"b" 和 "c" 的价值分别为 -1 ，-1 和 -1 。
 * 最大开销子字符串是 "" ，它的开销为 0 。
 * 0 是最大开销。
 * <p>
 * 提示：
 * 1 <= s.length <= 10^5
 * s只包含小写英文字母。
 * 1 <= chars.length <= 26
 * chars只包含小写英文字母，且 互不相同。
 * vals.length == chars.length
 * -1000 <= vals[i] <= 1000
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/find-the-substring-with-maximum-cost
 * @title: 找到最大开销的子字符串
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "maximumCostSubstring", "in.txt");
        IoUtil.testUtil(Solution.class, "maximumCostSubstring2", "in.txt");
    }


    public int maximumCostSubstring(String s, String chars, int[] vals) {

        int ans = 0;
        int tot = 0;
        int[] h = new int[26];
        boolean[] vis = new boolean[26];
        for (int i = 0; i < chars.length(); i++) {
            char c = chars.charAt(i);
            h[c - 'a'] = vals[i];
            vis[c - 'a'] = true;
        }
        for (int i = 0; i < h.length; i++) {
            if (vis[i]) continue;
            h[i] = i + 1;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            tot += h[c - 'a'];
            ans = Math.max(tot, ans);
            if (tot <= 0) {
                tot = 0;
            }
        }
        return ans;
    }

    public int maximumCostSubstring2(String s, String chars, int[] vals) {
        int ans = 0;
        int tot = 0;
        int[] h = new int[26];
        for (int i = 0; i < h.length; i++) {
            h[i] = i + 1;
        }
        for (int i = 0; i < chars.length(); i++) {
            char c = chars.charAt(i);
            int id = c - 'a';
            h[id] = vals[i];
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            tot += h[c - 'a'];
            ans = Math.max(tot, ans);
            if (tot <= 0) {
                tot = 0;
            }
        }
        return ans;
    }


}