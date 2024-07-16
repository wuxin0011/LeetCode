package leetcode.ox3if.dp.base_line.a_LCS.LCS_DP_0008;

import code_generation.utils.IoUtil;

/**
 * 1639. 通过给定词典构造目标字符串的方案数
 * <p>
 * 给你一个字符串列表 words和一个目标字符串target 。
 * words 中所有字符串都长度相同。
 * 你的目标是使用给定的 words字符串列表按照下述规则构造target：
 * 从左到右依次构造target的每一个字符。
 * 为了得到target 第i个字符（下标从 0开始），当target[i] = words[j][k]时，你可以使用words列表中第 j个字符串的第 k个字符。
 * 一旦你使用了 words中第 j个字符串的第 k个字符，你不能再使用 words字符串列表中任意单词的第 x个字符（x <= k）。也就是说，所有单词下标小于等于 k的字符都不能再被使用。
 * 请你重复此过程直到得到目标字符串target。
 * 请注意， 在构造目标字符串的过程中，你可以按照上述规定使用 words列表中 同一个字符串的 多个字符。
 * 请你返回使用 words构造 target的方案数。由于答案可能会很大，请对 10^9 + 7取余后返回。
 * （译者注：此题目求的是有多少个不同的 k序列，详情请见
 * 示例。）
 * <p>
 * 示例 1：
 * 输入：words = ["acca","bbbb","caca"], target = "aba"
 * 输出：6
 * 解释：总共有 6 种方法构造目标串。
 * "aba" -> 下标为 0 ("acca")，下标为 1 ("bbbb")，下标为 3 ("caca")
 * "aba" -> 下标为 0 ("acca")，下标为 2 ("bbbb")，下标为 3 ("caca")
 * "aba" -> 下标为 0 ("acca")，下标为 1 ("bbbb")，下标为 3 ("acca")
 * "aba" -> 下标为 0 ("acca")，下标为 2 ("bbbb")，下标为 3 ("acca")
 * "aba" -> 下标为 1 ("caca")，下标为 2 ("bbbb")，下标为 3 ("acca")
 * "aba" -> 下标为 1 ("caca")，下标为 2 ("bbbb")，下标为 3 ("caca")
 * <p>
 * 示例 2：
 * 输入：words = ["abba","baab"], target = "bab"
 * 输出：4
 * 解释：总共有 4 种不同形成 target 的方法。
 * "bab" -> 下标为 0 ("baab")，下标为 1 ("baab")，下标为 2 ("abba")
 * "bab" -> 下标为 0 ("baab")，下标为 1 ("baab")，下标为 3 ("baab")
 * "bab" -> 下标为 0 ("baab")，下标为 2 ("baab")，下标为 3 ("baab")
 * "bab" -> 下标为 1 ("abba")，下标为 2 ("baab")，下标为 3 ("baab")
 * <p>
 * 示例 3：
 * 输入：words = ["abcd"], target = "abcd"
 * 输出：1
 * <p>
 * 示例 4：
 * 输入：words = ["abab","baba","abba","baab"], target = "abba"
 * 输出：16
 * <p>
 * 提示：
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * words中所有单词长度相同。
 * 1 <= target.length <= 1000
 * words[i]和target都仅包含小写英文字母。
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/number-of-ways-to-form-a-target-string-given-a-dictionary
 * @title: 通过给定词典构造目标字符串的方案数
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "numWays", "in.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public int numWays(String[] words, String target) {
        int n = words.length, m = words[0].length();
        return 0;
    }


}