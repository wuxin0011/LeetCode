package leetcode.contest.weekly.w_400.w_471;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/weekly-contest-471/problems/sum-of-perfect-square-ancestors">完全平方数的祖先个数总和</a>
 * @title: 完全平方数的祖先个数总和
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class D {


    static class t0 {


        public static void main(String[] args) {
            IoUtil.testUtil(t0.class, "sumOfAncestors", "D.txt");
        }

        private static final int MOD = (int) 1e9 + 7;
        // https://leetcode.cn/problems/sum-of-perfect-square-ancestors/submissions/670281925/

        // 链式前向星建图
        static int N = (int) 1e5 + 10;
        static int head[] = new int[N], nxt[] = new int[N<<1], to[] = new int[N<<1], cnt;


        void clear(int n) {
            Arrays.fill(head, 0, n + 1, 0);
            cnt = 0;
        }

        void addEdge(int u, int v) {
            ++cnt;
            nxt[cnt] = head[u];
            to[cnt] = v;
            head[u] = cnt;
        }
        // 链式前向星建图结束

        public long sumOfAncestors(int n, int[][] edges, int[] nums) {
            Arrays.fill(map, 0);
            clear(n);
            a = nums;
            ans = 0;
            for (int[] e : edges) {
                int u = e[0], v = e[1];
                addEdge(u, v);
                addEdge(v, u);
            }
            dfs(0, -1);
            return ans;
        }

        static int[] a;
        static long ans;

        public static void dfs(int u, int fa) {
            int c = core[a[u]];
            ans += map[c];
            map[c]++;
            for (int e = head[u]; e > 0; e = nxt[e]) {
                int v = to[e];
                if (v != fa) {
                    dfs(v, u);
                }
            }
            map[c]--;
        }


        /*预处理平方核模板*/
        private static final int MX = 100_001;
        private static final int[] map = new int[MX];
        private static final int[] core = new int[MX];

        static {
            // 预处理平方剩余核
            for (int i = 1; i < MX; i++) {
                if (core[i] == 0) {
                    for (int j = 1; i * j * j < MX; j++) {
                        core[i * j * j] = i;
                    }
                }
            }
        }
        /*预处理平方核模板结束*/
    }


    static class t1 {
        public static void main(String[] args) {
            IoUtil.testUtil(t1.class, "sumOfAncestors", "D.txt");
        }

        private static final int MOD = (int) 1e9 + 7;

        public long sumOfAncestors(int n, int[][] edges, int[] nums) {
            Arrays.fill(map, 0);
            g = new List[n + 1];
            a = nums;
            ans = 0;
            Arrays.setAll(g, xxxx -> new ArrayList<Integer>());
            for (int[] e : edges) {
                g[e[0]].add(e[1]);
                g[e[1]].add(e[0]);
            }
            dfs(0, -1);
            return ans;
        }

        static List<Integer>[] g;
        static int[] a;
        static long ans;

        public static void dfs(int u, int fa) {
            int c = core[a[u]];
            ans += map[c];
            map[c]++;
            for (int v : g[u]) {
                if (v != fa) {
                    dfs(v, u);
                }
            }
            map[c]--;
        }


        /*预处理平方核模板*/
        private static final int MX = 100_001;
        private static final int[] map = new int[MX];
        private static final int[] core = new int[MX];

        static {
            // 预处理平方剩余核
            for (int i = 1; i < MX; i++) {
                if (core[i] == 0) {
                    for (int j = 1; i * j * j < MX; j++) {
                        core[i * j * j] = i;
                    }
                }
            }
        }
        /*预处理平方核模板结束*/
    }


}