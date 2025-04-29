package leetcode.contest.biweekly.bi_100.bi_155;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/biweekly-contest-155/problems/unit-conversion-i">单位转换 I</a>
 * @title: 单位转换 I
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class,"baseUnitConversions","B.txt");
    }
     


    private static int MOD = (int)1e9 + 7;


    public int[] baseUnitConversions(int[][] conversions) {
        int n = conversions.length+1;
        clear(n);
        for (int[] e : conversions) {
            int u = e[1],v = e[0],w = e[2];
            addEdge(u,v,w);
        }
        int[] dp = new int[n];
        int[] ans = new int[n];
        for(int i = 0;i < n;i++) {
            ans[i]=dfs(i,dp);
        }
        return ans;
    }

    public int  dfs(int u,int[] dp){
        if(dp[u]>0)return dp[u];
        long ans = 1;
        for (int e = head[u]; e >= 0; e = edges[e].nxt) {
            int v = edges[e].to,w = edges[e].w;
            if(v==u)continue;
            ans = ans * dfs(v,dp) * w;
            ans %= MOD;
        }
        return dp[u]=(int)ans;
    }



    // =====================================链式前向星建图开始

    private static final int MAXN = (int) 1e5 + 1,NOT_EXIST_FLAG = -1;
    private static final int EDGE_MAXN = (int) 1e5 + 10;



    private static int[] in = new int[MAXN], q = new int[MAXN], head = new int[MAXN], empty = new int[]{};
    private static int cnt, l, r;
    private static Edge[] edges = new Edge[EDGE_MAXN];

    private static void clear(int n) {
        for (int i = 0; i <= n; i++) {
            in[i] = 0;
            head[i] = NOT_EXIST_FLAG;
        }
        cnt = 0;
        l = r = 0;
    }

    private static void addEdge(int u, int v, int w) {
        if (edges[cnt] == null) {
            edges[cnt] = new Edge(head[u], v, w);
        } else {
            edges[cnt].update(head[u], v, w);
        }
        head[u] = cnt++;
    }

    private static class Edge {
        int w, nxt, to;

        Edge() {
        }

        Edge(int next, int to, int w) {
            this.update(next, to, w);
        }

        void update(int nxt, int to, int w) {
            this.nxt = nxt;
            this.to = to;
            this.w = w;
        }
    }

    // =====================================链式前向星建图结束

}