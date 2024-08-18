package leetcode.contest.weekly.w_400.w_410.b;

import code_generation.utils.IoUtil;

import java.util.ArrayList;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-410/problems/count-the-number-of-good-nodes
 * @title: 统计好节点的数目
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "countGoodNodes", "B.txt");
    }


    int ans = 0;
    ArrayList<Integer>[] g;

    public int countGoodNodes(int[][] edges) {
        int n = edges.length + 1;
        g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<Integer>();
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            g[u].add(v);
            // g[v].add(u);
        }
        dfs(0, -1);
        return ans;
    }


    public int dfs(int son, int fa) {
        int size = 1;
        int pre = 0;
        boolean ok = true;
        for (int x : g[son]) {
            // if (x == fa) continue;
            int s = dfs(x, son);
            if (pre > 0 && s != pre) {
                ok = false;
            }
            pre = s;
            size += s;
        }

        if (ok) {
            ans++;
        }
        return size;
    }


}