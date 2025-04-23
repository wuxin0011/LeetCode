package template.Fenwick;

/**
 * @author: wuxin0011
 * @Description: 树状数组维持等差差分 也可以用线段树解决 但是树状数组常数更好
 */
public class FenwickTemplate4_diff {


    // 模板1 针对公式 ar = al + k + (r - l)*d
    // https://www.luogu.com.cn/record/214837394
    public static class FenwickTemplate4_diff_1 {


        private static int N = (int) 1e5 + 100;
        private static long[] bits1 = new long[N], bits2 = new long[N], d = new long[N];
        private static int n;


        // array 下标从1开始 [1,size]
        private static void build(int[] array, int size) {
            n = size;
            for (int i = 1; i <= size; i++) {
                d[i] = array[i] - array[i - 1];
                add(i, d[i]);
                add(i + 1, -d[i]);
            }
        }

        private static int lowbit(int x) {
            return x & (-x);
        }

        private static void add(int x, long delta) {
            long id = x;
            while (x <= n) {
                bits1[x] += delta;
                bits2[x] += delta * id;
                x += lowbit(x);
            }
        }

        // ar = al + k + (r - l)*d
        private static void add(int l, int r, int k, int d) {
            add(l, k);
            add(l + 1, d - k);
            add(r + 1, -((r - l + 1) *1L * d + k));
            add(r + 2, d + (r - l) *1L * d);
        }

        private static long query(int x) {
            long id = x, sum = 0;
            while (x > 0) {
                sum += (id + 1) * bits1[x] - bits2[x];
                x -= lowbit(x);
            }
            return sum;
        }


    }


}
