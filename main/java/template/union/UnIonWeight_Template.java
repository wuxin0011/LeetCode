package template.union;


// 参考题目
// 带权并查集模板题 https://www.luogu.com.cn/problem/P8779 提交记录 https://www.luogu.com.cn/record/214594471

/**
 * @author: wuxin0011
 * @Description: 带权并查集
 */
public class UnIonWeight_Template {


    static class DSU {
        int fa[];
        long dis[];
        DSU(int n) {
            fa = new int[n + 1];
            dis = new long[n + 1];
            init(dis.length);
        }

        void init(int n) {
            for(int i = 0;i < n;i++) {
                fa[i] = i;
                dis[i] = 0;
            }
        }

        int find(int x){
            if(x == fa[x]) return x;
            int p = find(fa[x]);
            dis[x] += dis[fa[x]];
            fa[x] = p;
            return p;
        }

        boolean union(int l,int r,long val) {
            int lf = find(l);
            int rf = find(r);
            if(lf == rf) return false;
            fa[lf] = rf;
            dis[lf] = dis[r] - dis[l] + val;
            return true;
        }

        boolean isSame(int l,int r){
            return find(l) == find(r);
        }

        // 查询 两个集合距离 Long.MAX_VALUE 表示不存在
        long query(int l,int r) {
            return isSame(l,r) ? dis[l] - dis[r] : Long.MAX_VALUE;
        }
    }





}
