package leetcode.ox3if.slide_window.slide_0015;

import code_generation.utils.IoUtil;

/**
 *
 * 2156. 查找给定哈希值的子串
 *
 * 给定整数 p和 m，一个长度为 k且下标从 0开始的字符串s的哈希值按照如下函数计算：hash(s, p, m) = (val(s[0]) * p0 + val(s[1]) * p1 + ... + val(s[k-1]) * pk-1) mod m.其中val(s[i])表示s[i]在字母表中的下标，从val('a') = 1 到val('z') = 26。给你一个字符串s和整数power，modulo，k和hashValue。请你返回 s中 第一个 长度为 k的 子串sub，满足hash(sub, power, modulo) == hashValue。测试数据保证一定 存在至少一个这样的子串。子串 定义为一个字符串中连续非空字符组成的序列。
 *
 * 示例 1：输入：s = "leetcode", power = 7, modulo = 20, k = 2, hashValue = 0输出："ee"解释："ee" 的哈希值为 hash("ee", 7, 20) = (5 * 1 + 5 * 7) mod 20 = 40 mod 20 = 0 。"ee" 是长度为 2 的第一个哈希值为 0 的子串，所以我们返回 "ee" 。
 *
 * 示例 2：输入：s = "fbxzaad", power = 31, modulo = 100, k = 3, hashValue = 32输出："fbx"解释："fbx" 的哈希值为 hash("fbx", 31, 100) = (6 * 1 + 2 * 31 + 24 * 312) mod 100 = 23132 mod 100 = 32 。"bxz" 的哈希值为 hash("bxz", 31, 100) = (2 * 1 + 24 * 31 + 26 * 312) mod 100 = 25732 mod 100 = 32 。"fbx" 是长度为 3 的第一个哈希值为 32 的子串，所以我们返回 "fbx" 。注意，"bxz" 的哈希值也为 32 ，但是它在字符串中比 "fbx" 更晚出现。
 *
 * 提示：
 *
 *
 * 	1 <= k <= s.length <= 2 * 104
 * 	1 <= power, modulo <= 109
 * 	0 <= hashValue < modulo
 * 	s只包含小写英文字母。
 * 	测试数据保证一定 存在满足条件的子串。
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/find-substring-with-given-hash-value
 * @title: find-substring-with-given-hash-value
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "subStrHash", "in.txt");
    }



    public String subStrHash(String s, int power, int modulo, int k, int hashValue) {
        long[] pows = new long[k];
        pows[0] = 1;
        for (int i = 1; i < k; i++) {
            pows[i] = pows[i - 1] * power % modulo;
        }
        char[] cs = s.toCharArray();

        for (int i = 0; i <= cs.length - k; i++) {
            if (val(cs, i, i + k-1, pows, modulo) == hashValue) {
                return s.substring(i, i + k);
            }
        }
        return "";
    }

    private int val(char[] cs, int l, int r, long[] pows, int modulo) {
        long res = 0;
        for (int i = l; i <= r; i++) {
            res += (cs[i] - 'a' + 1) * pows[i-l];
            res %= modulo;
        }
        return (int) (res % modulo);
    }
}