package template.segment;

/**
 * @author: wuxin0011
 * @Description:
 */
public class LazySegmentInfo {

    /***************************************线段树模板开始**************************************/
    static Info op(Info x, Info y) {
        return e();
    }

    static Info e() {
        return new Info();
    }

    static boolean check0(Info i, Info t) {
        return false;
    }

    static class Info {
        long v = 0;

        Info() {

        }
    }

    public static class LazySegment {


        public Info[] arr, infos, addv, updv;
        int n;
        boolean[] visupd, visadd;

        LazySegment(int n) {
            this.n = n;
            this.init0();
        }

        LazySegment(Info[] a) {
            this.arr = a;
            this.n = a.length;
            this.init0();
        }

        private void init0() {
            this.infos = new Info[n << 2];
            this.addv = new Info[n << 2];
            this.updv = new Info[n << 2];
            this.visupd = new boolean[n << 2];
            this.visadd = new boolean[n << 2];
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
        public void addLazy(int i, int size, Info v) {
            infos[i] = op(infos[i], v);
            addv[i] = op(addv[i], v);
        }

        /****根据题目条件修改*/
        public void updateLazy(int i, int size, Info v) {
            infos[i] = v;
            updv[i] = v;
            visupd[i] = true;
            addv[i] = e();
            visadd[i] = false;
        }


        public void down(int i, int ln, int rn) {
            if (visupd[i]) {
                updateLazy(i << 1, ln, updv[i]);
                updateLazy(i << 1 | 1, rn, updv[i]);
                visupd[i] = false;
                addv[i] = e();
                visadd[i] = false;
            }
            if (visadd[i]) {
                addLazy(i << 1, ln, addv[i]);
                addLazy(i << 1 | 1, rn, addv[i]);
                addv[i] = e();
                visadd[i] = false;
            }
        }

        public void add(int ql, int qr, Info v, int l, int r, int i) {
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

        public void update(int ql, int qr, Info v, int l, int r, int i) {
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

        public Info query(int ql, int qr, int l, int r, int i) {
            if (ql <= l && r <= qr) {
                return infos[i];
            } else {
                Info ans = e();
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
        public int findFirst(int L, int R, Info x, int l, int r, int i) {
            if (r < L || l > R || !check0(infos[i], x))
                return -1;
            if (l == r) {
                return l;
            }
            int mid = l + ((r - l) >> 1);
            down(i, mid - l + 1, r - mid);
            if (L <= mid) {
                int p = findFirst(L, R, x, l, mid, i << 1);
                if (p >= 0)
                    return p;
            }
            return findFirst(L, R, x, mid + 1, r, i << 1 | 1);
        }

        // 线段树二分 查询最后一个
        public int findLast(int L, int R, Info x, int l, int r, int i) {
            if (r < L || l > R || !check0(infos[i], x))
                return -1;
            if (l == r) {
                return l;
            }
            int mid = l + ((r - l) >> 1);
            down(i, mid - l + 1, r - mid);
            if (L >= mid) {
                int p = findLast(L, R, x, mid + 1, r, i << 1 | 1);
                if (p >= 0)
                    return p;
            }
            return findLast(L, R, x, l, mid, i << 1);
        }

    }

    /***************************************线段树模板结束**************************************/
}
