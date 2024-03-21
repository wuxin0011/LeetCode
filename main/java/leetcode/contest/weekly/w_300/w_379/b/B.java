package leetcode.contest.weekly.w_300.w_379.b;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description: 这题属于脑筋急转题目 最多只能2中 当有一个挡住时候 可以挪开挡住的棋子
 * @url https://leetcode.cn/problems/minimum-moves-to-capture-the-queen/
 * @title
 */
public class B {
    public static void main(String[] args) {
        IoUtil.testUtil(B.class, IoUtil.DEFAULT_METHOD_NAME, "B.txt");
    }

    public int minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {

        // 分为在一条线上和不在一条线上
        if (
                a == e && (c != e || notInMid(b, d, f))
                        ||
                        b == f && (d != f || notInMid(a, c, e))
                        ||
                        c + d == e + f && (a + b != e + f || notInMid(c, a, e))
                        ||
                        c - d == e - f && (a - b != e - f || notInMid(c, a, e))
        ) {

            return 1;


        }

        return 2;
    }


    public static boolean notInMid(int x, int y, int z) {
        return !(Math.min(x, z) < y && y < Math.max(x, z));
    }
}
