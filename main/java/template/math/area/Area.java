package template.math.area;

/**
 * @author: wuxin0011
 * @Description: 计算常用面积
 */
public class Area {


    // 给点三个点位坐标 求构成的三角形面积
    // https://leetcode.cn/problems/largest-triangle-area/
    static double area3(int[] p1, int[] p2, int[] p3) {
        int x1 = p1[0], y1 = p1[1], x2 = p2[0], y2 = p2[1], x3 = p3[0], y3 = p3[1];
        return 0.5 * Math.abs(x1 * y2 + x2 * y3 + x3 * y1 - x1 * y3 - x2 * y1 - x3 * y2);
    }

    static double area4(int[] p1, int[] p2, int[] p3, int[] p4) {
        int x1 = p1[0], y1 = p1[1], x2 = p2[0], y2 = p2[1], x3 = p3[0], y3 = p3[1], x4 = p4[0], y4 = p4[1];
        return 0.5 * Math.abs(x1 * y2 + x2 * y3 + x3 * y4 + x4 * y1 - (y1 * x2 + y2 * x3 + y3 * x4 + y4 * x1));
    }

    // 求任意多边形面积 前提是 边不能相交
    // points[i]=[x,y] 是一个点坐标
    // 使用限制
    // 1 > 点的顺序必须正确
    // 2 > 不适用于自交多边形
    static double calcArea(int[]... points) {
        int n = points.length;
        double s1 = 0, s2 = 0;
        for (int i = 0; i < n; i++) {
            s1 += points[i][0] * points[(i + 1) % n][1];
            s2 += points[i][1] * points[(i + 1) % n][0];
        }
        return 0.5 * Math.abs(s1 - s2);
    }

}
