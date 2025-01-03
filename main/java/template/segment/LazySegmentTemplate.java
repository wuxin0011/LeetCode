package template.segment;

/**
 * @author: wuxin0011
 * @Description: 基本模板
 */

@SuppressWarnings("all")
public class LazySegmentTemplate {


    public static interface Operation {
        long op(long x, long y);
    }

    public static int MAXN = (int) 1e5 + 1;

    public static class LazySegment {

        public int[] arr;
        public int[] add;
        public int[] change;
        public boolean[] vis;
        public long[] vals;
        Operation operation;
        long initVal;
        int n;

        LazySegment(int n, Operation operation, long initVal) {
            this.initVal = initVal;
            this.n = n;
            this.arr = new int[n + 1];
            this.add = new int[n << 2];
            this.change = new int[n << 2];
            this.vis = new boolean[n << 2];
            this.add = new int[n << 2];
            this.vals = new long[n << 2];
            this.operation = operation;
        }

        public void up(int i) {
            vals[i] = operation.op(vals[i << 1], vals[i << 1 | 1]);
        }

        public void build(int l, int r, int i) {
            if (l == r) {
                vals[i] = arr[l];
            } else {
                int mid = l + ((r - l) >> 1);
                build(l, mid, i << 1);
                build(mid + 1, r, i << 1 | 1);
                up(i);
            }
            vis[i] = false;
            change[i] = 0;
            add[i] = 0;
        }

        public void addLazy(int i, int size, int v) {
            add[i] += v;

            // if other op
             vals[i] += v;

            // if op is sum
//            vals[i] += v * 1L * size;
        }

        public void updateLazy(int i, int size, int v) {
            add[i] = 0;
            change[i] = v;
            vis[i] = true;
            // if other op
             vals[i] = v;

            // if op is sum
            // vals[i] = v * 1L * size;
        }

        public void down(int i, int ln, int rn) {
            if (vis[i]) {
                updateLazy(i << 1, ln, change[i]);
                updateLazy(i << 1 | 1, rn, change[i]);
                vis[i] = false;
            }
            if (add[i] != 0) {
                addLazy(i << 1, ln, add[i]);
                addLazy(i << 1 | 1, rn, add[i]);
                add[i] = 0;
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
                return vals[i];
            } else {
                long ans = initVal;
                int mid = l + ((r - l) >> 1);
                down(i, mid - l + 1, r - mid);
                if (ql <= mid) {
                    ans = operation.op(ans, query(ql, qr, l, mid, i << 1));
                }
                if (qr > mid) {
                    ans = operation.op(ans, query(ql, qr, mid + 1, r, i << 1 | 1));
                }
                return ans;
            }
        }

    }


    public static void main(String[] args) {
        long initVal = Long.MIN_VALUE;
        Operation operation = (a, b) -> Math.max(a,b);
        int T = 10000; // 测试次数
        boolean ok = true;
        while (--T > 0 && ok) {
            int n = 100;
            int[] nums = new int[n + 1];

            LazySegment seg = new LazySegment(n, operation, initVal);

            for (int i = 1; i <= n; i++) {
                boolean isNeg = Math.random() > 0.5;
                int v = (int) (Math.random() * 12);
                nums[i] = isNeg ? -v : v;
                seg.arr[i] = nums[i];
            }

            seg.build(1, n, 1);

            int k = 100;

            while (--k > 0) {

                boolean isNeg = Math.random() > 0.5;
                int v = (int) (Math.random() * 1000);
                if (isNeg) v = -v;

                // 注意要 + 1
                int a = (int) ((Math.random() * n) + 1);
                int b = (int) ((Math.random() * n) + 1);


                // 设置左右区间
                int L = Math.min(a, b);
                int R = Math.max(a, b);
//                System.out.printf("{%s %s} n = %s\n",L,R,n);


                double op = Math.random();


                long result = 0, expect = 0;
                if (op < 0.2) {
                    seg.add(L, R, v, 1, n, 1);
                    checkAdd(L, R, v, nums);
                } else if (op < 0.4) {
                    seg.update(L, R, v, 1, n, 1);
                    checkChange(L, R, v, nums);
                } else {
                    result = seg.query(L, R, 1, n, 1);
                    expect = query(L, R, nums, operation, initVal);
                    if (result != expect) {
                        ok = false;
                        System.out.println("query sum error" + " { " + L + " , " + R + " }" + ",expect = " + expect + " result = " + result);
                        break;
                    }
                }

            }
        }
        System.out.println(ok ? "ok" : "fail");
    }


    // 暴力测试部分
    public static void checkAdd(int l, int r, long V, int[] array) {
        for (int i = l; i <= r; i++) {
            array[i] += V;
        }
    }

    public static void checkChange(int l, int r, int V, int[] array) {
        for (int i = l; i <= r; i++) {
            array[i] = V;
        }
    }


    public static long query(int l, int r, int[] array, Operation op, long initVal) {
        long ans = initVal;
        for (int i = l; i <= r; i++) {
            ans = op.op(ans, array[i]);
        }
        return ans;
    }

}
