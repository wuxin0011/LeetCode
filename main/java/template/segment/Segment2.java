package template.segment;

/**
 * @author: wuxin0011
 * @Description:
 */
public class Segment2 {
    /**
     * 取模版本
     */
    public static class SegmentMod {
        public int N;
        public int[] arr;
        public int LEN;
        public long[] sum;
        public long[] add;
        public long[] change;
        public boolean[] update;
        public long[] max;
        public long[] min;
        long MOD;


        public SegmentMod() {
            this((int) 1e5, (long) 1e9 + 7);
        }

        public SegmentMod(int N, long MOD) {
            this.N = N;
            this.LEN = N * 4 + 2;
            this.arr = new int[N + 2];
            this.sum = new long[LEN];
            this.add = new long[LEN];
            this.change = new long[LEN];
            this.update = new boolean[LEN];
            this.max = new long[LEN];
            this.min = new long[LEN];
            this.MOD = MOD;
        }


        public void up(int i) {
            sum[i] = (sum[i << 1] + sum[i << 1 | 1] + MOD) % MOD;
            max[i] = Math.max(max[i << 1], max[i << 1 | 1]);
            min[i] = Math.min(min[i << 1], min[i << 1 | 1]);
        }

        public void addLazy(int i, long v, int n) {
            sum[i] = (sum[i] + n * v + MOD) % MOD;
            add[i] = (add[i] + v + MOD) % MOD;
            max[i] = (max[i] + v + MOD) % MOD;
            min[i] = (min[i] + v + MOD) % MOD;
        }

        public void updateLazy(int i, long v, int n) {
            sum[i] = ((long) n * v) % MOD;
            add[i] = 0;
            update[i] = true;
            change[i] = v;
            max[i] = v;
            min[i] = v;
        }


        public void down(int i, int ln, int rn) {
            if (update[i]) {
                updateLazy(i << 1, change[i], ln);
                updateLazy(i << 1 | 1, change[i], rn);
                update[i] = false;
            }

            // 注意一定要先的处理更新内容再处理 add
            if (add[i] != 0) {
                addLazy(i << 1, add[i], ln);
                addLazy(i << 1 | 1, add[i], rn);
                add[i] = 0;
            }
        }

        public void init(int l, int r, int i) {
            if (l == r) {
                sum[i] = arr[l];
                max[i] = arr[l];
                min[i] = arr[l];
            } else {
                int mid = l + ((r - l) >> 1);
                init(l, mid, i << 1);
                init(mid + 1, r, i << 1 | 1);
                up(i);
            }
            add[i] = 0;
            update[i] = false;
            change[i] = 0;
        }


        /**
         * 添加
         *
         * @param L 查询的左端点
         * @param R 查询的右端点
         * @param V 添加值
         * @param l 当前节点的左端点
         * @param r 当前节点的右端点
         * @param i 当前节点 （二叉树 父亲节点为 i 左儿子为 2 * i, 右孩子 i * 2 + 1 )
         */
        public void add(int L, int R, long V, int l, int r, int i) {
            if (L <= l && r <= R) {
                addLazy(i, V, r - l + 1);
            } else {
                int mid = l + ((r - l) >> 1);
                down(i, mid - l + 1, r - mid);

                if (L <= mid) {
                    add(L, R, V, l, mid, i << 1);
                }

                if (R > mid) {
                    add(L, R, V, mid + 1, r, i << 1 | 1);
                }

                up(i);
            }
        }


        // 参数解释同 add
        public void change(int L, int R, long V, int l, int r, int i) {
            if (L <= l && r <= R) {
                updateLazy(i, V, r - l + 1);
            } else {
                int mid = l + ((r - l) >> 1);
                down(i, mid - l + 1, r - mid);
                if (L <= mid) {
                    change(L, R, V, l, mid, i << 1);
                }
                if (R > mid) {
                    change(L, R, V, mid + 1, r, i << 1 | 1);
                }
                up(i);
            }
        }

        // 参数解释同 add
        public long querySum(int L, int R, int l, int r, int i) {
            if (L <= l && r <= R) {
                return sum[i];
            } else {
                int mid = l + ((r - l) >> 1);
                down(i, mid - l + 1, r - mid);
                long ans = 0;

                if (L <= mid) {
                    ans = (ans + querySum(L, R, l, mid, i << 1) +  MOD) % MOD;
                }

                if (R > mid) {
                    ans = (ans + querySum(L, R, mid + 1, r, i << 1 | 1) + MOD) % MOD;
                }

                return ans;
            }
        }

        // 参数解释同 add
        public long queryMax(int L, int R, int l, int r, int i) {
            if (L <= l && r <= R) {
                return max[i];
            } else {
                int mid = l + ((r - l) >> 1);
                down(i, mid - l + 1, r - mid);
                long ans = Long.MIN_VALUE;

                if (L <= mid) {
                    ans = Math.max(ans, queryMax(L, R, l, mid, i << 1));
                }

                if (R > mid) {
                    ans = Math.max(ans, queryMax(L, R, mid + 1, r, i << 1 | 1));
                }

                return ans;
            }
        }

        // 参数解释同 add
        public long queryMin(int L, int R, int l, int r, int i) {
            if (L <= l && r <= R) {
                return min[i];
            } else {
                int mid = l + ((r - l) >> 1);
                down(i, mid - l + 1, r - mid);
                long ans = Long.MAX_VALUE;

                if (L <= mid) {
                    ans = Math.min(ans, queryMin(L, R, l, mid, i << 1));
                }

                if (R > mid) {
                    ans = Math.min(ans, queryMin(L, R, mid + 1, r, i << 1 | 1));
                }

                return ans;
            }
        }
    }

}
