package leetcode.contest.biweekly.bi_100.bi_129.d;

import code_generation.utils.IoUtil;

/**
 * 3130. 找出所有稳定的二进制数组 II
 *
 *
 *  给你 3 个正整数zero，one和limit。
 * 一个 二进制数组 arr 如果满足以下条件，那么我们称它是 稳定的 ：
 * 	0 在arr中出现次数 恰好为zero。
 * 	1 在arr中出现次数 恰好为one。
 * 	arr 中每个长度超过 limit的 子数组 都 同时 包含 0 和 1 。
 * 请你返回 稳定二进制数组的 总 数目。
 * 由于答案可能很大，将它对10^9 + 7取余后返回。
 *
 * 示例 1：
 * 输入：zero = 1, one = 1, limit = 2
 * 输出：2
 * 解释：
 * 两个稳定的二进制数组为[1,0] 和[0,1]，两个数组都有一个 0 和一个 1 ，且没有子数组长度大于 2 。
 *
 * 示例 2：
 * 输入：zero = 1, one = 2, limit = 1
 * 输出：1
 * 解释：
 * 唯一稳定的二进制数组是[1,0,1]。
 * 二进制数组[1,1,0] 和[0,1,1]都有长度为 2 且元素全都相同的子数组，所以它们不稳定。
 *
 * 示例 3：
 * 输入：zero = 3, one = 3, limit = 2
 * 输出：14
 * 解释：
 * 所有稳定的二进制数组包括[0,0,1,0,1,1]，[0,0,1,1,0,1]，[0,1,0,0,1,1]，[0,1,0,1,0,1]，[0,1,0,1,1,0]，[0,1,1,0,0,1]，[0,1,1,0,1,0]，[1,0,0,1,0,1]，[1,0,0,1,1,0]，[1,0,1,0,0,1]，[1,0,1,0,1,0]，[1,0,1,1,0,0]，[1,1,0,0,1,0]和[1,1,0,1,0,0]。
 *
 * 提示：
 * 	1 <= zero, one, limit <= 1000
 *
 * @author: agitated-curranfnd
 * @Description:
 * @url:   https://leetcode.cn/contest/biweekly-contest-129/problems/find-all-possible-stable-binary-arrays-ii
 * @title: 找出所有稳定的二进制数组II
 */
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class,"numberOfStableArrays","D.txt");
    }
     

    public int numberOfStableArrays(int zero, int one, int limit) {    

        return 0; 
	}

  

}