package leetcode.ox3if.graph.topological_sorting.Solution_0002;

import code_generation.utils.IoUtil;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/course-schedule
 * @title: 课程表
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "canFinish", "in.txt");
        IoUtil.testUtil(Solution.class, "canFinish2", "in.txt");
    }


    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int tot = 0;
        int[] in = new int[numCourses];
        ArrayList<Integer>[] g = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            g[i] = new ArrayList<Integer>();
        }
        for (int[] e : prerequisites) {
            g[e[1]].add(e[0]);
            in[e[0]]++;
        }
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (in[i] == 0) {
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            int i = q.poll();
            tot++;
            for (int next : g[i]) {
                --in[next];
                if (in[next] == 0) {
                    q.add(next);
                }
            }
        }
        return tot == numCourses;
    }


    /**
     * 链式前向星方式解决
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        clear(numCourses);
        int c = 0;

        for (int[] edge : prerequisites) {
            addEdge(edge[1], edge[0]);
            in[edge[0]]++;
        }

        for (int i = 0; i < numCourses; i++) {
            if (in[i] == 0) {
                queue[r] = i;
                r++;
            }
        }

        while (r > l) {
            int i = queue[l];
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
        return c == numCourses;
    }

    public static int MAX = 10000;
    public static int N = 2001;
    public static int[] head = new int[N];

    public static int[] next = new int[MAX];

    public static int[] to = new int[MAX];

    public static int[] queue = new int[MAX];

    public static int l, r = 0;

    public static int[] in = new int[MAX];

    public static int cnt = 0;

    public static void clear(int n) {
        r = l = cnt = 0;
        Arrays.fill(head, 0, n, -1);
        Arrays.fill(in, 0, n, 0);
    }

    public static void addEdge(int fa, int t) {
        next[cnt] = head[fa];
        to[cnt] = t;
        head[fa] = cnt;
        cnt++;
    }


}