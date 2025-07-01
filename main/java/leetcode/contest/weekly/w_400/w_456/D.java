package leetcode.contest.weekly.w_400.w_456;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/weekly-contest-456/problems/maximize-spanning-tree-stability-with-upgrades">升级后最大生成树稳定性</a>
 * @title: 升级后最大生成树稳定性
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "maxStability", "D.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public int maxStability(int n, int[][] edges, int k) {
        Union oneUnion = new Union(n);
        Union allUnion = new Union(n);
        int minSel = 0x3ffffff;
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            if (e[3] == 1) {
                // 必选构成了环
                if (oneUnion.isSame(u, v)) return -1;
                oneUnion.union(u, v);
                minSel = Math.min(minSel, e[2]);
            }
            // 全部选择
            allUnion.union(u, v);
        }

        // 全部选择都不能构成一个集合
        if (allUnion.size > 1) return -1;

        // 全部都是必选边
        if (oneUnion.size == 1) return minSel;

        // 按照最大边权排序
        Arrays.sort(edges, (e0, e1) -> e1[2] - e0[2]);

        List<Integer> a = new ArrayList<>();
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2], flag = edge[3];
            // 必须是可以增强 同时和合并成功的
            if (flag == 0 && !oneUnion.isSame(u, v)) {
                a.add(w);
                oneUnion.union(u, v);
            }
        }

        int m = a.size();
        int ans = minSel;
        if (m > 0) {
            ans = Math.min(ans, a.get(m - 1) * 2);
            if (k < m) {
                // 说明这部分是可选 按照题意不升级
                ans = Math.min(ans, a.get(m - 1 - k));
            }
        }
        return ans;
    }


    static class Union {
        int[] fa;

        int size = 0;

        public Union(int n) {
            fa = new int[n];
            for (int i = 0; i < n; i++) {
                fa[i] = i;
            }
            size = n;
        }

        public boolean isSame(int x, int y) {
            return find(x) == find(y);
        }

        public boolean union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx == fy) {
                return false;
            } else {
                size--;
                fa[fy] = fx;
                return true;
            }
        }

        public int find(int i) {
            while (i != fa[i]) {
                fa[i] = fa[fa[i]];
                i = fa[i];
            }
            return fa[i];
        }


    }


}