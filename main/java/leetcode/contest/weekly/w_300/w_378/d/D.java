package leetcode.contest.weekly.w_300.w_378.d;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 * 2983. 回文串重新排列查询
 *
 * 给你一个长度为 偶数n，下标从 0开始的字符串s。
 * 同时给你一个下标从 0开始的二维整数数组queries，其中queries[i] = [ai, bi, ci, di]。
 * 对于每个查询i，你需要执行以下操作：
 * 	将下标在范围0 <= ai <= bi < n / 2内的子字符串s[ai:bi]中的字符重新排列。
 * 	将下标在范围 n / 2 <= ci <= di < n内的 子字符串s[ci:di]中的字符重新排列。
 * 对于每个查询，你的任务是判断执行操作后能否让 s变成一个 回文串 。
 * 每个查询与其他查询都是 独立的。
 * 请你返回一个下标从 0开始的数组answer，如果第i个查询执行操作后，可以将s变为一个回文串，那么answer[i] =true，否则为false。
 * 	子字符串指的是一个字符串中一段连续的字符序列。
 * 	s[x:y]表示 s中从下标 x到 y且两个端点 都包含的子字符串。
 *
 * 示例 1：
 * 输入：s = "abcabc", queries = [[1,1,3,5],[0,2,5,5]]
 * 输出：[true,true]
 * 解释：这个例子中，有 2 个查询：
 * 第一个查询：
 * - a0 = 1, b0 = 1, c0 = 3, d0 = 5
 * - 你可以重新排列 s[1:1] => abcabc 和 s[3:5] => abcabc。
 * - 为了让 s 变为回文串，s[3:5] 可以重新排列得到 => abccba 。
 * - 现在 s 是一个回文串。所以 answer[0] = true 。
 * 第二个查询：
 * - a1 = 0, b1 = 2, c1 = 5, d1 = 5.
 * - 你可以重新排列 s[0:2] => abcabc 和 s[5:5] => abcabc。
 * - 为了让 s 变为回文串，s[0:2] 可以重新排列得到 => cbaabc 。
 * - 现在 s 是一个回文串，所以 answer[1] = true 。
 *
 * 示例 2：
 * 输入：s = "abbcdecbba", queries = [[0,2,7,9]]
 * 输出：[false]
 * 解释：这个
 * 示例中，只有一个查询。
 * a0 = 0, b0 = 2, c0 = 7, d0 = 9.
 * 你可以重新排列 s[0:2] => abbcdecbba 和 s[7:9] => abbcdecbba。
 * 无法通过重新排列这些子字符串使 s 变为一个回文串，因为 s[3:6] 不是一个回文串。
 * 所以 answer[0] = false 。
 *
 * 示例 3：
 * 输入：s = "acbcab", queries = [[1,2,4,5]]
 * 输出：[true]
 * 解释：这个
 * 示例中，只有一个查询。
 * a0 = 1, b0 = 2, c0 = 4, d0 = 5.
 * 你可以重新排列 s[1:2] => acbcab 和 s[4:5] => acbcab。
 * 为了让 s 变为回文串，s[1:2] 可以重新排列得到 => abccab。
 * 然后 s[4:5] 重新排列得到 abccba。
 * 现在 s 是一个回文串，所以 answer[0] = true 。
 *
 * 提示：
 * 	2 <= n == s.length <= 10^5
 * 	1 <= queries.length <= 10^5
 * 	queries[i].length == 4
 * 	ai == queries[i][0], bi == queries[i][1]
 * 	ci == queries[i][2], di == queries[i][3]
 * 	0 <= ai <= bi < n / 2
 * 	n / 2 <= ci <= di < n
 * 	n是一个偶数。
 * 	s 只包含小写英文字母。
 *
 *
 * @author: agitated-curranfnd
 * @Description:
 * @url:   https://leetcode.cn/contest/weekly-contest-378/problems/palindrome-rearrangement-queries
 * @title: 回文串重新排列查询
 */
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class,"canMakePalindromeQueries","D.txt");
    }
     

    public boolean[] canMakePalindromeQueries(String s, int[][] queries) {    

        return null; 
	}

  

}