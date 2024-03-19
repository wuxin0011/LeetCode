package leetcode.top_interview_150.graph;

import code_generation.utils.IoUtil;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description:
 */
public class course_schedule {

    public static void main(String[] args) {
        IoUtil.testUtil(course_schedule.class, "canFinish", "./txt_file/course_schedule.txt");
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 构建点集
        int[] in = new int[numCourses];

        // 构建边集
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            in[edge[0]]++; // 入读++
            // 绑定
            edges.get(edge[1]).add(edge[0]);
        }
        Deque<Integer> q = new ArrayDeque<>();

        // 找到入读为0的点为起点
        for (int i = 0; i < numCourses; i++) {
            if (in[i] == 0) q.add(i);
        }
        while (!q.isEmpty()) {
            int pre = q.poll();
            numCourses--;
            for (int cur : edges.get(pre)) {
                in[cur]--;
                if (in[cur] == 0) {
                    q.add(cur);
                }
            }
        }
        // 判断是否完全消除
        return numCourses == 0;
    }
}
