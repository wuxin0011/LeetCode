package template.Fenwick;

/**
 * @author: wuxin0011
 * @Description: 树状数组基础版本 单点修改 范围查询
 */
public class Fenwick1 {

    public static class Fenwick {
        int n;
        int[] tree;

        Fenwick(int n) {
            this.n = n;
            this.tree = new int[n];
        }

        static int lowbit(int i) {
            return i & -i;
        }

        void add(int i, int val) {
            i += 1;
            while (i < n) {
                tree[i] += val;
                i += lowbit(i);
            }
        }

        private int _sum(int i) {
            int s = 0;
            while (i > 0) {
                s += tree[i];
                i -= lowbit(i);
            }
            return s;
        }

        int query(int l, int r) {
            if (l < 0 || r > n || l >= r) {
                return 0;
            }
            return _sum(r) - _sum(l-1);
        }
    }

}
