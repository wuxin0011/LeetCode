package template.graph;


import java.util.Arrays;

/**
 * @see leetcode._0x3f_.graph.topological_sorting.Solution_0001.Solution
 * @see leetcode._0x3f_.graph.topological_sorting.Solution_0002.Solution
 * @see leetcode._0x3f_.graph.topological_sorting.Solution_0003.Solution
 * @author: wuxin0011
 * @Description: 链式向前星建图
 */
public class LinkStartBuildGraph {

        // 模板
        // https://leetcode.cn/problems/course-schedule-ii/submissions/571351678/
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            clear(numCourses);

            for (int[] e : prerequisites) {
                int u = e[1], v = e[0], w = 0;
                addEdge(u, v, w);
                in[v]++;
            }
            for (int i = 0; i < numCourses; i++) {
                if (in[i] == 0) {
                    q[r++] = i;
                }
            }
            int[] ans = new int[numCourses];
            int i = 0;
            while (l < r) {
                int x = q[l++];
                ans[i++] = x;
                numCourses--;
                for (int e = head[x]; e >= 0; e = edges[e].next) {
                    int v = edges[e].to;
                    in[v]--;
                    if (in[v] == 0) {
                        q[r++] = v;
                    }
                }
            }
            return numCourses == 0 ? ans : empty;
        }


        static int[] in = new int[2001], q = new int[2001], head = new int[2001], empty = new int[]{};
        static int cnt, l, r;
        static Edge[] edges = new Edge[5001];

        static void clear(int n) {
            for (int i = 0; i <= n; i++) {
                in[i] = 0;
                head[i] = -1;
            }
            cnt = 0;
            l = r = 0;
        }

        static void addEdge(int u, int v, int w) {
            if (edges[cnt] == null) {
                edges[cnt] = new Edge(head[u], v, w);
            } else {
                edges[cnt].update(head[u], v, w);
            }
            head[u] = cnt++;
        }

        static class Edge {
            int w, next, to;

            Edge() {
            }

            Edge(int next, int to, int w) {
                this.update(next, to, w);
            }

            void update(int next, int to, int w) {
                this.next = next;
                this.to = to;
                this.w = w;
            }
        }
}
