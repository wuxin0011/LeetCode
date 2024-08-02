package leetcode._0x3f_.slide_window.slide_0006;

import code_generation.utils.IoUtil;


/**
 * 2379. 得到 K 个黑块的最少涂色次数
 * 给你一个长度为 n下标从 0开始的字符串blocks，blocks[i]要么是'W'要么是'B'，表示第i块的颜色。字符'W' 和'B'分别表示白色和黑色。给你一个整数k，表示想要连续黑色块的数目。每一次操作中，你可以选择一个白色块将它 涂成黑色块。请你返回至少出现 一次连续 k个黑色块的 最少操作次数。
 * 示例 1：输入：blocks = "WBBWWBBWBW", k = 7输出：3解释：一种得到 7 个连续黑色块的方法是把第 0 ，3 和 4 个块涂成黑色。得到 blocks = "BBBBBBBWBW" 。可以证明无法用少于 3 次操作得到 7 个连续的黑块。所以我们返回 3 。
 * 示例 2：输入：blocks = "WBWBBBW", k = 2输出：0解释：不需要任何操作，因为已经有 2 个连续的黑块。所以我们返回 0 。
 * 提示：
 * n == blocks.length
 * 1 <= n <= 100
 * blocks[i]要么是'W'，要么是'B' 。
 * 1 <= k <= n
 */

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/minimum-recolors-to-get-k-consecutive-black-blocks
 * @title: minimum-recolors-to-get-k-consecutive-black-blocks
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "minimumRecolors", "in.txt");
    }


    public int minimumRecolors(String blocks, int k) {
        // W
        // B
        int cnt = 0;
        char[] charArray = blocks.toCharArray();
        int ans = blocks.length();
        for (int i = 0; i < charArray.length; i++) {
            if (i >= k && charArray[i-k] == 'W') {
                cnt -= 1;
            }

            if(charArray[i] == 'W'){
                cnt++;
            }

            if (i >= k - 1) {
                ans = Math.min(ans, cnt);
            }

        }

        return ans;
    }


}

