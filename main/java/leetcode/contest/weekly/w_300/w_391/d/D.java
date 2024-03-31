package leetcode.contest.weekly.w_300.w_391.d;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url
 * @title
 */
public class D {
    public static void main(String[] args) {
        IoUtil.testUtil(D.class, IoUtil.DEFAULT_METHOD_NAME, "D.txt");
    }

    public int minimumDistance(int[][] points) {
        int v1 = 0, v2 = 0;
        int n = points.length;
        for (int i = 0; i < n; i++) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i + 1; j < n; j++) {
                int x2 = points[j][0], y2 = points[j][1];
                double distance = Math.ceil(Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)));
                // System.out.println("distance = " + distance);
                int v = (int) distance;
                if (distance > v1) {
                    v2 = v1;
                    v1 = v;
                } else if (distance > v2) {
                    v2 = v;
                }
            }
        }
        return v2;
    }


}
