package leetcode.ox3if.graph.topological_sorting.Solution_0004;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1462. 课程表 IV
 * <p>
 * 你总共需要上numCourses门课，课程编号依次为 0到numCourses-1。
 * 你会得到一个数组prerequisite ，其中prerequisites[i] = [ai, bi]表示如果你想选bi 课程，你 必须 先选ai课程。
 * 有的课会有直接的先修课程，比如如果想上课程 1，你必须先上课程 0，那么会以 [0,1]数对的形式给出先修课程数对。
 * 先决条件也可以是 间接 的。如果课程 a 是课程 b 的先决条件，课程 b 是课程 c 的先决条件，那么课程 a 就是课程 c 的先决条件。
 * 你也得到一个数组queries，其中queries[j] = [uj, vj]。对于第 j 个查询，您应该回答课程uj是否是课程vj的先决条件。
 * 返回一个布尔数组 answer ，其中 answer[j] 是第 j 个查询的答案。
 * <p>
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
 * 输出：[false,true]
 * 解释：课程 0 不是课程 1 的先修课程，但课程 1 是课程 0 的先修课程。
 * <p>
 * 示例 2：
 * 输入：numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
 * 输出：[false,false]
 * 解释：没有先修课程对，所以每门课程之间是独立的。
 * <p>
 * 示例 3：
 * 输入：numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
 * 输出：[true,true]
 * <p>
 * 提示：
 * 2 <= numCourses <= 100
 * 0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2)
 * prerequisites[i].length == 2
 * 0 <= ai, bi<= n - 1
 * ai!= bi
 * 每一对[ai, bi]都 不同
 * 先修课程图中没有环。
 * 1 <= queries.length <= 10^4
 * 0 <= ui, vi<= n - 1
 * ui!= vi
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/course-schedule-iv
 * @title: course-schedule-iv
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "checkIfPrerequisite", "in.txt");
    }


    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        ArrayList<Integer>[] g = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            g[i] = new ArrayList<Integer>();
        }
        for (int[] e : prerequisites) {
            g[e[0]].add(e[1]);
        }
        int m = queries.length;
        int[][] qes = new int[m][3];
        List<Boolean> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            qes[i][0] = queries[i][0];
            qes[i][1] = queries[i][1];
            qes[i][2] = i;
            ans.add(false);
        }
        Arrays.sort(qes, (a, b) -> a[0] - b[0]);

        boolean[] vis = new boolean[numCourses];
        for (int i = 0; i < m;) {
            int j = i;
            while (j < m && qes[i][0] == qes[j][0]) {
                j++;
            }
            dfs(qes[i][0], g, vis);
            for (int k = i; k < j; k++) {
                if (vis[qes[k][1]]) {
                    ans.set(qes[k][2], true);
                }
            }
            Arrays.fill(vis, false);
            i = j;
        }
        return ans;
    }

    public static void dfs(int fa, ArrayList<Integer>[] g, boolean[] vis) {
        vis[fa] = true;
        for (int next : g[fa]) {
            if (!vis[next]) {
                dfs(next, g, vis);
            }
        }
    }


}