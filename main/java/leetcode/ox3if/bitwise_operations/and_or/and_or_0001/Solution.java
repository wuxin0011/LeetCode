package leetcode.ox3if.bitwise_operations.and_or.and_or_0001;

import code_generation.utils.IoUtil;

/**
 * 1318. 或运算的最小翻转次数
 * <p>
 * 给你三个正整数a、b 和 c。
 * 你可以对 a 和 b的二进制表示进行位翻转操作，返回能够使按位或运算 a OR b == c成立的最小翻转次数。
 * 「位翻转操作」是指将一个数的二进制表示任何单个位上的 1 变成 0 或者 0 变成 1 。
 * <p>
 * 示例 1：
 * 输入：a = 2, b = 6, c = 5
 * 输出：3
 * 解释：翻转后 a = 1 , b = 4 , c = 5 使得 a OR b == c
 * <p>
 * 示例 2：
 * 输入：a = 4, b = 2, c = 7
 * 输出：1
 * <p>
 * 示例 3：
 * 输入：a = 1, b = 2, c = 3
 * 输出：0
 * <p>
 * 提示：
 * 1 <= a <= 10^9
 * 1 <= b<= 10^9
 * 1 <= c<= 10^9
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/minimum-flips-to-make-a-or-b-equal-to-c
 * @title: 或运算的最小翻转次数
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "minFlips", "in.txt");
    }


    public int minFlips(int a, int b, int c) {
        int cnt = 0;
        if ((a | b) == c) {
            return 0;
        }
        for (int i = 0; i < 32; i++) {
            int va = (a >> i) & 1;
            int vb = (b >> i) & 1;
            int vc = (c >> i) & 1;
            if ((va | vb) == vc) {
                continue;
            }

//            System.out.println("va :" + va);
//            System.out.println("vb :" + va);
//            System.out.println("vc :" + vc);

            // 1 0 1
            // vc = 1
            if (vc == 1) {
                // 0 0
                cnt += 1;
            } else {
                // 1 1 0
                // 0 0
                // 2
                cnt += va == vb ? 2 : 1;
                // 1 0 0
                // = > 1
            }


        }
        return cnt;
    }


}