package template.matrix;

/**
 * https://leetcode.cn/problems/largest-magic-square
 * 计算矩阵的前缀和斜对角线和等
 * @author: wuxin0011
 * @Description:
 */
public class MatPreSums {
    private int m;
    private int n;
    private int[][] g;
    private int[][] sums;
    private int[][] sumsL;
    private int[][] sumsR;

    public MatPreSums(int[][] g) {
        this.m = g.length;
        this.n = g[0].length;
        this.g = g;
        this.sums = new int[m + 3][n + 3];
        this.sumsL = new int[m + 3][n + 3];
        this.sumsR = new int[m + 3][n + 3];
        init();
        initL();
        initR();
    }


    // (x0,y0) 左上 (x1,y1) 右下
    public int get(int x0, int y0, int x1, int y1) {
        return sums[x1 + 1][y1 + 1] - sums[x1 + 1][y0] - sums[x0][y1 + 1] + sums[x0][y0];
    }

    // (x0,y0) 左上 (x1,y1) 右下
    public int getL(int x0, int y0, int x1, int y1) {
        return sumsL[x1 + 1][y1 + 1] - sumsL[x0][y0];
    }

    // (x0,y0) 左下 (x1,y1) 右上
    public int getR(int x0, int y0, int x1, int y1) {
        return sumsR[x0 + 1][y0 + 1] - sumsR[x1][y1 + 2];
    }

    private void initL() {
        for (int i0 = m - 1; i0 >= 0; i0--) {
            int i = i0;
            int j = 0;
            while (i < m && j < n) {
                sumsL[i + 1][j + 1] = sumsL[i][j] + g[i][j];
                i++;
                j++;
            }
        }
        for (int j0 = 1; j0 < n; j0++) {
            int i = 0;
            int j = j0;
            while (i < m && j < n) {
                sumsL[i + 1][j + 1] = sumsL[i][j] + g[i][j];
                i++;
                j++;
            }
        }
    }

    private void initR() {
        for (int i0 = m - 1; i0 >= 0; i0--) {
            int i = i0;
            int j = n - 1;
            while (i < m && j >= 0) {
                sumsR[i + 1][j + 1] = sumsR[i][j + 2] + g[i][j];
                i++;
                j--;
            }
        }
        for (int j0 = n - 2; j0 >= 0; j0--) {
            int i = 0;
            int j = j0;
            while (i < m && j >= 0) {
                sumsR[i + 1][j + 1] = sumsR[i][j + 2] + g[i][j];
                i++;
                j--;
            }
        }
    }

    private void init() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sums[i + 1][j + 1] = sums[i + 1][j] + sums[i][j + 1] + g[i][j] - sums[i][j];
            }
        }
    }
}