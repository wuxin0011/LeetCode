package template.Fenwick;

/**
 * @author: wuxin0011
 * @Description: 树状数组维持可差分信息 区间修改 范围查询
 */
public class FenwickTemplate2 {



    // 注意 下标是从1开始的 如果 [l,r] 对应区间和非0开始的数组区间 使用需要 +1
    static class Fenwick {
        static int MAXN = (int) 1e5 * 5 + 100;
        static long[] data1 = new long[MAXN], data2 = new long[MAXN];
        static int size;


        static int lowbit(int x) {
            return x & -x;
        }

        // [1,size]
        static void add(long[] d, int i, long v) {
            while (i <= size) {
                d[i] += v;
                i += lowbit(i);
            }
        }

        // [1,size]
        static long sum(long[] d, int i) {
            long s = 0;
            while (i > 0) {
                s += d[i];
                i -= lowbit(i);
            }
            return s;
        }

        // [l,r]
        static void add(int l, int r, long v) {
            add(data1, l, v);
            add(data1, r + 1, -v);
            add(data2, l, v * 1L * (l - 1));
            add(data2, r + 1, -v * 1L * (r + 1 - 1));
        }

        // [l,r]
        static long sum(int l, int r) {
            long ans = 0;
            ans += (sum(data1, r) * 1L * r - sum(data1, l - 1) * 1L * (l - 1));
            ans -= (sum(data2, r) - sum(data2, l - 1));
            return ans;
        }


        static void clear(int n){
            for(int i = 0;i <= n;i++) {
                data1[i] = data2[i] = 0;
            }
            size = n;
        }
    }

}
