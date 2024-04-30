package leetcode.ox3if.graph.topological_sorting.Solution_0003;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * 210. 课程表 II
 *
 * 现在你总共有 numCourses 门课需要选，记为0到numCourses - 1。
 * 给你一个数组prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修bi 。
 * 	例如，想要学习课程 0 ，你需要先完成课程1 ，我们用一个匹配来表示：[0,1] 。
 * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
 *
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：[0,1]
 * 解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 *
 * 示例 2：
 * 输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * 输出：[0,2,1,3]
 * 解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 * 因此，一个正确的课程顺序是[0,1,2,3] 。另一个正确的排序是[0,2,1,3] 。
 *
 * 示例 3：
 * 输入：numCourses = 1, prerequisites = []
 * 输出：[0]
 *
 * 提示：
 * 	1 <= numCourses <= 2000
 * 	0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * 	prerequisites[i].length == 2
 * 	0 <= ai, bi < numCourses
 * 	ai != bi
 * 	所有[ai, bi] 互不相同
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/course-schedule-ii
 * @title: course-schedule-ii
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"findOrder","in.txt",false,false);
        // IoUtil.testUtil(Solution2.class,"findOrder","in.txt",false,false);
    }
     

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        int tot = 0;
        int[] in = new int[numCourses];
        ArrayList<Integer>[] g = new ArrayList[numCourses];
        for(int i = 0; i < numCourses ;i++ ) {
            g[i] = new ArrayList<Integer>();
        }
        for(int[] e : prerequisites) {
            g[e[1]].add(e[0]);
            in[e[0]]++;
        }
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if(in[i] == 0) {
                q.add(i);
            }
        }
        int[] ans = new int[numCourses];
        int j = 0;
        while(!q.isEmpty()) {
            int i = q.poll();
            ans[j] = i;
            j++;
            tot++;
            for(int next : g[i]) {
                --in[next];
                if(in[next]==0) {
                    q.add(next);
                }
            }
        }
        return tot == numCourses ? ans : new int[]{};
	}


    /**
     * 链式前向星解
     */
    public static class Solution2 {
        private static final int[] EMPTY_ARR = new int[] {};

        public static int MAX = 10000;

        public static int[] head = new int[MAX];

        public static int[] next = new int[MAX];

        public static int[] to = new int[MAX];

        public static int[] queue = new int[MAX];

        public static int l, r = 0;

        public static int[] in = new int[MAX];

        public static int cnt = 0;

        public static void clear(int N) {
            r = l = cnt = 0;
            Arrays.fill(head, 0, N, -1);
            Arrays.fill(in, 0, N, 0);
        }

        public static void addEdge(int fa, int t) {
            next[cnt] = head[fa];
            to[cnt] = t;
            head[fa] = cnt;
            cnt++;
        }

        public int[] findOrder(int numCourses, int[][] edges) {
            clear(numCourses);
            int c = 0;

            for (int[] edge : edges) {
                addEdge(edge[1], edge[0]);
                in[edge[0]]++;
            }

            for (int i = 0; i < numCourses; i++) {
                if (in[i] == 0) {
                    queue[r] = i;
                    r++;
                }
            }

            int[] ans = new int[numCourses];
            int j = 0;
            while (r > l) {
                int i = queue[l];
                ans[j++] = i;
                c++;
                l++;
                for (int ei = head[i]; ei != -1; ei = next[ei]) {
                    in[to[ei]]--;
                    if (in[to[ei]] == 0) {
                        queue[r] = to[ei];
                        r++;
                    }
                }
            }
            return c == numCourses ? ans : EMPTY_ARR;
        }

    }

  

}