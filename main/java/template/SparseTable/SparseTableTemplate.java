package template.SparseTable;


import code_generation.utils.RandomArrayUtils;

/**
 * St 表 O(1) 查询区间最值问题 解决RMQ 问题
 */
public class SparseTableTemplate {

    public static class SparseTable {
        @FunctionalInterface
        public interface Operation {
            int op(int x, int y);
        }

        private final int[][] st;
        private final Operation op;

        public SparseTable(int[] array, Operation op) {
            this.op = op;
            int n = array.length;
            int log = (int) (Math.ceil(Math.log(n) / Math.log(2))) + 1;
            st = new int[n][log];
            for (int i = 0; i < n; i++) {
                st[i][0] = array[i];
            }
            for (int j = 1; j < log; j++) {
                int pj = (1 << (j - 1));
                for (int i = 0; i + pj < n; i++) {
                    st[i][j] = op.op(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
                }
            }
        }

        public int query(int l, int r) {
            int lt = r - l + 1;
            int q = (int) (Math.floor(Math.log(lt) / Math.log(2)));
            return op.op(st[l][q], st[r - (1 << q) + 1][q]);
        }
    }




}