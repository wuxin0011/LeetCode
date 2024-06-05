package template.union;

/**
 * @author: wuxin0011
 * @Description: 并查集
 */
public class DefaultUnion {

    public static void main(String[] args) {

    }

    // 并查集基础模板
    // 参考题目
    static class Union {
        int MAXN ;
        int[] fa;

        int size = 0;

        public Union(int MAXN) {
            this.MAXN = MAXN;
            this.fa = new int[MAXN];
        }

        public void init(int N) {
            for (int i = 0; i < N; i++) {
                fa[i] = i;
            }
            size = N;
        }

        public boolean isSame(int x, int y) {
            return find(x) == find(y);
        }

        public boolean union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx == fy) {
                return false;
            } else {
                size--;
                fa[fy] = fx;
                return true;
            }
        }

        public int find(int i) {
            while (i != fa[i]) {
                fa[i] = fa[fa[i]];
                i = fa[i];
            }
            return fa[i];
        }


    }
}
