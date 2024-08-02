package template.array;

/**
 * @author: wuxin0011
 * @Description:
 */
public class DoubleArray {


    // 二维前缀和模板
    static class Mat {

        int[][] sum;

        Mat(int[][] grid) {
            this.sum = init(grid);
        }

        /**
         * 二维前缀和
         *
         * @param grid
         * @return
         */
        public int[][] init(int[][] grid) {
            int row = grid.length, col = grid[0].length;
            int[][] sum = new int[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    int left = j > 0 ? sum[i][j - 1] : 0;
                    int top = i > 0 ? sum[i - 1][j] : 0;
                    int left_top = i > 0 && j > 0 ? sum[i - 1][j - 1] : 0;
                    int cur = grid[i][j];
                    sum[i][j] = left + top - left_top + cur;
                }
            }
            return sum;
        }

        /**
         * 给定左上坐标和右边下坐标 求 区域和
         * 坐上坐标 a,b
         * 右下坐标 c,d
         */
        public int query(int a, int b, int c, int d) {
            if (sum == null || a > c || b > d) {
                return 0;
            }
            int top = a > 0 ? sum[a - 1][d] : 0;
            int tot = sum[c][d];
            int left = b > 0 ? sum[c][b - 1] : 0;
            int left_top = a > 0 && b > 0 ? sum[a - 1][b - 1] : 0;
            return tot + left_top - top - left;
        }
    }
}
