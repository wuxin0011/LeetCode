package leetcode.contest.weekly.w_300.w_379.a;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url https://leetcode.cn/problems/maximum-area-of-longest-diagonal-rectangle/
 * @title
 */
public class A {
    public static void main(String[] args) {
        IoUtil.testUtil(A.class, IoUtil.DEFAULT_METHOD_NAME, "A.txt");
    }

    public int areaOfMaxDiagonal(int[][] dimensions) {
        long l = 0;
        int area = 0;
        for (int[] d : dimensions) {
            long m = (long) d[0] * d[0] + (long) d[1] * d[1];
            int a = d[0] * d[1];
            if (m == l) {
                area = Math.max(area, a);
            } else if (l < m) {
                l = m;
                area = a;
            }
        }
        return area;
    }
}
