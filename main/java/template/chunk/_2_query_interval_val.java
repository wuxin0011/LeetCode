package template.chunk;

import java.util.Arrays;
import java.util.Random;

/**
 * 测试通过
 * https://www.luogu.com.cn/record/222398355
 *
 * @author: wuxin0011
 * @Description:
 */
public class _2_query_interval_val {

    static boolean ok = true;
    static int test_max_len = (int) 1e6;

    public static void main(String[] args) {

        int T = 100000;
        double t1 = 0, t2 = 0;

        flag:
        for (int compareTime = 1; T > 0 && ok; T--, compareTime++) {
            int n = (int) (Math.random() * ((int) test_max_len) + 1);

            long st = 0, ed = 0;
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

            int val = (int) (Math.random() * 10 + 1);
            Block block = new Block(b);
            if (op == 0) {
                st = System.currentTimeMillis();
                add(a, L, R, val);
                ed = System.currentTimeMillis();
                t1 += ed - st;
                st = ed;
                block.add(L, R, val);
                ed = System.currentTimeMillis();
                t2 += ed - st;
            } else if (op == 1) {
                st = System.currentTimeMillis();
                long x = queryMaxCount(a, L, R, val);
                ed = System.currentTimeMillis();
                t1 += ed - st;
                st = ed;
                long y = block.query(L, R, val);
                ed = System.currentTimeMillis();
                t2 += ed - st;
                if (x != y) {
                    System.out.println(x + " " + y);
                    throw new RuntimeException("query count is Error");
                }

            }

        }

        System.out.println(ok ? "ok" : "fail");
        System.out.println("暴力用时: " + t1 + " ms");
        System.out.println("分块用时: " + t2 + " ms");

    }

    static class Block {

        long[] a, sortedVal, addVal;
        int sz;
        int blockNum;
        int[] bl;
        int[] br;
        boolean[] needSort;

        Block(int[] array) {
            int n = array.length;
            a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = array[i];
            }
            sortedVal = new long[n];
            addVal = new long[n + 1];

            sz = (int) Math.sqrt(n);
            blockNum = (n + sz - 1) / sz;

            bl = new int[blockNum];
            br = new int[blockNum];
            needSort = new boolean[blockNum];
            Arrays.fill(needSort,true);
            Arrays.fill(bl, Integer.MAX_VALUE);
            Arrays.fill(br, Integer.MIN_VALUE);


            for (int i = 0; i < n; i++) {
                int blockIndex = i / sz;
                bl[blockIndex] = Math.min(bl[blockIndex], i);
                br[blockIndex] = Math.max(br[blockIndex], i);
            }


            System.arraycopy(a, 0, sortedVal, 0, n);
        }

        private void sortBlock(int blockIndex) {
            int l = bl[blockIndex];
            int r = br[blockIndex];
            for (int i = l; i <= r; i++) {
                a[i] += addVal[blockIndex];
                sortedVal[i] = a[i];
            }
            addVal[blockIndex] = 0;
            needSort[blockIndex] = true;
            // Arrays.sort(sortedVal, l, r + 1);
        }

        private int calc(int blockIndex, long v) {
            int r = br[blockIndex];
            int l = bl[blockIndex];
            if (needSort[blockIndex]) {
                Arrays.sort(sortedVal, l, r + 1);
                needSort[blockIndex] = false;
            }
            int end = r;
            v -= addVal[blockIndex];
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                if (sortedVal[mid] >= v) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            return end - l + 1;
        }

        private long query(int l, int r, long v) {
            long ans = 0;
            int blockL = l / sz;
            int blockR = r / sz;

            if (blockL == blockR) {
                if (bl[blockL] == l && r == br[blockR]) {
                    ans += calc(blockL, v);
                } else {
                    for (int i = l; i <= r; i++) {
                        if (a[i] + addVal[blockL] >= v) {
                            ans++;
                        }
                    }
                }
            } else {

                for (int i = l; i <= br[blockL]; i++) {
                    if (a[i] + addVal[blockL] >= v) {
                        ans++;
                    }
                }


                for (int i = blockL + 1; i <= blockR - 1; i++) {
                    ans += calc(i, v);
                }


                if (br[blockR] == r) {
                    ans += calc(blockR, v);
                } else {
                    for (int i = bl[blockR]; i <= r; i++) {
                        if (a[i] + addVal[blockR] >= v) {
                            ans++;
                        }
                    }
                }
            }
            return ans;
        }

        private void add(int l, int r, long v) {
            int blockL = l / sz;
            int blockR = r / sz;

            if (blockL == blockR) {
                if (bl[blockL] == l && r == br[blockR]) {
                    addVal[blockL] += v;
                } else {
                    for (int i = l; i <= r; i++) {
                        a[i] += v;
                    }
                    sortBlock(blockL);
                }
            } else {

                for (int i = l; i <= br[blockL]; i++) {
                    a[i] += v;
                }
                sortBlock(blockL);


                for (int i = blockL + 1; i <= blockR - 1; i++) {
                    addVal[i] += v;
                }


                if (br[blockR] == r) {
                    addVal[blockR] += v;
                } else {
                    for (int i = bl[blockR]; i <= r; i++) {
                        a[i] += v;
                    }
                    sortBlock(blockR);
                }
            }
        }
    }


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
