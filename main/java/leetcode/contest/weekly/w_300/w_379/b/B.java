package leetcode.contest.weekly.w_300.w_379.b;

import code_generation.utils.IoUtil;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   https://leetcode.cn/contest/weekly-contest-379/problems/minimum-moves-to-capture-the-queen
 * @title: 捕获黑皇后需要的最少移动次数
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "minMovesToCaptureTheQueen", "B.txt");
    }


    public int minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {

        // a b => 车
        // c d => 象
        // e f => 黑
        // 分为 在同一行
        // 1、不在同一行
        // 2、在同一行 但是 列合法
        if (a == e && (c != e || check(b,d,f)) || b == f && (d != f || check(a,c,e))) {
            return 1;
        }

        if (
                c - d == e - f && (c - d != a - b || check(c,a,e))
                        ||
                        c + d == e + f && (c + d != a + b || check(c,a,e))

        ) {
            return 1;
        }

        return 2;
    }

    public boolean check(int i, int j, int k) {
        return !(j >= Math.min(i, k) && j <= Math.max(i, k));
    }


}