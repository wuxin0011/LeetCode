package template.segment;

/**
 * @author: wuxin0011
 * @Description: 线段树基础版本 懒更新 + 最值查询 + 区间求和
 */
public class Segment1 {

    public static class Segment {
        public int N;
        public int[] arr;
        public int MAXN;
        public long[] sum, add, change, max, min;
        public boolean[] update;
        boolean isCheck = true;

        public Segment() {
            this((int) 1e5);
        }

        public Segment(int N) {
            this.N = N;
            this.MAXN = N * 4 + 1;
            this.arr = new int[N + 2];
            this.sum = new long[MAXN];
            this.add = new long[MAXN];
            this.change = new long[MAXN];
            this.update = new boolean[MAXN];
            this.max = new long[MAXN];
            this.min = new long[MAXN];
        }


        public void up(int i) {
            if (sum != null) {
                sum[i] = sum[i << 1] + sum[i << 1 | 1];
            }
            if (max != null) {
                max[i] = Math.max(max[i << 1], max[i << 1 | 1]);
            }
            if (min != null) {
                min[i] = Math.min(min[i << 1], min[i << 1 | 1]);
            }
        }

        public void addLazy(int i, long v, int n) {
            if (sum != null) {
                sum[i] += n * v;
                add[i] += v;
            }
            if (max != null) {
                max[i] += v;
            }
            if (min != null) {
                min[i] += v;
            }
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

        public long querySum(int l, int r) {
            return this.querySum(l, r, 1, N, 1);
        }

        public long queryMax(int l, int r) {
            return this.queryMax(l, r, 1, N, 1);
        }

        public long queryMin(int l, int r) {
            return this.queryMin(l, r, 1, N, 1);
        }

        public void init(int l, int r, int i) {
            if (l == r) {
                if (sum != null) {
                    sum[i] = arr[l];
                }
                if (max != null) {
                    max[i] = arr[l];
                }
                if (min != null) {
                    min[i] = arr[l];
                }
            } else {
                int mid = l + ((r - l) >> 1);
                init(l, mid, i << 1);
                init(mid + 1, r, i << 1 | 1);
                up(i);
            }
            if (add != null) {
                add[i] = 0;
            }
            if (update != null && change != null) {
                update[i] = false;
                change[i] = 0;
            }
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
            if (isCheck) {
                if (L <= 0 || L > R || L > N || R > N) {
                    return;
                }
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
            if (isCheck) {
                if (update == null || change == null) {
                    throw new RuntimeException("update is null or change is null");
                }
                if (L <= 0 || L > R || L > N || R > N) {
                    return;
                }
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

        // 参数解释同 add
        public long querySum(int L, int R, int l, int r, int i) {
            if (isCheck) {
                if (sum == null) {
                    throw new RuntimeException("sum is null");
                }
                if (L <= 0 || L > R || L > N || R > N) {
                    return 0;
                }
            }

            if (L <= l && r <= R) {
                return sum[i];
            } else {
                int mid = l + ((r - l) >> 1);
                down(i, mid - l + 1, r - mid);
                long ans = 0;

                if (L <= mid) {
                    ans += querySum(L, R, l, mid, i << 1);
                }

                if (R > mid) {
                    ans += querySum(L, R, mid + 1, r, i << 1 | 1);
                }

                return ans;
            }
        }

        // 参数解释同 add
        public long queryMax(int L, int R, int l, int r, int i) {
            if (isCheck) {
                if (max == null) {
                    throw new RuntimeException("max is null");
                }
                if (L <= 0 || L > R || L > N || R > N) {
                    return Long.MIN_VALUE;
                }
            }

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

            if (isCheck) {
                if (min == null) {
                    throw new RuntimeException("min is null");
                }
                if (L <= 0 || L > R || L > N || R > N) {
                    return Long.MAX_VALUE;
                }
            }

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








    public static void main(String[] args) {


        int MAXN = 10000;
        Segment segment = new Segment(MAXN + 1);

        int T = 1000; // 测试次数
        boolean ok = true;
        while (--T > 0 && ok) {

            int N = Math.max(100, (int) (Math.random() * MAXN));

            int[] nums = new int[N + 10];

            for (int i = 1; i <= N; i++) {
                boolean isNeg = Math.random() > 0.5;
                int v = (int) (Math.random() * 1000);
                nums[i] = isNeg ? -v : v;
                segment.arr[i] = nums[i];
            }

            segment.init(1, N, 1);

            int k = 100;

            while (--k > 0) {

                boolean isNeg = Math.random() > 0.5;
                int v = (int) (Math.random() * 1000);
                if (isNeg) v = -v;

                // 注意要 + 1
                int a = (int) (Math.random() * N + 1);
                int b = (int) (Math.random() * N + 1);

                // 设置左右区间
                int l = Math.min(a, b), r = Math.max(a, b);

                // 0.2 add
                // 0.4 update
                // 0.6 query sum
                // 0.8 query max
                // > 0.8 query min
                double op = Math.random();


                long result = 0, expect = 0;
                if (op < 0.2) {
                    // add
                    segment.add(l, r, v, 1, N, 1);
                    checkAdd(l, r, v, nums);
                } else if (op < 0.4) {
                    segment.change(l, r, v, 1, N, 1);
                    checkChange(l, r, v, nums);
                } else if (op < 0.6) {
                    result = segment.querySum(l, r, 1, N, 1);
                    expect = checkSum(l, r, nums);
                    if (result != expect) {
                        ok = false;
                        System.out.println("query sum error" + " { " + l + " , " + r + " }");
                        break;
                    }
                } else if (op < 0.8) {
                    result = segment.queryMax(l, r, 1, N, 1);
                    expect = checkMax(l, r, nums);
                    if (result != expect) {
                        ok = false;
                        System.out.println("query max error" + " { " + l + " , " + r + " }");
                        break;
                    }
                } else {
                    result = segment.queryMin(l, r, 1, N, 1);
                    expect = checkMin(l, r, nums);
                    if (result != expect) {
                        ok = false;
                        System.out.println("query min error" + " { " + l + " , " + r + " }");
                        break;
                    }
                }

            }
        }

        System.out.println(ok ? "ok" : "fail");

    }


    // 暴力测试部分
    public static void checkAdd(int l, int r, long V, int[] nums) {
        for (int i = l; i <= r; i++) {
            nums[i] += V;
        }
    }

    public static void checkChange(int l, int r, int V, int[] nums) {
        for (int i = l; i <= r; i++) {
            nums[i] = V;
        }
    }


    public static long checkSum(int l, int r, int[] nums) {
        long ans = 0;
        for (int i = l; i <= r; i++) {
            ans += nums[i];
        }
        return ans;
    }

    public static long checkMax(int l, int r, int[] nums) {
        long ans = Long.MIN_VALUE;
        for (int i = l; i <= r; i++) {
            ans = Math.max(ans, nums[i]);
        }
        return ans;
    }

    public static long checkMin(int l, int r, int[] nums) {
        long ans = Long.MAX_VALUE;
        for (int i = l; i <= r; i++) {
            ans = Math.min(ans, nums[i]);
        }
        return ans;
    }



}
