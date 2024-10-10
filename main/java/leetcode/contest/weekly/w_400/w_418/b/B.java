package leetcode.contest.weekly.w_400.w_418.b;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-418/problems/remove-methods-from-project
 * @title: 移除可疑的方法
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "remainingMethods", "B.txt");
    }


    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {

        g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<Integer>());
        for (int[] e : invocations) {
            int a = e[0], b = e[1];
            g[a].add(b);
        }
        boolean[] vis = new boolean[n];
        dfs(k, -1, vis);
        boolean del = true;
        for (int[] e : invocations) {
            int a = e[0], b = e[1];
            // a->b
            if (vis[b] && !vis[a]) {
                del = false;
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (vis[i] && del) {
                continue;
            }
            ans.add(i);
        }
        return ans;
    }


    ArrayList[] g;
    List<Integer> ans = new ArrayList<>();


    void dfs(int x, int fa, boolean[] v) {
        v[x] = true;
        ArrayList<Integer> ls = g[x];
        for (int y : ls) {
            if (!v[y] && y != fa) {
                dfs(y, x, v);
            }
        }
    }


}