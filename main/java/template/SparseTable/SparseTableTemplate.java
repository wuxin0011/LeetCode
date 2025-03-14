package template.SparseTable;


/**
 * St 表 O(1) 查询区间最值问题 解决RMQ 问题
 * 通常可以用st表代替单调栈求区间等系列问题 这个方法比较莽 但是思路简单
 */
public class SparseTableTemplate {


    // ======================================SparseTable 模板开始 ======================================
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

        // [l,r]
        public int query(int l, int r) {
            int lt = r - l + 1;
            int q = (int) (Math.floor(Math.log(lt) / Math.log(2)));
            return op.op(st[l][q], st[r - (1 << q) + 1][q]);
        }
    }

    // ======================================SparseTable 模板结束 ======================================


    // =============================辅助函数=============================

    // 查询 [l,r] 第一次等于x 必须保证 r 为 x 最后一个解 最后查询结果必须严格保证  [pos,end] 严格 等于 x
    private static int firstEqual(SparseTable st, int l, int r, int x) {
        int ans = r;
        int end = r;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (st.query(mid, end) == x) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    // 查询 [l,r] 第一次等于x 必须保证 l 为 x 第一个解 最后查询结果必须严格保证  [start,pos] 严格 等于 x
    private static int lastEqual(SparseTable st, int l, int r, int x) {
        int ans = r;
        int start = l;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (st.query(start, mid) == x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }


    // 查询第一个 >= x 的位置
    private static int lowerbound(SparseTable st, int l, int r, int x) {
        int ans = -1;
        int end = r;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            // >= 替换 <= ,查询 <= 查询第一个小于等于 x
            if (st.query(l, mid) >= x) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    // 查询最后一个 >= x 的位置
    private static int upperbound(SparseTable st, int l, int r, int x) {
        int ans = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            // >= 可以替换 <=
            if (st.query(mid, r) >= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }


}