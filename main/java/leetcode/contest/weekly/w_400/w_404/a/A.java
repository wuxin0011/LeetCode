package leetcode.contest.weekly.w_400.w_404.a;

import code_generation.utils.IoUtil;

/**
 *
 * 100340. 三角形的最大高度
 *
 * 给你两个整数 red 和 blue，分别表示红色球和蓝色球的数量。
 * 你需要使用这些球来组成一个三角形，满足第 1 行有 1 个球，第 2 行有 2 个球，第 3 行有 3 个球，依此类推。
 * 每一行的球必须是 相同 颜色，且相邻行的颜色必须 不同。
 * 返回可以实现的三角形的 最大 高度。
 *
 * 示例 1：
 * 输入： red = 2, blue = 4
 * 输出： 3
 * 解释：
 * 上图显示了唯一可能的排列方式。
 *
 * 示例 2：
 * 输入： red = 2, blue = 1
 * 输出： 2
 * 解释：
 * 上图显示了唯一可能的排列方式。
 *
 * 示例 3：
 * 输入： red = 1, blue = 1
 * 输出： 1
 *
 * 示例 4：
 * 输入： red = 10, blue = 1
 * 输出： 2
 * 解释：
 * 上图显示了唯一可能的排列方式。
 *
 * 提示：
 * 	1 <= red, blue <= 100
 *
 * @author: wuxin0011
 * @Description:
 * @url https://leetcode.cn/problems/maximum-height-of-a-triangle/
 * @title
 */
public class A {
    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "maxHeightOfTriangle", "A.txt");
    }

    public int maxHeightOfTriangle(int red, int blue) {
        return Math.max(helper(red, blue), helper(blue, red));
    }

    public static int helper(int a, int b) {
        int x = 0;
        int i = 1;
        while (a >= 0 || b >= 0) {
            if (i % 2 == 1) {
                if (a - i < 0) return x;
                a -= i;
            } else {
                if (b - i < 0) return x;
                b -= i;
            }
            i += 1;
            x += 1;
        }
        return x;
    }

}
