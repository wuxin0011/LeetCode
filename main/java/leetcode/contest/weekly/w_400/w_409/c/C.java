package leetcode.contest.weekly.w_400.w_409.c;

import code_generation.utils.IoUtil;

import java.util.TreeSet;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-409/problems/shortest-distance-after-road-addition-queries-ii
 * @title: 新增道路查询后的最短距离 II
 */
public class C {

    static class S1 {
        public static void main(String[] args) {
            IoUtil.testUtil(S1.class, "shortestDistanceAfterQueries", "C.txt");
        }


        // 本题注意不存在交叉
        // 区间并查集解法
        public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
            int size = n - 1;
            Union u = new Union(size);
            int m = queries.length;
            int[] ans = new int[m];
            for (int i = 0; i < m; i++) {
                int l = queries[i][0], r = queries[i][1];
                int pos = u.find(l);
                while (pos < r - 1) {
                    u.union(pos, r - 1);
                    pos = u.find(pos + 1);
                }
                ans[i] = u.size;
            }
            return ans;
        }

        static class Union {
            int[] fa;
            int size;

            Union(int size) {
                this.size = size;
                fa = new int[size];
                for (int i = 0; i < size; i++) {
                    fa[i] = i;
                }
            }

            int find(int x) {
                while (fa[x] != x) {
                    fa[x] = fa[fa[x]];
                    x = fa[x];
                }
                return x;
            }

            void union(int x, int y) {
                x = find(x);
                y = find(y);
                if (x != y) {
                    size--;
                    fa[x] = y;
                }
            }
        }

    }


 

}