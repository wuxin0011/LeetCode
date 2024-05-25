package leetcode.contest.weekly.w_300.w_379.d;

import code_generation.utils.IoUtil;
import java.util.*;



/**
 *
 * 3003. 执行操作后的最大分割数量
 *
 * 给你一个下标从 0 开始的字符串s和一个整数k。
 * 你需要执行以下分割操作，直到字符串s变为空：
 * 	选择s的最长前缀，该前缀最多包含k个不同字符。
 * 	删除这个前缀，并将分割数量加一。如果有剩余字符，它们在s中保持原来的顺序。
 * 执行操作之 前 ，你可以将s中至多一处 下标的对应字符更改为另一个小写英文字母。
 * 在最优选择情形下改变至多一处下标对应字符后，用整数表示并返回操作结束时得到的最大分割数量。
 *
 * 示例 1：
 * 输入：s = "accca", k = 2
 * 输出：3
 * 解释：在此
 * 示例中，为了最大化得到的分割数量，可以将 s[2] 改为 'b'。
 * s 变为 "acbca"。
 * 按照以下方式执行操作，直到 s 变为空：
 * - 选择最长且至多包含 2 个不同字符的前缀，"acbca"。
 * - 删除该前缀，s 变为 "bca"。现在分割数量为 1。
 * - 选择最长且至多包含 2 个不同字符的前缀，"bca"。
 * - 删除该前缀，s 变为 "a"。现在分割数量为 2。
 * - 选择最长且至多包含 2 个不同字符的前缀，"a"。
 * - 删除该前缀，s 变为空。现在分割数量为 3。
 * 因此，答案是 3。
 * 可以证明，分割数量不可能超过 3。
 *
 * 示例 2：
 * 输入：s = "aabaab", k = 3
 * 输出：1
 * 解释：在此
 * 示例中，为了最大化得到的分割数量，可以保持 s 不变。
 * 按照以下方式执行操作，直到 s 变为空：
 * - 选择最长且至多包含 3 个不同字符的前缀，"aabaab"。
 * - 删除该前缀，s 变为空。现在分割数量为 1。
 * 因此，答案是 1。
 * 可以证明，分割数量不可能超过 1。
 *
 * 示例 3：
 * 输入：s = "xxyz", k = 1
 * 输出：4
 * 解释：在此
 * 示例中，为了最大化得到的分割数量，可以将 s[1] 改为 'a'。
 * s 变为 "xayz"。
 * 按照以下方式执行操作，直到 s 变为空：
 * - 选择最长且至多包含 1 个不同字符的前缀，"xayz"。
 * - 删除该前缀，s 变为 "ayz"。现在分割数量为 1。
 * - 选择最长且至多包含 1 个不同字符的前缀，"ayz"。
 * - 删除该前缀，s 变为 "yz"，现在分割数量为 2。
 * - 选择最长且至多包含 1 个不同字符的前缀，"yz"。
 * - 删除该前缀，s 变为 "z"。现在分割数量为 3。
 * - 选择最且至多包含 1 个不同字符的前缀，"z"。
 * - 删除该前缀，s 变为空。现在分割数量为 4。
 * 因此，答案是 4。
 * 可以证明，分割数量不可能超过 4。
 *
 * 提示：
 * 	1 <= s.length <= 10^4
 * 	s只包含小写英文字母。
 * 	1 <= k <= 26
 *
 *
 * @author: agitated-curranfnd
 * @Description:
 * @url:   https://leetcode.cn/contest/weekly-contest-379/problems/maximize-the-number-of-partitions-after-operations
 * @title: 执行操作后的最大分割数量
 */
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class,"maxPartitionsAfterOperations","D.txt");
    }
	  

	     public int maxPartitionsAfterOperations(String s, int k) {    

	       return 0; 
   		}

  

}