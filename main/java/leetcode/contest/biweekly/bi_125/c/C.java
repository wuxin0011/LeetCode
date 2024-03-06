package leetcode.contest.biweekly.bi_125.c;

import leetcode.utils.IoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description: 图 BFS
 * @title 在带权树网络中统计可连接服务器对数目
 * @url https://leetcode.cn/problems/count-pairs-of-connectable-servers-in-a-weighted-tree-network/
 */
public class C {
    public static void main(String[] args) {
        IoUtil.testUtil(C.class,"countPairsOfConnectableServers","in.txt");
    }

    public int[] countPairsOfConnectableServers(int[][] edges, int singal) {
        int n = edges.length + 1;
        int[] ans = new int[n];
        List<int[]>[] g = new ArrayList[n];
        for(int i = 0;i<n;i++) {
            g[i] = new ArrayList<>();
        }
        for(int[] e : edges) {
            int from = e[0];
            int to = e[1];
            int w = e[2];
            g[from].add(new int[] {to,w});
            g[to].add(new int[] {from,w});
        }
        for(int i = 0;i<n;i++) {
            List<int[]> esList = g[i];
            int tot = 0;
            for(int[] e : esList) {
                int cnt = dfs(e[0],i,e[1],g,singal);
                ans[i] += cnt * tot;
                tot += cnt;
            }
        }
        return ans;
    }

    public static int dfs(int in ,int f,int w,List<int[]>[] g,int signal) {
        int tot = w % signal == 0 ? 1 : 0;
        for(int[] e : g[in]) {
            int to = e[0];
            if( to != f ) {
                tot += dfs(to,in,w + e[1],g,signal);
            }
        }
        return tot;
    }
}
