package template.sparseTable;


/**
 * 二维st表
 *https://leetcode.cn/contest/weekly-contest-502/problems/largest-local-values-in-a-matrix-ii/submissions/725472069/
 */
public class SparseTabl2DTemplate {


    // ======================================SparseTable 模板开始 ======================================
    public static class SparseTable2D {
        @FunctionalInterface
        public interface Operation {
            int op(int x, int y);
        }

        private int[][][][] st;
        private int[] LOG;
        private int m, n,initial;
        private final Operation op;

        public SparseTable2D(int[][] matrix, Operation op,int initial) {
            this.op = op;
            this.m = matrix.length;
            this.n = matrix[0].length;
            this.initial = initial;
            LOG = new int[Math.max(m, n) + 10];
            for (int i = 2; i < LOG.length; i++) {
                LOG[i] = LOG[i / 2] + 1;
            }
            int K = LOG[m] + 1;
            int L = LOG[n] + 1;
            st = new int[K][L][m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    st[0][0][i][j] = matrix[i][j];
                }
            }
            for (int kx = 1; kx < K; kx++) {
                for (int i = 0; i + (1 << kx) <= m; i++) {
                    for (int j = 0; j < n; j++) {
                        st[kx][0][i][j] = op.op(
                                st[kx - 1][0][i][j],
                                st[kx - 1][0][i + (1 << (kx - 1))][j]
                        );
                    }
                }
            }
            for (int ky = 1; ky < L; ky++) {
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j + (1 << ky) <= n; j++) {
                        st[0][ky][i][j] = op.op(
                                st[0][ky - 1][i][j],
                                st[0][ky - 1][i][j + (1 << (ky - 1))]
                        );
                    }
                }
            }
            for (int kx = 1; kx < K; kx++) {
                for (int ky = 1; ky < L; ky++) {
                    for (int i = 0; i + (1 << kx) <= m; i++) {
                        for (int j = 0; j + (1 << ky) <= n; j++) {
                            st[kx][ky][i][j] = op.op(
                                    op.op(st[kx - 1][ky - 1][i][j],
                                            st[kx - 1][ky - 1][i + (1 << (kx - 1))][j]),
                                    op.op(st[kx - 1][ky - 1][i][j + (1 << (ky - 1))],
                                            st[kx - 1][ky - 1][i + (1 << (kx - 1))][j + (1 << (ky - 1))])
                            );
                        }
                    }
                }
            }
        }
        public int query(int x1, int y1, int x2, int y2) {
            x1 = Math.max(x1,0);
            y1 = Math.max(y1,0);
            x2 = Math.min(x2,m-1);
            y2 = Math.min(y2,n-1);
            if (x1 > x2  || y1 > y2 ) {
                return initial;
            }

            int kx = LOG[x2 - x1 + 1];
            int ky = LOG[y2 - y1 + 1];

            int topLeft = st[kx][ky][x1][y1];
            int topRight = st[kx][ky][x1][y2 - (1 << ky) + 1];
            int bottomLeft = st[kx][ky][x2 - (1 << kx) + 1][y1];
            int bottomRight = st[kx][ky][x2 - (1 << kx) + 1][y2 - (1 << ky) + 1];

            return op.op(op.op(topLeft, bottomLeft), op.op(topRight, bottomRight));
        }
    }

    // ======================================SparseTable 模板结束 ======================================


}