package template.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description: 建图
 */
@SuppressWarnings("all")
public class BuildEdge {

    // 测试链接 https://leetcode.cn/problems/second-minimum-time-to-reach-destination/submissions/571668490/
    // 参数说明
    // uIdx 为 u 的索引
    // vIdx 为 v 的索引
    // wIdx 为 w 的索引
    // cnt[] 为 统计点i有多少边
    // es[][] 为建图参数
    // n 为建图参数表示有多少个点
    // g2 表示建图后的 i 中有哪些(v)
    // g3 表示建图后的 i 中有哪些(v,w)
    // in[] 为入读，out[]为出度
    // hasDirect 是否是有向图
    // hasWeight 是否有权重
    public static class Graph {
        int uIdx = 0, vIdx = 1, wIdx = 2, cnt[], es[][], n = 0, g2[][], g3[][][], in[], out[];
        boolean hasDirect = true, hasWeight = false;


        Graph(int n, List<List<Integer>> eList) {
            if(eList == null) {
                throw new RuntimeException("List is null");
            }
            int[][] es = new int[eList.size()][];
            for (int i = 0; i < eList.size(); i++) {
                es[i] = new int[eList.get(i).size()];
                for (int j = 0; j < eList.get(i).size(); j++) {
                    es[i][j] = eList.get(i).get(j);
                }
            }
            this.init(n, es);
        }

        Graph(int n, int[][] es) {
            this.init(n, es);
        }

        void init(int n, int[][] es) {
            this.n = n;
            this.es = es;
            // this.in = new int[n];
            // this.out = new int[n];
            this.build();
        }


        void addEdge2(int u, int v) {
            cnt[u]--;
            g2[u][cnt[u]] = v;
        }

        void addEdge3(int u, int v, int w) {
            cnt[u]--;
            g3[u][cnt[u]][0] = v;
            g3[u][cnt[u]][1] = w;
        }

        void build() {
            if (es == null) {
                throw new RuntimeException("edges is null");
            }
            this.cnt = new int[n];

            for (int[] e : es) {
                int u = e[uIdx], v = e[vIdx];
                cnt[u]++;
                if (in != null) in[v]++;
                if (out != null) out[u]++;
                if (!hasDirect) {
                    cnt[v]++;
                    if (in != null) in[u]++;
                    if (out != null) out[v]++;
                }
            }
            if (hasWeight) {
                this.g3 = new int[n][][];
            } else {
                this.g2 = new int[n][];
            }
            for (int i = 0; i < n; i++) {
                if (hasWeight) {
                    this.g3[i] = new int[cnt[i]][2];
                } else {
                    this.g2[i] = new int[cnt[i]];
                }
            }
            for (int[] e : es) {
                int u = e[uIdx], v = e[vIdx];
                if (hasWeight) {
                    int w = e[wIdx];
                    addEdge3(u, v, w);
                    if (!hasDirect) {
                        addEdge3(v, u, w);
                    }
                } else {
                    addEdge2(u, v);
                    if (!hasDirect) {
                        addEdge2(v, u);
                    }
                }
            }


        }


    }


    public static void buildEdgeList2(int n,int[][] edges) {

        // 建立无向图 没有权值
//        List<Integer>[] g = new List[n];
//        Arrays.setAll(g,i->new ArrayList<>());
//        for(int[] e : edges) {
//            int u = e[0],v = e[1];
//            g[u].add(v);
//            g[v].add(u);
//        }

        // 建立有向图 有权值
//        List<int[]>[] g = new List[n];
//        Arrays.setAll(g,i->new ArrayList<>());
//        for(int[] e : edges) {
//            int u = e[0],v = e[1],w = e[2];
//            g[u].add(new int[]{v,w});
//            g[v].add(new int[]{u,w});
//        }
    }


    public static void buildEdgeList3(int n,List<List<Integer>> edges) {

        // 建立无向图 没有权值
//        List<Integer>[] g = new List[n];
//        Arrays.setAll(g, i->new ArrayList<>());
//        for(List<Integer> e : edges) {
//            int u = e.get(0),v = e.get(1);
//            g[u].add(v);
//            g[v].add(u);
//        }

        // 建立有向图 有权值
//        List<int[]>[] g = new List[n];
//        Arrays.setAll(g, i->new ArrayList<>());
//        for(List<Integer> e : edges) {
//            int u = e.get(0),v = e.get(1),w = e.get(2);
//            g[u].add(new int[]{v,w});
//            g[v].add(new int[]{v,w});
//        }
    }


    /**
     * 离散化建图
     */

    static class Lca_3 {


        public static void example(int[][] edges, int[][] q) {
            int[] a = rnq(edges);
            LCA_ST_Template.Lca_2.LCA g = new LCA_ST_Template.Lca_2.LCA(100, 10000);
            for (int[] edge : edges) {
                int u = lower_bound(a, a.length, edge[0]), v = lower_bound(a, a.length, edge[1]), w = 0;
                g.addEdge(u, v, w);
                g.addEdge(v, u, w);
            }

            for (int i = 0; i < q.length; i++) {
                int u = lower_bound(a, a.length, q[i][0]), v = lower_bound(a, a.length, q[i][1]);
                System.out.printf("lca {%d %d} = %d\n", q[i][0], q[i][1], a[g.lca(u, v)]);
            }

        }


        public static int[] rnq(int[][] edges) {
            int n = edges.length + 1;
            List<Integer> lt = new ArrayList<>();
            for (int[] edge : edges) {
                lt.add(edge[0]);
                lt.add(edge[1]);
            }

            int[] a = new int[lt.size()];
            for (int i = 0; i < lt.size(); i++) {
                a[i] = lt.get(i);
            }
            Arrays.sort(a);
            int size = 1;
            for (int i = 0; i < a.length; i++) {
                if (a[i] != a[size - 1]) {
                    a[size++] = a[i];
                }
            }
            return Arrays.copyOf(a, size);
        }

        public static int lower_bound(int[] a, int size, int x) {
            int l = 0, r = size - 1;
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                if (a[mid] >= x) r = mid - 1;
                else l = mid + 1;
            }
            return l;
        }

    }


}
