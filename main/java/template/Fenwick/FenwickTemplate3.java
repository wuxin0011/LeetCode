package template.Fenwick;

/**
 * @author: wuxin0011
 * @Description: 树状数组维持 二维差分、查询
 */
public class FenwickTemplate3 {


    // 单点修改 范围查询
    static class Mat {
        int[][] data, nums;
        int m, n;

        Mat(int[][] originArrays) {
            m = originArrays.length;
            n = originArrays[0].length;
            data = new int[m + 1][n + 1];
            nums = new int[m + 1][n + 1];
//            this.originArray = originArray;
            for(int i  = 0;i < m;i++) {
                for(int j = 0;j < n;j++) {
                    update(i,j,originArrays[i][j]);
                }
            }
        }


        // [row,col] 修改为 val
        void update(int row, int col, int val) {
            add(row, col, val - nums[calc(row)][calc(col)]);
            nums[calc(row)][calc(col)] = val;
        }

        void add(int row, int col, int val) {
            row = calc(row);
            col = calc(col);
            for (int i = row; i <= m; i += lowbit(i)) {
                for (int j = col; j <= n; j += lowbit(j)) {
                    data[i][j] += val;
                }
            }
        }

        static int lowbit(int i) {
            return i & -i;
        }


        // [1,1] 到 [row,col] 累加和
        int query(int row, int col) {
            int s = 0;
            row = calc(row);
            col = calc(col);
            for (int i = row; i > 0; i -= lowbit(i)) {
                for (int j = col; j > 0; j -= lowbit(j)) {
                    s += data[i][j];
                }
            }
            return s;
        }


        // 查询 左上角 [row1,co1] 到右下角 [row2,col2]
        int query(int row1, int col1, int row2, int col2) {
            int ans = 0;
            ans += query(row2, col2);
            ans -= query(row2, col1 - 1);
            ans -= query(row1 - 1, col2);
            ans += query(row1 - 1, col1 - 1);
            return ans;
        }



        // 控制下标是否 + 1
        static int calc(int i) {
            return i + 1;
        }
    }



    // 维持差分信息 区间修改 范围查询

    static class FenwickDoubleDiff {
        public static int MAXN = 2050;

        public static int MAXM = 2050;

        public static long[][] data1 = new long[MAXN][MAXM],data2 = new long[MAXN][MAXM],data3 = new long[MAXN][MAXM],data4 = new long[MAXN][MAXM];


        public static int n, m;

        public static int lowbit(int i) {
            return i & -i;
        }

        public static void add(int x, int y, int v) {
            long v1 = v;
            long v2 = x * 1L* v;
            long v3 = y * 1L*  v;
            long v4 = x * 1L*  y * v;
            for (int i = x; i <= n; i += lowbit(i)) {
                for (int j = y; j <= m; j += lowbit(j)) {
                    data1[i][j] += v1;
                    data2[i][j] += v2;
                    data3[i][j] += v3;
                    data4[i][j] += v4;
                }
            }
        }

        // 以(1,1)左上角，以(x,y)右下角
        public static long query(int x, int y) {
            long ans = 0;
            for (int i = x; i > 0; i -= lowbit(i)) {
                for (int j = y; j > 0; j -= lowbit(j)) {
                    ans += (x + 1) * (y + 1) * data1[i][j] - (y + 1) * data2[i][j] - (x + 1) * data3[i][j] + data4[i][j];
                }
            }
            return ans;
        }

        public static void add(int a, int b, int c, int d, int v) {
            add(a, b, v);
            add(a, d + 1, -v);
            add(c + 1, b, -v);
            add(c + 1, d + 1, v);
        }

        public static long range(int a, int b, int c, int d) {
            return query(c, d) - query(a - 1, d) - query(c, b - 1) + query(a - 1, b - 1);
        }

    }
}
