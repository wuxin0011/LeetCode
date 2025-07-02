package template.chunk;

import java.util.Arrays;
import java.util.Random;

/**
 * - 求区间最值 最大值最小值 这个线段树能做
 * - 区间求和 这个线段树能做
 * - 区间 `[L,R]` 求满足 `>= val` 的个数,说明一下
 * -  `>val` 可以通过 查询 `val + 1` 来转换
 * -  `<val` 可以通过 查询`(R - L + 1) - >=val` 来转换
 * -  `==val` 可以通过 查询 `(>=val) - (>= val + 1)` 来转换
 * -  `<= val` 可以通过 查询 `(R - L + 1) - (>=val + 1)` 来转换
 *
 * @author: wuxin0011
 * @Description:
 */
public class _1_query_interval_val {

    static boolean ok = true;
    static int test_max_len = 1000;

    public static void main(String[] args) {
//		int T = (int) (Math.random() * 10 + 10);
        int T = 100000;
        double t1 = 0, t2 = 0;

        flag:
        for (int compareTime = 1; T > 0 && ok; T--, compareTime++) {
            int n = (int) (Math.random() * ((int) test_max_len) + 1);
            n = Math.min(n, 20);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = (int) (Math.random() * 10 + 1);
            }
            int[] b = Arrays.copyOf(a, a.length);
            int op = new Random().nextInt(10);
            int l = (int) (Math.random() * n);
            int r = (int) (Math.random() * n);
            int L = Math.min(l, r);
            int R = Math.max(l, r);
//			System.out.println(op);
            int val = (int) (Math.random() * 10 + 1);
            Block block = new Block(b);
            if (op == 0) {
                add(a, L, R, val);
                block.add(L, R, val);
            } else if (op == 1) {
                update(a, L, R, val);
                block.update(L, R, val);
            } else if (op == 2) {
                long x = queryMaxCount(a, L, R, val);
                long y = block.query(L, R, val, BlockOp.QUERY_MAX_COUNT);
                if (x != y) {
                    System.out.println(x + " " + y);
                    throw new RuntimeException("query count is Error");
                }

            } else if (op == 3) {
                long x = queryMax(a, L, R);
                long y = block.query(L, R, 0, BlockOp.QUERY_MAX);
                if (x != y) {
                    System.out.println(x + " " + y);
                    throw new RuntimeException("query max is Error");
                }
            } else if (op == 4) {
                // 求 <= val 可以利用上面 >=(val + 1) 来做 这里提供一个接口
                long x = queryMin(a, L, R);
                long y = block.query(L, R, 0, BlockOp.QUERY_MIN);
                if (x != y) {
                    System.out.println(x + " " + y);
                    throw new RuntimeException("query min is Error");
                }
            } else if (op == 5) {
//				if (!Arrays.equals((long[])a, block.getArray())) {
//					throw new RuntimeException("query array is Error");
//				}
            } else if (op == 6) {
                long x = querySum(a, L, R);
                long y = block.query(L, R, 0, BlockOp.QUERY_SUM);
                if (x != y) {
                    System.out.println(x + " " + y);
                    throw new RuntimeException("query min is Error");
                }
            } else if (op == 7) {
                long x = queryMinCount(a, L, R, val);
                long y = block.query(L, R, val, BlockOp.QUERY_MIN_COUNT);
                if (x != y) {
                    System.out.println(x + " " + y);
                    throw new RuntimeException("query min count is Error");
                }
            } else if (op == 8) {
                int sz = R - L + 1;
                long x = queryMinCount(a, L, R, val);
                long y = sz - block.query(L, R, val + 1, BlockOp.QUERY_MAX_COUNT);
                if (x != y) {
                    System.out.println(x + " " + y);
                    throw new RuntimeException("query convert count is Error");
                }
            } else if (op == 9) {
                long x = queryEqualCount(a, L, R, val);
                long max_count = block.query(L, R, val, BlockOp.QUERY_MAX_COUNT);
                long max_count_ADD_1 = block.query(L, R, val + 1, BlockOp.QUERY_MAX_COUNT);
                long y = max_count - max_count_ADD_1;
                if (y != x) {
                    System.out.println(x + " " + y);
                    throw new RuntimeException("query convert count is Error");
                }
            } else {
                // ignore
            }

        }

