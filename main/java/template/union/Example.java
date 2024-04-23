package template.union;

/**
 * @author: wuxin0011
 * @Description: 并查集
 */
public class Example {

    public static void main(String[] args) {

    }

    // 并查集基础模板
    // 参考题目
    public static class Union {
        public int size;
        public int[] fa;
        public Union(int size) {
            this.size = size;
            fa = new int[size];
            build();
        }

        public void build() {
            for (int i = 0; i < size; i++) {
                fa[i] = i;
            }
        }

        public int find(int i) {
            if (i != fa[i]) {
                fa[i] = find(fa[i]);
            }
            return fa[i];
        }

        public boolean isSame(int x, int y) {
            return find(x) == find(y);
        }

        public void union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx != fy) {
                fa[fy] = fx;
                size--;
            }
        }
    }
}
