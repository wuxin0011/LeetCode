package template.math.area;

/**
 * @author: wuxin0011
 * @Description: 计算常用面积
 */
public class Area {


    // 给点三个点位坐标 求构成的三角形面积
    // https://leetcode.cn/problems/largest-triangle-area/
    static double area3(int[] p1,int[] p2,int[] p3){
        int x1 = p1[0],y1=p1[1],x2 = p2[0],y2=p2[1],x3 = p3[0],y3=p3[1];
        return 0.5 * Math.abs(x1*y2+x2*y3+x3*y1-x1*y3-x2*y1-x3*y2);
    }


    // 拓展 给定四个点坐标
    // (如果是平行四边形 可以通过使用上面公式 * 2 就是平行四边形面积 (平行四边形可以看作两个面积相等三角形构成)
}
