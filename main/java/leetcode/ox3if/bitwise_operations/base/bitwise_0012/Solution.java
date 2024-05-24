package leetcode.ox3if.bitwise_operations.base.bitwise_0012;

import code_generation.utils.IoUtil;

/**
 *
 * 2220. 转换数字的最少位翻转次数
 *
 * 一次 位翻转定义为将数字x二进制中的一个位进行 翻转操作，即将0变成1，或者将1变成0。比方说，x = 7，二进制表示为111，我们可以选择任意一个位（包含没有显示的前导 0 ）并进行翻转。比方说我们可以翻转最右边一位得到110，或者翻转右边起第二位得到101，或者翻转右边起第五位（这一位是前导 0 ）得到10111等等。给你两个整数start 和goal，请你返回将start转变成goal的最少位翻转次数。
 *
 * 示例 1：输入：start = 10, goal = 7输出：3解释：10 和 7 的二进制表示分别为 1010 和 0111 。我们可以通过 3 步将 10 转变成 7 ：- 翻转右边起第一位得到：1010 -> 1011 。- 翻转右边起第三位：1011 -> 1111 。- 翻转右边起第四位：1111 -> 0111 。我们无法在 3 步内将 10 转变成 7 。所以我们返回 3 。
 *
 * 示例 2：输入：start = 3, goal = 4输出：3解释：3 和 4 的二进制表示分别为 011 和 100 。我们可以通过 3 步将 3 转变成 4 ：- 翻转右边起第一位：011 -> 010 。- 翻转右边起第二位：010 -> 000 。- 翻转右边起第三位：000 -> 100 。我们无法在 3 步内将 3 变成 4 。所以我们返回 3 。
 *
 * 提示：
 *
 *
 * 	0 <= start, goal <= 109
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/minimum-bit-flips-to-convert-number
 * @title: 转换数字的最少位翻转次数
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"minBitFlips","in.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    public int minBitFlips(int start, int goal) {    

        return Integer.bitCount(start ^ goal);
	}

  

}