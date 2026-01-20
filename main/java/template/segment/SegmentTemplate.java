package template.segment;

/**
 * @author: wuxin0011
 * @Description: 单点和区间修改的线段树 没有懒更新标记
 */
public class SegmentTemplate {

    static class Info_template {

        // 线段树模板开始
        static class Info {
            long a = 0;

            Info() {

            }

            Info(long x) {
                this.a = x;
            }
        }


        static Info e(){
            return new Info();
        }

        static Info op(Info l, Info r) {
            Info info = new Info();
            info.a = Math.max(l.a, r.a);
            return info;
        }

        // 线段树二分函数
        static boolean check0(Info i, Info t) {
            return false;
        }

        public static class Segment {
            Info[] infos, a;
            int n;

            public Segment(int n) {
                this.n = n;
                this.initInfo();
            }

            private void initInfo() {
                this.infos = new Info[n << 2];
                for (int i = 0; i < (n << 2); i++) {
                    infos[i] = e();
                }
                this.build(0, n - 1, 1);
            }

            public Segment(Info[] a) {
                this.a = a;
                this.n = a.length;
                this.initInfo();
            }

            private void build(int l, int r, int i) {
                if (l == r) {
                    infos[i] = a == null ? new Info() : a[l];
                } else {
                    int mid = l + ((r - l) >> 1);
                    build(l, mid, i << 1);
                    build(mid + 1, r, i << 1 | 1);
                    up(i);
                }
            }

            public void up(int i) {
                infos[i] = op(infos[i << 1], infos[i << 1 | 1]);
            }

            public Info query(int ql, int qr, int l, int r, int i) {
                if (ql <= l && r <= qr) {
                    return infos[i];
                } else {
                    int mid = l + ((r - l) >> 1);
                    Info info = e();
                    if (ql <= mid) {
                        info = op(info, query(ql, qr, l, mid, i << 1));
                    }
                    if (qr > mid) {
                        info = op(info, query(ql, qr, mid + 1, r, i << 1 | 1));
                    }
                    return info;
                }
            }

            public void update(int ql, Info x, int l, int r, int i) {
                if (l == r) {
                    infos[i] = x;
                } else {
                    int mid = l + ((r - l) >> 1);
                    if (ql <= mid) {
                        update(ql, x, l, mid, i << 1);
                    } else {
                        update(ql, x, mid + 1, r, i << 1 | 1);
                    }
                    up(i);
                }
            }

            public void add(int ql, Info x, int l, int r, int i) {
                if (l == r) {
                    infos[i] = op(infos[i], x);
                } else {
                    int mid = l + ((r - l) >> 1);
                    if (ql <= mid) {
                        update(ql, x, l, mid, i << 1);
                    } else {
                        update(ql, x, mid + 1, r, i << 1 | 1);
                    }
                }
            }

            public int findFirst(int ql, int qr, Info info, int l, int r, int i) {
                if (l > qr || r < ql || !check0(infos[i], info)) return -1;
                if (l == r) return l;
                int mid = l + ((r - l) >> 1);
                if (ql <= mid) {
                    int p = findFirst(ql, qr, info, l, mid, i << 1);
                    if (p != -1) return p;
                }
                return findFirst(ql, qr, info, mid + 1, r, i << 1 | 1);
            }

            public int findLast(int ql, int qr, Info info, int l, int r, int i) {
                if (l > qr || r < ql || !check0(infos[i], info)) return -1;
                if (l == r) return l;
                int mid = l + ((r - l) >> 1);
                if (qr > mid) {
                    int p = findLast(ql, qr, info, mid + 1, r, i << 1 | 1);
                    if (p != -1) return p;
                }
                return findLast(ql, qr, info, l, mid, i << 1);
            }
        }

        // 线段树模板结束
    }


    // 不需要维护更多信息版本 效率更高
    static class Long_template {

        // 线段树模板开始
        static long op(long l, long r) {
            return Math.max(l, r);
        }

        static long e() {
            return 0;
        }

        // 线段树二分函数
        static boolean check0(long i, long t) {
            return false;
        }

        public static class Segment {
            long[] infos, a;
            int n;

            public Segment(int n) {
                this.n = n;
                this.initInfo();
            }

            private void initInfo() {
                this.infos = new long[n << 2];
                for (int i = 0; i < (n << 2); i++) {
                    infos[i] = e();
                }
                this.build(0, n - 1, 1);
            }

            public Segment(long[] a) {
                this.a = a;
                this.n = a.length;
                this.initInfo();
            }

            private void build(int l, int r, int i) {
                if (l == r) {
                    infos[i] = a == null ? e() : a[l];
                } else {
                    int mid = l + ((r - l) >> 1);
                    build(l, mid, i << 1);
                    build(mid + 1, r, i << 1 | 1);
                    up(i);
                }
            }

            public void up(int i) {
                infos[i] = op(infos[i << 1], infos[i << 1 | 1]);
            }

            public long query(int ql, int qr, int l, int r, int i) {
                if (ql <= l && r <= qr) {
                    return infos[i];
                } else {
                    int mid = l + ((r - l) >> 1);
                    long info = e();
                    if (ql <= mid) {
                        info = op(info, query(ql, qr, l, mid, i << 1));
                    }
                    if (qr > mid) {
                        info = op(info, query(ql, qr, mid + 1, r, i << 1 | 1));
                    }
                    return info;
                }
            }

            public void update(int ql, long x, int l, int r, int i) {
                if (l == r) {
                    infos[i] = x;
                } else {
                    int mid = l + ((r - l) >> 1);
                    if (ql <= mid) {
                        update(ql, x, l, mid, i << 1);
                    } else {
                        update(ql, x, mid + 1, r, i << 1 | 1);
                    }
                    up(i);
                }
            }

            public void add(int ql, long x, int l, int r, int i) {
                if (l == r) {
                    infos[i] = op(infos[i], x);
                } else {
                    int mid = l + ((r - l) >> 1);
                    if (ql <= mid) {
                        update(ql, x, l, mid, i << 1);
                    } else {
                        update(ql, x, mid + 1, r, i << 1 | 1);
                    }
                }
            }

            public int findFirst(int ql, int qr, long info, int l, int r, int i) {
                if (l > qr || r < ql || !check0(infos[i], info))
                    return -1;
                if (l == r)
                    return l;
                int mid = l + ((r - l) >> 1);
                if (ql <= mid) {
                    int p = findFirst(ql, qr, info, l, mid, i << 1);
                    if (p != -1)
                        return p;
                }
                return findFirst(ql, qr, info, mid + 1, r, i << 1 | 1);
            }

            public int findLast(int ql, int qr, long info, int l, int r, int i) {
                if (l > qr || r < ql || !check0(infos[i], info))
                    return -1;
                if (l == r)
                    return l;
                int mid = l + ((r - l) >> 1);
                if (qr > mid) {
                    int p = findLast(ql, qr, info, mid + 1, r, i << 1 | 1);
                    if (p != -1)
                        return p;
                }
                return findLast(ql, qr, info, l, mid, i << 1);
            }
        }

        // 线段树模板结束

    }


}
