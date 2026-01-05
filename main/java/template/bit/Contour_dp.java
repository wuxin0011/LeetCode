package template.bit;

/**
 * https://leetcode.cn/problems/number-of-ways-to-paint-n-3-grid
 * 模板题 ： 轮廓线dp专用
 * @author: wuxin0011
 * @Description:
 */
class Contour_dp {



    private static final int V0 = 3;
    // 需要根据题面状态大小设置合适值
    

    // 获取S的第j位置的值
    public static int getV(int S, int j) {
        return (S >> (j << 1)) & 3;
    }

    //将S的第j位设置为v
    public static int setV(int S, int j, int v) {
        return S & (~(3 << (j << 1))) | (v << (j << 1));
    }
}

