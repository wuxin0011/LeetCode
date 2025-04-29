package leetcode.contest.weekly.w_400.w_447;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/weekly-contest-447/problems/path-existence-queries-in-a-graph-i">针对图的路径存在性查询 I</a>
 * @title: 针对图的路径存在性查询 I
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "pathExistenceQueries", "B.txt");
    }


    public static class Union {
        int[] fa;
        int[] s;
        int size;

        public Union(int n) {
            fa = new int[n];
            s = new int[n];
            for (int i = 0; i < n; i++) {
                fa[i] = i;
                s[i] = 1;
            }
            size = n;
        }

        public int find(int i) {
            while (i != fa[i]) {
                fa[i] = fa[fa[i]];
                i = fa[i];
            }
            return i;
        }


        public boolean isSame(int x, int y) {
            return find(x) == find(y);
        }


        public boolean union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x == y) {
                return false;
            } else {
                size--;
                int sx = s[x];
                int sy = s[y];
                if (sx < sy) {
                    int temp = y;
                    y = x;
                    x = temp;
                }
                fa[y] = x;
                s[x] += s[y];
                s[y] = 0;
                return true;
            }
        }
    }

    public boolean[] pathExistenceQueries(int n, int[] a, int maxDiff, int[][] queries) {
        Union uf = new Union(n + 10);
        for (int i = 0, l = 0, r = 0; i < a.length; i++) {
            while (r < a.length && a[r] - a[i] <= maxDiff) {
                uf.union(i, r);
                r++;
            }
        }
        boolean[] ans = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0], v = queries[i][1];
            ans[i] = uf.isSame(u, v);
        }
        return ans;
    }


}