package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph
 * @title: all-ancestors-of-a-node-in-a-directed-acyclic-graph
 */
public class Code_0034_2192 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0034_2192.class, "getAncestors", "txt_file\\Code_0034_2192.txt");
    }


    List<List<Integer>> ans;
    List[] g;
    boolean[] vis;

    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        this.ans = new ArrayList<>();
        this.g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<Integer>();
        for (int[] e : edges) {
            g[e[1]].add(e[0]); // 逆向构建边的指向
        }
        for (int i = 0; i < n; i++) {
            vis = new boolean[n];
            dfs(i);
            List<Integer> res = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (vis[j] && i != j) {
                   res.add(j);
                }
            }
            ans.add(res);
        }
        return ans;
    }


    public void dfs(int x) {
        vis[x] = true;
        List<Integer> edges = g[x];
        for (int cur : edges) {
            if (!vis[cur]) {
                dfs(cur);
            }
        }
    }

}