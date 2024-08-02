package leetcode._0x3f_.slide_window.slide_0028;

import code_generation.utils.IoUtil;

/**
 * 2106. 摘水果
 * <p>
 * 在一个无限的 x 坐标轴上，有许多水果分布在其中某些位置。给你一个二维整数数组 fruits ，其中 fruits[i] = [positioni, amounti] 表示共有 amounti 个水果放置在 positioni 上。fruits 已经按 positioni 升序排列 ，每个 positioni 互不相同 。另给你两个整数 startPos 和 k 。最初，你位于 startPos 。从任何位置，你可以选择 向左或者向右 走。在 x 轴上每移动 一个单位 ，就记作 一步 。你总共可以走 最多 k 步。你每达到一个位置，都会摘掉全部的水果，水果也将从该位置消失（不会再生）。返回你可以摘到水果的 最大总数 。
 * <p>
 * 示例 1：输入：fruits = [[2,8],[6,3],[8,6]], startPos = 5, k = 4输出：9解释：最佳路线为：- 向右移动到位置 6 ，摘到 3 个水果- 向右移动到位置 8 ，摘到 6 个水果移动 3 步，共摘到 3 + 6 = 9 个水果
 * <p>
 * 示例 2：输入：fruits = [[0,9],[4,1],[5,7],[6,2],[7,4],[10,9]], startPos = 5, k = 4输出：14解释：可以移动最多 k = 4 步，所以无法到达位置 0 和位置 10 。最佳路线为：- 在初始位置 5 ，摘到 7 个水果- 向左移动到位置 4 ，摘到 1 个水果- 向右移动到位置 6 ，摘到 2 个水果- 向右移动到位置 7 ，摘到 4 个水果移动 1 + 3 = 4 步，共摘到 7 + 1 + 2 + 4 = 14 个水果
 * <p>
 * 示例 3：输入：fruits = [[0,3],[6,4],[8,5]], startPos = 3, k = 2输出：0解释：最多可以移动 k = 2 步，无法到达任一有水果的地方
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= fruits.length <= 105
 * fruits[i].length == 2
 * 0 <= startPos, positioni <= 2 * 105
 * 对于任意 i > 0 ，positioni-1 < positioni 均成立（下标从 0 开始计数）
 * 1 <= amounti <= 104
 * 0 <= k <= 2 * 105
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/maximum-fruits-harvested-after-at-most-k-steps
 * @title: maximum-fruits-harvested-after-at-most-k-steps
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "maxTotalFruits", "in.txt");
    }


    public int maxTotalFruits(int[][] fruits, int m, int k) {
        return f(fruits, m, k);
    }


    public static int f(int[][] fruits, int pos, int k) {
        int left = lowerBound(fruits, pos - k); // 向左最远能到 fruits[left][0]
        int right = left, s = 0, n = fruits.length;
        for (; right < n && fruits[right][0] <= pos; right++) {
            s += fruits[right][1]; // 从 fruits[left][0] 到 startPos 的水果数
        }

        int ans = s;
        for (; right < n && fruits[right][0] <= pos + k; right++) {
            s += fruits[right][1]; // 枚举最右位置为 fruits[right][0]
            while (fruits[right][0] * 2 - fruits[left][0] - pos > k &&  fruits[right][0] - fruits[left][0] * 2 + pos > k) {
                s -= fruits[left++][1]; // fruits[left][0] 无法到达
            }
            ans = Math.max(ans, s); // 更新答案最大值
        }
        return ans;
    }


    public static int lowerBound(int[][] fruits, int target) {
        int r = fruits.length , l = -1;
        while (l+1 < r) {
            int mid = l + ((r - l) >> 1);
            if (fruits[mid][0] < target) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return r;
    }

}