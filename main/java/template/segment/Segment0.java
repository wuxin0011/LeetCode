package template.segment;

/**
 * @author: wuxin0011
 * @Description: 基本模板
 */
public class Segment0 {
    public static class LazySegment {
        public int N;
        public int[] arr;
        public int MAXN;
        public long[] sum, add, change, max, min;
        public boolean[] update;

        public LazySegment() {
            this((int) 1e5);
        }

        public LazySegment(int N) {
            this.N = N;
            this.MAXN = N * 4 + 1;
            this.arr = new int[N + 2];
            this.sum = new long[MAXN];
            this.add = new long[MAXN];
            this.change = new long[MAXN];
            this.update = new boolean[MAXN];
//            this.max = new long[MAXN];
//            this.min = new long[MAXN];
        }


        public void up(int i) {
            if (sum != null) sum[i] = sum[i << 1] + sum[i << 1 | 1];
            if (max != null) max[i] = Math.max(max[i << 1], max[i << 1 | 1]);
            if (min != null) min[i] = Math.min(min[i << 1], min[i << 1 | 1]);
        }

        public void addLazy(int i, long v, int n) {
            if (sum != null) {
                sum[i] += n * v;
                add[i] += v;
            }
            if (max != null) max[i] += v;
            if (min != null) min[i] += v;
        }

        public void updateLazy(int i, long v, int n) {
            if (sum != null && add != null) {
                sum[i] = (long) n * v;
                add[i] = 0;
            }
            if (update != null && change != null) {
                update[i] = true;
                change[i] = v;
            }
            if (max != null) {
                max[i] = v;
            }
            if (min != null) {
                min[i] = v;
            }
        }


        public void down(int i, int ln, int rn) {
            if (update != null && update[i]) {
                updateLazy(i << 1, change[i], ln);
                updateLazy(i << 1 | 1, change[i], rn);
                update[i] = false;
            }

            // 注意一定要先的处理更新内容再处理 add
            if (add != null && add[i] != 0) {
                addLazy(i << 1, add[i], ln);
                addLazy(i << 1 | 1, add[i], rn);
                add[i] = 0;
            }
        }

        public void init() {
            this.init(1, N, 1);
        }

        public long query(int l, int r) {
            return this.query(l, r, 1, N, 1);
        }

        public void init(int l, int r, int i) {
            if (l == r) {
                if (sum != null) sum[i] = arr[l];
                if (max != null) max[i] = arr[l];
                if (min != null) min[i] = arr[l];
            } else {
                int mid = l + ((r - l) >> 1);
                init(l, mid, i << 1);
                init(mid + 1, r, i << 1 | 1);
                up(i);
            }
            if (add != null) add[i] = 0;
            if (update != null && change != null) {
                update[i] = false;
                change[i] = 0;
            }
        }

        public void add(int L, int R, long V, int l, int r, int i) {
            if (L <= 0 || L > R || L > N || R > N) {
                return;
            }
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
            if (L <= 0 || L > R || L > N || R > N) {
                return;
            }
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

        public long query(int L, int R, int l, int r, int i) {
            if (L <= 0 || L > R || L > N || R > N) {
                return 0;
            }
            if (L <= l && r <= R) {
                return sum[i];
            } else {
                int mid = l + ((r - l) >> 1);
                down(i, mid - l + 1, r - mid);
                long ans = 0;
                if (L <= mid) {
                    ans += query(L, R, l, mid, i << 1);
                }
                if (R > mid) {
                    ans += query(L, R, mid + 1, r, i << 1 | 1);
                }
                return ans;
            }
        }
    }

}
