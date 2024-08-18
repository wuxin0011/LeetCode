package template.Fenwick;

/**
 * @author: wuxin0011
 * @Description: 树状数组基础版本 单点修改 范围查询
 */
public class Fenwick1 {

    public static class Fenwick {
        int n;
        int[] tree, diff, a;

        public Fenwick(int[] a) {
            this.n = a.length;
            this.tree = new int[n + 1];
            this.diff = new int[n + 1];
            this.a = a;
        }
        public Fenwick(int n) {
            this.n = n;
            this.tree = new int[n + 1];
        }

        public void init() {
            for (int i = 0; i < n; i++) {
                this.add(i, a[i]);
            }
        }

        public int lowbit(int i) {
            return i & -i;
        }

        public void add(int i, int val) {
            int v = val;
            if (diff != null) {
                v = val - this.diff[i];
                this.diff[i] = v;
            }
            i++;
            while (i <= n) {
                tree[i] += v;
                i += lowbit(i);
            }
        }

        private int sums(int i) {
            int s = 0;
            i++;
            while (i > 0) {
                s += tree[i];
                i -= lowbit(i);
            }
            return s;
        }

        public int sums(int l, int r) {
            if (l < 0 || r > n || l > r) {
                return 0;
            }
            return sums(r) - sums(l - 1);
        }
    }

}
