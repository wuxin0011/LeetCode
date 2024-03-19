package leetcode.contest.weekly.w_300.w_386.b;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url https://leetcode.cn/problems/find-the-largest-area-of-square-inside-two-rectangles/
 */
public class B {
    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "largestSquareArea");
    }

    // 你可以选择一个区域，该区域由两个矩形的 交集 形成。你需要找出能够放入该区域 内 的 最大 正方形面积，并选择最优解。
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        long ans = 0;
        int n = bottomLeft.length;
        for (int i = 0; i < n; i++) {
            int[] b1 = bottomLeft[i], t1 = topRight[i];
            for (int j = i + 1; j < n; j++) {
                int[] b2 = bottomLeft[j], t2 = topRight[j];
                int height = Math.min(t1[1], t2[1]) - Math.max(b1[1], b2[1]);
                int width = Math.min(t1[0], t2[0]) - Math.max(b1[0], b2[0]);
                int size = Math.min(width, height);
                if (size > 0) {
                    ans = Math.max(ans, (long) size * size);
                }
            }
        }
        return ans;
    }
}
