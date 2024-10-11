package template.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description: 建图
 */
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
}
