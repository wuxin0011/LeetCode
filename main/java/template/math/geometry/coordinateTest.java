package template.math.geometry;

/**
 * @author: wuxin0011
 * @Description:
 */
public class coordinateTest {


    // 判断三点共线 根据  =>(y1-y2)/(x1-x2)=(y3-y2)/(x3-x2)
    public boolean isLine(int[] p1,int[] p2,int[] p3){
        return 1L*(p1[1]-p2[1])*(p3[0]-p2[0])==1L*(p3[1]-p2[1])*(p1[0]-p2[0]);
    }


    // 判断 两条线平行
    // 方法一 向量点乘
    // p1、p2 和 p3、p4 构成的线是否平行
    static boolean areLinesParallel(int[] p1, int[] p2, int[] p3, int[] p4) {
        return 1L*(p2[0] - p1[0]) * (p4[1] - p3[1]) - 1L*(p2[1] - p1[1]) * (p4[0] - p3[0]) == 0;
    }

    // 方法二 斜率
    static boolean areLinesParallel2(int[] p1, int[] p2, int[] p3, int[] p4) {
        int dx1 = p2[0] - p1[0];
        int dy1 = p2[1] - p1[1];
        int dx2 = p4[0] - p3[0];
        int dy2 = p4[1] - p3[1];
        if (dx1 == 0 && dx2 == 0) {
            return true;
        } else if (dx1 == 0 || dx2 == 0) {
            return false;
        }
        return dy1 * dx2 == dy2 * dx1;
    }
}
