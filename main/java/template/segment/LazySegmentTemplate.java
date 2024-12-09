package template.segment;

/**
 * @author: wuxin0011
 * @Description: 基本模板
 */

@SuppressWarnings("all")
public class LazySegmentTemplate {


    @FunctionalInterface
    public static interface Operation {
        long operation(long x, long y);
    }


    public static class LazySegment {
        public int n, MAXN;
        public long vals[], addVal[], changeVal[], array[], initVal;
        public boolean[] vis;
        Operation op;

        public LazySegment(int n, Operation op, long initVal) {
            this.n = n;
            this.op = op;
            this.initVal = initVal;
            this.array = new long[n + 1];
            this.MAXN = (n << 2);
            this.vals = new long[MAXN];
            this.addVal = new long[MAXN];
            this.changeVal = new long[MAXN];
            this.vis = new boolean[MAXN];
        }


        public void up(int i) {
            vals[i] = op.operation(vals[i << 1], vals[i << 1 | 1]);
        }

        public void addLazy(int i, long v, int size) {
            vals[i] += v;
            addVal[i] += v;
//            vals[i] += n *1L* v;
//           query sums
        }

//        public void updateLazy(int i, long v, int size) {
//            vals[i] = v;
//            addVal[i] = 0;
//            vis[i] = true;
//            changeVal[i] = v;
//        }
//
//
//        public void down(int i, int lsize, int rsize) {
//            if (vis != null && vis[i]) {
//                updateLazy(i << 1, changeVal[i], lsize);
//                updateLazy(i << 1 | 1, changeVal[i], rsize);
//                vis[i] = false;
//            }
//            if (addVal != null && addVal[i] != 0) {
//                addLazy(i << 1, addVal[i], lsize);
//                addLazy(i << 1 | 1, addVal[i], rsize);
//                addVal[i] = 0;
//            }
//        }

        public void init() {
            this.init(1, n, 1);
        }

        public long query(int l, int r) {
            return this.query(l, r, 1, n, 1);
        }

        public void init(int l, int r, int i) {
            if (l == r) {
                vals[i] = array[l];
            } else {
                int mid = l + ((r - l) >> 1);
                init(l, mid, i << 1);
                init(mid + 1, r, i << 1 | 1);
                up(i);
            }
        }

        public void add(int L, int R, long V, int l, int r, int i) {
            if (L <= 0 || L > R || L > n || R > n) {
                throw new RuntimeException("out");
            }
            if (l == r) {
                vals[i] += V;
//                addLazy(i, V, r - l + 1);
            } else {
                int mid = l + ((r - l) >> 1);
                // down(i, mid - l + 1, r - mid);
                if (L <= mid) {
                    add(L, R, V, l, mid, i << 1);
                }
                if (R > mid) {
                    add(L, R, V, mid + 1, r, i << 1 | 1);
                }
                up(i);
            }
        }
        public void update(int L, int R, long V, int l, int r, int i) {
            if (L <= 0 || L > R || L > n || R > n) {
                throw new RuntimeException("out");
            }
            if (l == r) {
                // updateLazy(i, V, r - l + 1);
                vals[i]= V;
            } else {
                int mid = l + ((r - l) >> 1);
//                down(i, mid - l + 1, r - mid);
                if (L <= mid) {
                    update(L, R, V, l, mid, i << 1);
                }
                if (R > mid) {
                    update(L, R, V, mid + 1, r, i << 1 | 1);
                }
                up(i);
            }
        }

        public long query(int L, int R, int l, int r, int i) {
            if (L <= 0 || L > R || L > n || R > n) {
                throw new RuntimeException("out");
            }
            if (L <= l && r <= R) {
                return vals[i];
            } else {
                int mid = l + ((r - l) >> 1);
                long ans = initVal;
                if (L <= mid) {
                    ans = op.operation(ans, query(L, R, l, mid, i << 1));
                }
                if (R > mid) {
                    ans = op.operation(ans, query(L, R, mid + 1, r, i << 1 | 1));
                }
                return ans;
            }
        }
    }


    public static void main(String[] args) {
        long initVal = 0;
        Operation operation = (a, b) -> a + b;
        int T = 10; // 测试次数
        boolean ok = true;
        while (--T > 0 && ok) {
            int n = 100;
            int[] nums = new int[n + 1];

            LazySegment seg = new LazySegment(n, operation, initVal);

            for (int i = 1; i <= n; i++) {
                boolean isNeg = Math.random() > 0.5;
                int v = (int) (Math.random() * 12);
                nums[i] = isNeg ? -v : v;
                seg.array[i] = nums[i];
            }

            seg.init(1,n,1);

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
            ans = op.operation(ans, array[i]);
        }
        return ans;
    }

}
