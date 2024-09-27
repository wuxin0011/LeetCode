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
    // https://leetcode.cn/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph
    public static class Union {
        int[] fa;
        int[] s;
        int size;
        public Union(int n) {
            fa = new int[n];
            s = new int[n];
            for(int i = 0; i < n;i++) {
                fa[i] = i;
                s[i] = 1;
            }
            size = n;
        }

        public int find(int i) {
            while(i != fa[i]) {
                fa[i] = fa[fa[i]];
                i = fa[i];
            }
            return i;
        }


        public boolean isSame(int x,int y) {
            return find(x) == find(y);
        }


        // 基于集合大小的
        public boolean union(int x,int y) {
            x = find(x);
            y = find(y);
            if(x == y) {
                return false;
            }else {
                size--;
                int sx = s[x];
                int sy = s[y];
                if (sx < sy) {
                    int temp = y;
                    y = x;
                    x = temp;
                }
                fa[y] = x;
                s[x] += s[y];
                s[y] = 0;
                return true;
            }
        }



        /**

        // 数字大小小挂大
        public boolean union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x == y) {
                return false;
            } else {
                size--;
                if (y < x) {
                    int temp = y;
                    y = x;
                    x = temp;
                }
                fa[y] = x;
                s[x] += s[y];
                s[y] = 0;
                return true;
            }
        }
         */

    }
}
