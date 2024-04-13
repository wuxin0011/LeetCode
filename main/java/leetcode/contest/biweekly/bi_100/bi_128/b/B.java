package leetcode.contest.biweekly.bi_100.bi_128.b;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/biweekly-contest-128/problems/minimum-rectangles-to-cover-points
 * @title: 覆盖所有点的最少矩形数目
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "minRectanglesToCoverPoints", "B.txt");
    }


    public int minRectanglesToCoverPoints(int[][] points, int w) {
        Arrays.sort(points, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int n = points.length;
        int cnt = 1;
        for (int r = 0, l = 0; r < points.length; r++) {
            while (r < n && points[r][0] - points[l][0] <= w) {
                r++;
            }
            if (r < n) {
                cnt++;
            }
            l = r;
        }
        return cnt;
    }


}