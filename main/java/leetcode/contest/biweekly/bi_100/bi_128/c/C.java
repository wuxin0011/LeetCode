package leetcode.contest.biweekly.bi_100.bi_128.c;

import code_generation.utils.IoUtil;

import java.util.ArrayList;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/biweekly-contest-128/problems/minimum-time-to-visit-disappearing-nodes
 * @title: 访问消失节点的最少时间
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "minimumTime", "C.txt");
    }

    ArrayList[] g;
    boolean[] vis;

    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        int[] ans = new int[n];
        vis = new boolean[n];
        this.g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<int[]>();
        }
        for (int[] edge : edges) {
            g[edge[0]].add(new int[]{edge[1], edge[2]});
            g[edge[1]].add(new int[]{edge[0], edge[2]});
        }
        for (int i = 1; i < n; i++) {
            ArrayList<int[]> e = g[0];
            int w = Integer.MAX_VALUE;
            for (int[] edge : e) {
                w = Math.min(w,dfs(edge[0],0,i,edge[1]));
            }
            ans[i] = w >= disappear[i] ? -1 : Math.min(disappear[i],w);
            vis[i] = ans[i] == -1;
        }
        return ans;
    }

    public int dfs(int x, int fa, int t,int w) {
        if(x==t){
            return w;
        }
        ArrayList<int[]> edges = g[x];
        for (int[] edge : edges) {
            if (edge[0] != fa) {
                if(vis[edge[0]]){
                    return Integer.MAX_VALUE;
                }
                w += edge[1];
                dfs(edge[0], x, t,w);
            }
        }
        return w;
    }


}