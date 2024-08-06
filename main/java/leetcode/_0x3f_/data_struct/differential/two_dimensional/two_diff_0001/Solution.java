package leetcode._0x3f_.data_struct.differential.two_dimensional.two_diff_0001;

import code_generation.utils.IoUtil;

/**
 * 2132. 用邮票贴满网格图
 * <p>
 * 给你一个m x n的二进制矩阵grid，每个格子要么为0（空）要么为1（被占据）。
 * 给你邮票的尺寸为stampHeight x stampWidth。我们想将邮票贴进二进制矩阵中，且满足以下限制和要求：
 * 覆盖所有 空格子。
 * 不覆盖任何 被占据的格子。
 * 我们可以放入任意数目的邮票。
 * 邮票可以相互有 重叠部分。
 * 邮票不允许 旋转。
 * 邮票必须完全在矩阵 内。
 * 如果在满足上述要求的前提下，可以放入邮票，请返回true，否则返回false。
 * <p>
 * 示例 1：
 * 输入：grid = [[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0]], stampHeight = 4, stampWidth = 3
 * 输出：true
 * 解释：我们放入两个有重叠部分的邮票（图中标号为 1 和 2），它们能覆盖所有与空格子。
 * <p>
 * 示例 2：
 * 输入：grid = [[1,0,0,0],[0,1,0,0],[0,0,1,0],[0,0,0,1]], stampHeight = 2, stampWidth = 2
 * 输出：false
 * 解释：没办法放入邮票覆盖所有的空格子，且邮票不超出网格图以外。
 * <p>
 * 提示：
 * m == grid.length
 * n == grid[r].length
 * 1 <= m, n <= 10^5
 * 1 <= m * n <= 2 * 10^5
 * grid[r][c] 要么是0，要么是1 。
 * 1 <= stampHeight, stampWidth <= 10^5
 *
 * @author: wuxin0011
 * @Description: 二维差分 + 二维前缀和
 * @url: https://leetcode.cn/problems/stamping-the-grid
 * @title: 用邮票贴满网格图
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "possibleToStamp", "in.txt");
    }


    int[][] diff, g, sums;
    int m, n;

    public boolean possibleToStamp(int[][] g, int h, int w) {
        this.m = g.length;
        this.n = g[0].length;
        this.sums = new int[m + 1][n + 1];
        this.g = g;
        this.diff = new int[m + 2][n + 2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sums[i + 1][j + 1] = sums[i + 1][j] + sums[i][j + 1] - sums[i][j] + g[i][j];
            }
        }

        for (int i = 0; i + h <= m; i++) {
            for (int j = 0; j + w <= n; j++) {
                if (query(i, j, i + h - 1, j + w - 1) == 0) {
                    add(i, j, i + h - 1, j + w - 1, 1);
                }
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                diff[i][j] += diff[i - 1][j] + diff[i][j - 1] - diff[i - 1][j - 1];
                g[i - 1][j - 1] += diff[i][j];
                if (g[i - 1][j - 1] == 0) {
                    return false;
                }
            }
        }

        return true;
    }


    int query(int x0, int y0, int x1, int y1) {
        x0++;
        y0++;
        x1++;
        y1++;
        return sums[x1][y1] - sums[x1][y0 - 1] - sums[x0 - 1][y1] + sums[x0 - 1][y0 - 1];
    }


    void add(int x0, int y0, int x1, int y1, int v) {
        x0++;
        y0++;
        x1++;
        y1++;
        diff[x0][y0] += v;
        diff[x1 + 1][y0] -= v;
        diff[x0][y1 + 1] -= v;
        diff[x1 + 1][y1 + 1] += v;
    }


}