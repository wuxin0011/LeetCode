package leetcode.contest.weekly.w_400.w_427.b;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/weekly-contest-427/problems/maximum-area-rectangle-with-point-constraints-i">用点构造面积最大的矩形 I</a>
 * @title: 用点构造面积最大的矩形 I
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "maxRectangleArea", "B.txt");
    }

    int[][] points;

    public int maxRectangleArea(int[][] points) {
        this.points = points;
        int ans = -1;
        int n = points.length;
        for (int mask = 0; mask < (1 << n); mask++) {
            if (Integer.bitCount(mask) != 4) {
                continue;
            }
            List<int[]> cur = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((mask >> j & 1) == 1) {
                    cur.add(points[j]);
                }
            }
            ans = Math.max(ans, calc(cur));
        }
        return ans;
    }


    public int calc(List<int[]> cur) {
        int x_max = -1;
        int y_max = -1;
        int x_min = 1 << 30;
        int y_min = 1 << 30;
        for (int[] p : cur) {
            x_max = Math.max(x_max, p[0]);
            y_max = Math.max(y_max, p[1]);
            x_min = Math.min(x_min, p[0]);
            y_min = Math.min(y_min, p[1]);
        }

        int[] p1 = cur.get(0);
        int[] p2 = null;
        int[] p3 = null;
        int[] p4 = null;

        int id = -1;
        for (int i = 1; i < cur.size(); i++) {
            int[] p = cur.get(i);
            if (p[0] == p1[0]) {
                id = i;
                p2 = p;
            }
        }

        for (int i = 1; i < cur.size(); i++) {
            if (id == i) {
                continue;
            }
            int[] p = cur.get(i);
            if (p3 == null) {
                p3 = p;
            } else if (p4 == null) {
                p4 = p;
            }
        }

        if (p1 == null || p2 == null || p3 == null || p4 == null) {
            return -1;
        }

        if (p1[0] != p2[0]) {
            return -1;
        }


        if (p3[0] != p4[0]) {
            return -1;
        }

        Map<Integer, Integer> x_cnt1 = new HashMap<>();
        Map<Integer, Integer> y_cnt1 = new HashMap<>();

        for (int[] p : points) {
            int x = p[0], y = p[1];
            if (x_min <= x && x <= x_max && y_min <= y && y <= y_max) {
                x_cnt1.put(p[0], x_cnt1.getOrDefault(p[0], 0) + 1);
                y_cnt1.put(p[1], y_cnt1.getOrDefault(p[1], 0) + 1);
            }
        }

        for (Map.Entry<Integer, Integer> item : x_cnt1.entrySet()) {
            if (item.getValue() != 2) {
                return -1;
            }
        }
        for (Map.Entry<Integer, Integer> item : y_cnt1.entrySet()) {
            if (item.getValue() != 2) {
                return -1;
            }
        }

        int w = Math.abs(p1[1] - p2[1]);
        int h = Math.abs(p3[0] - p2[0]);
        return w * h;
    }


}