        System.out.println(ok ? "ok" : "fail");

    }

    public enum BlockOp {
        UPDATE, ADD, QUERY_MAX, QUERY_MIN, QUERY_MAX_COUNT, QUERY_MIN_COUNT, QUERY_SUM;
    }

    public static class Block {
        int n, blockSize, blockNumber;
        int[] bl, br;
        long[] addHistory, updateHistory, a, sort, sum;
        boolean[] updateFlag;

        public Block(int[] array) {
            this.n = array.length;
            this.blockSize = (int) Math.sqrt(n);
            this.blockNumber = (n + blockSize - 1) / blockSize;
            this.a = new long[n];
            this.bl = new int[blockNumber];
            this.br = new int[blockNumber];
            this.addHistory = new long[blockNumber];
            this.updateHistory = new long[blockNumber];
            this.updateFlag = new boolean[blockNumber];
            this.sum = new long[blockNumber];
            this.sort = new long[n];
            Arrays.fill(bl, n + 20);
            Arrays.fill(br, -1);
            // 构建分块
            for (int i = 0; i < n; i++) {

                // 初始化
                this.a[i] = array[i];
                this.sort[i] = array[i];

                // 分块
                int blockIndex = get(i);
                bl[blockIndex] = Math.min(bl[blockIndex], i);
                br[blockIndex] = Math.max(br[blockIndex], i);
                sum[blockIndex] += array[i];
            }

            // 每个分块排序
            for (int blockIndex = 0; blockIndex < blockNumber; blockIndex++) {
                Arrays.sort(sort, bl[blockIndex], br[blockIndex] + 1);
            }
        }

        public int get(int i) {
            return i / blockSize;
        }



        private long getRawValue(int i) {
            int blockIndex = get(i);
            if (updateFlag[blockIndex]) {
                return updateHistory[blockIndex];
            }
            return a[i];
        }

        long[] getArray() {
            long[] b = new long[n];
            for (int i = 0; i < n; i++) {
                b[i] = getValue(i);
            }
            Arrays.fill(addHistory, 0);
            Arrays.fill(updateFlag, false);
            Arrays.fill(updateHistory, 0);
            return b;
        }

        // 暴力更新部分
        private void updateSomeBlock(int l, int r, int val, BlockOp op) {
            int blockIndex = get(l);
            if (updateFlag[blockIndex]) {
                Arrays.fill(a, bl[blockIndex], br[blockIndex] + 1, updateHistory[blockIndex]);
                addHistory[blockIndex] = 0;
                updateFlag[blockIndex] = false;
            }
            if (addHistory[blockIndex] != 0) {
                for (int i = bl[blockIndex]; i <= br[blockIndex]; i++) {
                    a[i] += addHistory[blockIndex];
                }
                addHistory[blockIndex] = 0;
            }
            for (int i = l; i <= r; i++) {
                if (op == BlockOp.UPDATE) {
                    sum[blockIndex] += val - a[i];
                    a[i] = val;
                } else if (op == BlockOp.ADD) {
                    sum[blockIndex] += val;
                    a[i] += val;
                }
            }
            sum[blockIndex] = 0;
            for (int i = bl[blockIndex]; i <= br[blockIndex]; i++) {
                sort[i] = a[i] = getRawValue(i);
                sum[blockIndex] += a[i];
            }
            Arrays.sort(sort, bl[blockIndex], br[blockIndex] + 1);
            updateFlag[blockIndex] = false;
            addHistory[blockIndex] = 0;
        }

        // 懒更新一个块
        void lazyOp(int blockIndex, int v, BlockOp op) {
            int blockSize = br[blockIndex] - bl[blockIndex] + 1;
            if (op == BlockOp.UPDATE) {
                updateHistory[blockIndex] = v;
                sum[blockIndex] = 1L * v * blockSize;
                updateFlag[blockIndex] = true;
                addHistory[blockIndex] = 0;
            } else if (op == BlockOp.ADD) {
                sum[blockIndex] += 1L * v * blockSize;
                if (updateFlag[blockIndex]) {
                    updateHistory[blockIndex] += v;
                } else {
                    addHistory[blockIndex] += v;
                }
            } else {
                unknowOp();
            }
        }

        public void update(int l, int r, int v) {
            blockOperationInterface(l, r, v, BlockOp.UPDATE);
        }

        public void add(int l, int r, int v) {
            blockOperationInterface(l, r, v, BlockOp.ADD);
        }

        // 块操作提供的接口
        public void blockOperationInterface(int l, int r, int v, BlockOp op) {
            int blockL = get(l), blockR = get(r);
            // 左块（部分或完整）
            if (blockL == blockR) {
                if (l == bl[blockL] && r == br[blockR]) {
                    lazyOp(blockL, v, op);
                } else {
                    updateSomeBlock(l, r, v, op);
                }
            } else {
                // 左块
                updateSomeBlock(l, br[blockL], v, op);
                // 中间块（完整）
                for (int i = blockL + 1; i <= blockR - 1; i++) {
                    lazyOp(i, v, op);
                }

                // 右块
                if (r == br[blockR]) {
                    lazyOp(blockR, v, op);
                } else {
                    updateSomeBlock(bl[blockR], r, v, op);
                }
            }
        }

        private long queryFullBlock(int blockIndex, BlockOp op, long ans, long v) {
            int sz = (br[blockIndex] - bl[blockIndex] + 1);
            if (op == BlockOp.QUERY_MAX || op == BlockOp.QUERY_MIN) {
                ans = queryBlock(blockIndex, op, ans);
            } else if (op == BlockOp.QUERY_MAX_COUNT || op == BlockOp.QUERY_MIN_COUNT) {
                boolean isMAX = BlockOp.QUERY_MAX_COUNT == op;
                if (updateFlag[blockIndex]) {
                    ans += (isMAX ? updateHistory[blockIndex] >= v : updateHistory[blockIndex] <= v) ? sz : 0;
                } else {
                    int cnt = lowerboundQueryCount(blockIndex, v - addHistory[blockIndex] + (isMAX ? 0 : 1));
                    ans += (isMAX ? cnt : sz - cnt);
                }
            } else if (op == BlockOp.QUERY_SUM) {
                ans += sum[blockIndex];
            } else {
                unknowOp();
            }
            return ans;
        }

        private static void unknowOp() {
            throw new RuntimeException("该操作未实现！");
        }

        private long querySomeBlock(int l, int r, BlockOp op, long ans, long v) {
            for (int i = l; i <= r; i++) {
                long val = getValue(i);
                if (op == BlockOp.QUERY_MAX) {
                    ans = Math.max(ans, val);
                } else if (op == BlockOp.QUERY_MIN) {
                    ans = Math.min(ans, val);
                } else if (op == BlockOp.QUERY_MAX_COUNT) {
                    ans += val >= v ? 1 : 0;
                } else if (op == BlockOp.QUERY_MIN_COUNT) {
                    ans += val <= v ? 1 : 0;
                } else if (op == BlockOp.QUERY_SUM) {
                    ans += val;
                } else {
                    unknowOp();
                }
            }
            return ans;
        }

        public long query(int l, int r, long v, BlockOp op) {
            long ans = 0;
            if (op == BlockOp.QUERY_MIN || op == BlockOp.QUERY_MAX) {
                ans = getValue(l);
            }

            int blockL = get(l), blockR = get(r);
            if (blockL == blockR) {
                if (l == bl[blockL] && r == br[blockL]) {
                    ans = queryFullBlock(blockL, op, ans, v);
                } else {
                    ans = querySomeBlock(l, r, op, ans, v);
                }
            } else {
                // 左块
                ans = querySomeBlock(l, br[blockL], op, ans, v);
                // 中间块
                for (int blockIndex = blockL + 1; blockIndex <= blockR - 1; blockIndex++) {
                    ans = queryFullBlock(blockIndex, op, ans, v);
                }
                // 右块
                if (r == br[blockR]) {
                    ans = queryFullBlock(blockR, op, ans, v);
                } else {
                    ans = querySomeBlock(bl[blockR], r, op, ans, v);
                }
            }
            return ans;
        }

        private long queryBlock(int blockIndex, BlockOp op, long ans) {
            if (updateFlag[blockIndex]) {
                long val = updateHistory[blockIndex];
                return op == BlockOp.QUERY_MAX ? Math.max(ans, val) : Math.min(ans, val);
            } else {
                long blockMin = Long.MAX_VALUE;
                long blockMax = Long.MIN_VALUE;
                for (int i = bl[blockIndex]; i <= br[blockIndex]; i++) {
                    long val = getValue(i);
                    blockMin = Math.min(blockMin, val);
                    blockMax = Math.max(blockMax, val);
                }
                return op == BlockOp.QUERY_MAX ? Math.max(ans, blockMax) : Math.min(ans, blockMin);
            }
        }

        private long getValue(int i) {
            int blockIndex = get(i);
            if (updateFlag[blockIndex]) {
                return updateHistory[blockIndex];
            } else {
                return a[i] + addHistory[blockIndex];
            }
        }

        public int lowerboundQueryCount(int blockIndex, long x) {
            if (updateFlag[blockIndex]) {
                return updateHistory[blockIndex] >= x ? (br[blockIndex] - bl[blockIndex] + 1) : 0;
            }

            // 确保sort数组是最新的
            if (addHistory[blockIndex] != 0) {
                for (int i = bl[blockIndex]; i <= br[blockIndex]; i++) {
                    sort[i] = getRawValue(i) + addHistory[blockIndex];
                }
                Arrays.sort(sort, bl[blockIndex], br[blockIndex] + 1);
                addHistory[blockIndex] = 0;
            }

            // 二分查找
            int left = bl[blockIndex];
            int right = br[blockIndex];
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (sort[mid] >= x) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return br[blockIndex] - left + 1;
        }
    }

    // 暴力测试部分
    // =======================================================

    public static long queryMaxCount(int[] a, int l, int r, int v) {
        long ans = 0;
        for (int i = l; i <= r; i++) {
            if (a[i] >= v)
                ans++;
        }
        return ans;
    }


    public static long queryEqualCount(int[] a, int l, int r, int v) {
        long ans = 0;
        for (int i = l; i <= r; i++) {
            if (a[i] == v)
                ans++;
        }
        return ans;
    }

    public static long queryMinCount(int[] a, int l, int r, int v) {
        long ans = 0;
        for (int i = l; i <= r; i++) {
            if (a[i] <= v)
                ans++;
        }
        return ans;
    }


    public static long querySum(int[] a, int l, int r) {
        long ans = 0;
        for (int i = l; i <= r; i++) {
            ans += a[i];
        }
        return ans;
    }

    public static long queryMax(int[] a, int l, int r) {
        int ans = a[l];
        for (int i = l; i <= r; i++) {
            ans = Math.max(ans, a[i]);
        }
        return ans;
    }

    public static long queryMin(int[] a, int l, int r) {
        int ans = a[l];
        for (int i = l; i <= r; i++) {
            ans = Math.min(ans, a[i]);
        }
        return ans;
    }

    public static void update(int[] a, int l, int r, int v) {
        for (int i = l; i <= r; i++) {
            a[i] = v;
        }
    }

    public static void add(int[] a, int l, int r, int v) {
        for (int i = l; i <= r; i++) {
            a[i] += v;
        }
    }
}
