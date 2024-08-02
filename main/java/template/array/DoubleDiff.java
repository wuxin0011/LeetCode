package template.array;

/**
 * @author: wuxin0011
 * @Description: 二维差分
 */
public class DoubleDiff {


    static class Diff {

        int[][] diffs;
        int[][] mat;
        int m, n;

        Diff(int[][] grid) {
            this.mat = grid;
            m = grid.length;
            n = grid[0].length;
            diffs = new int[m + 2][n + 2];
        }

        void add(int x0, int y0, int x1, int y1, int v) {
            x0++;
            y0++;
            x1++;
            y1++;
            diffs[x0][y0] += v;
            diffs[x0][y1 + 1] -= v;
            diffs[x1 + 1][y0] -= v;
            diffs[x1 + 1][y1 + 1] += v;
        }

        void origin(int[][] mat) {
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    diffs[i][j] = diffs[i - 1][j] + diffs[i][j - 1] + diffs[i - 1][j - 1] + diffs[i][j];
                    mat[i - 1][j - 1] += diffs[i][j];
                }
            }
        }


    }
}
