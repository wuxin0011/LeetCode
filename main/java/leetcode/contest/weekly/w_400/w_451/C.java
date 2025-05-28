package leetcode.contest.weekly.w_400.w_451;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/weekly-contest-451/problems/maximum-profit-from-trading-stocks-with-discounts">折扣价交易股票的最大利润</a>
 * @title: 折扣价交易股票的最大利润
 */
//@TestCaseGroup(start = 6,end = 0x3fff,use = true)
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "maxProfit", "C.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
        if (n == 0) return 0;
        if (n == 1) {
            return Math.max(0, present[0] <= budget ? future[0] - present[0] : 0);
        }
        clear(n);
        this.present = present;
        this.future = future;
        this.hierarchy = hierarchy;
        int[] in = new int[n + 10];
        for (int i = 0; i < hierarchy.length; i++) {
            int u = hierarchy[i][0], v = hierarchy[i][1];
            u--;
            v--;
            in[v]++;
            addEdge(u, v, 0);
        }
        int ans = 0;
         ins = new HashSet<>();

        for (int i = 0; i < hierarchy.length; i++) {
            int u = hierarchy[i][0], v = hierarchy[i][1];
            u--;
            if (in[u] == 0) {
                ins.add(u);
            }

        }
        if(ins.size()==0) return 0;
        System.out.println(outList);
        for (Integer u : ins) {
//            ans=Math.max(ans,dfs(u,0,0,budget));
        }
        return ans;
    }

    int[] present, future;
    int[][] hierarchy;
    Set<Integer> ins;

    List<Integer> outList = new ArrayList<>();
    boolean[] vis;

    int inf = Integer.MAX_VALUE >> 1;


    public int dfs(int u, int f, int fatherBuy, int use) {
        if (use < 0) return -inf;
        if (vis[u]) return 0;
        vis[u] = true;
        if (use <= 0) return 0;
        int pre = fatherBuy == 1 ? present[u] / 2 : present[u];
        int cost = use - pre >= 0 ? future[u] - pre : 0;
        int ans = 0;
        boolean findNxt = false;
        for (int e = head[u]; e >= 0; e = nxt[e]) {
            int v = to[e];
            if (v != f) {
                ans = Math.max(ans, dfs(v, u, 0, use));
                ans = Math.max(ans, dfs(v, u, 1, use - pre) + cost);
                findNxt = true;
            }
        }
        for (int outu : ins) {
            if (vis[outu]) continue;
            if (!findNxt && use - pre >= 0) {
                ans = Math.max(ans, dfs(outu, 0, 0, use - pre) + cost);
            }
        }
        vis[u] = false;
        return Math.max(cost, ans);
    }

    private static int N = 300, NOT_EXIST_FLAG = -1;
    private static int M = N << 1; // 无向图
    private static int head[] = new int[N], nxt[] = new int[M], to[] = new int[M], weight[] = new int[M], cnt;


    public static void clear(int n) {
        for (int i = 0; i <= n + 10; i++) {
            head[i] = NOT_EXIST_FLAG;
        }
        cnt = 0;
    }

    public static void addEdge(int u, int v, int w) {
        nxt[cnt] = head[u];
        to[cnt] = v;
        weight[cnt] = w;
        head[u] = cnt++;
    }


}