package template.segment;

/**
 * @author: wuxin0011
 * @Description: 基本模板 通过数组维护线段树 功能单一
 */

@SuppressWarnings("all")
public class LazySegmentTemplate {


    /***************************************线段树模板开始**************************************/
    static long op(long x, long y) {
        return -1;
    }

    static long e() {
        return -1;
    }

    static boolean check0(long i, long t) {
        return false;
    }

    public static class LazySegment {


        public int[] arr;
        int n;
        boolean[] visupd;
        long[] infos, addv, updv;

        LazySegment(int n) {
            this.n = n;
            this.init0();
        }

        LazySegment(int[] a) {
            this.arr = a;
            this.n = a.length;
            this.init0();
        }

        private void init0() {
            this.infos = new long[n << 2];
            this.addv = new long[n << 2];
            this.updv = new long[n << 2];
            this.visupd = new boolean[n << 2];
            for (int i = 0; i < (n << 2); i++) {
                updv[i] = e();
                infos[i] = e();
                addv[i] = e();
            }
            this.build(0, n - 1, 1);
        }

        public void up(int i) {
            infos[i] = op(infos[i << 1], infos[i << 1 | 1]);
        }


        public void build(int l, int r, int i) {
            if (l == r) {
                infos[i] = arr == null ? e() : arr[l];
            } else {
                int mid = l + ((r - l) >> 1);
                build(l, mid, i << 1);
                build(mid + 1, r, i << 1 | 1);
                up(i);
            }
        }

        /****根据题目条件修改*/
        public void addLazy(int i, int size, long v) {
            infos[i] += 1L * size * v;
            addv[i] += v;
        }

        public void updateLazy(int i, int size, long v) {
            infos[i] = 1L * size * v;
            updv[i] = v;
            addv[i] = 0;
            visupd[i] = true;
        }

        /****根据题目条件修改*/
        public void down(int i, int ln, int rn) {
            if (visupd[i]) {
                updateLazy(i << 1, ln, updv[i]);
                updateLazy(i << 1 | 1, rn, updv[i]);
                visupd[i] = false;
                addv[i] = 0;
            }
            if (addv[i] != 0) {
                addLazy(i << 1, ln, addv[i]);
                addLazy(i << 1 | 1, rn, addv[i]);
                addv[i] = 0;
            }
        }

        public void add(int ql, int qr, int v, int l, int r, int i) {
            if (ql <= l && r <= qr) {
                addLazy(i, r - l + 1, v);
            } else {
                int mid = l + ((r - l) >> 1);
                down(i, mid - l + 1, r - mid);
                if (ql <= mid) {
                    add(ql, qr, v, l, mid, i << 1);
                }
                if (qr > mid) {
                    add(ql, qr, v, mid + 1, r, i << 1 | 1);
                }
                up(i);
            }
        }

        public void update(int ql, int qr, int v, int l, int r, int i) {
            if (ql <= l && r <= qr) {
                updateLazy(i, r - l + 1, v);
            } else {
                int mid = l + ((r - l) >> 1);
                down(i, mid - l + 1, r - mid);
                if (ql <= mid) {
                    update(ql, qr, v, l, mid, i << 1);
                }
                if (qr > mid) {
                    update(ql, qr, v, mid + 1, r, i << 1 | 1);
                }
                up(i);
            }
        }

        public long query(int ql, int qr, int l, int r, int i) {
            if (ql <= l && r <= qr) {
                return infos[i];
            } else {
                long ans = e();
                int mid = l + ((r - l) >> 1);
                down(i, mid - l + 1, r - mid);
                if (ql <= mid) {
                    ans = op(ans, query(ql, qr, l, mid, i << 1));
                }
                if (qr > mid) {
                    ans = op(ans, query(ql, qr, mid + 1, r, i << 1 | 1));
                }
                return ans;
            }
        }

        // 线段树二分 查询第一个
        public int findFirst(int L,int R, int x, int l, int r, int i) {
            if (r < L || l > R || !check0(infos[i], x))
                return -1;
            if (l == r) {
                return l;
            }
            int mid = l + ((r - l) >> 1);
            down(i, mid - l + 1, r - mid);
            if (L <= mid) {
                int p = findFirst(L,R, x, l, mid, i << 1);
                if (p >= 0)
                    return p;
            }
            return findFirst(L, R,x, mid + 1, r, i << 1 | 1);
        }

        // 线段树二分 查询最后一个
        public int findLast(int L,int R, int x, int l, int r, int i) {
            if (r < L || l > R || !check0(infos[i], x))
                return -1;
            if (l == r) {
                return l;
            }
            int mid = l + ((r - l) >> 1);
            down(i, mid - l + 1, r - mid);
            if (L >= mid) {
                int p = findLast(L,R,x, mid + 1, r, i << 1 | 1);
                if (p >= 0)
                    return p;
            }
            return findLast(L, R,x, l, mid, i << 1);
        }

    }

    /***************************************线段树模板结束**************************************/


}
