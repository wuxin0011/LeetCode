package leetcode.contest.biweekly.bi_100.bi_130.b;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 100302. 正方形中的最多点数
 * <p>
 * 给你一个二维数组points和一个字符串s，其中points[i]表示第 i个点的坐标，s[i]表示第 i个点的 标签。
 * 如果一个正方形的中心在(0, 0)，所有边都平行于坐标轴，且正方形内不存在标签相同的两个点，那么我们称这个正方形是合法的。
 * 请你返回 合法正方形中可以包含的 最多点数。
 * 注意：
 * 如果一个点位于正方形的边上或者在边以内，则认为该点位于正方形内。
 * 正方形的边长可以为零。
 * <p>
 * 示例 1：
 * 输入：points = [[2,2],[-1,-2],[-4,4],[-3,1],[3,-3]], s = "abdca"
 * 输出：2
 * 解释：
 * 边长为 4 的正方形包含两个点points[0] 和points[1]。
 * <p>
 * 示例 2：
 * 输入：points = [[1,1],[-2,-2],[-2,2]], s = "abb"
 * 输出：1
 * 解释：
 * 边长为 2 的正方形包含 1 个点points[0]。
 * <p>
 * 示例 3：
 * 输入：points = [[1,1],[-1,-1],[2,-2]], s = "ccd"
 * 输出：0
 * 解释：
 * 任何正方形都无法只包含points[0] 和points[1]中的一个点，所以合法正方形中都不包含任何点。
 * <p>
 * 提示：
 * 1 <= s.length, points.length <= 10^5
 * points[i].length == 2
 * -10^9 <= points[i][0], points[i][1] <= 10^9
 * s.length == points.length
 * points中的点坐标互不相同。
 * s只包含小写英文字母。
 *
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/biweekly-contest-130/problems/maximum-points-inside-the-square
 * @title: 正方形中的最多点数
 */
public class B {

    public static void main(String[] args) {
        // IoUtil.testUtil(B.class, "maxPointsInsideSquare", "B.txt");
        IoUtil.testUtil(B.class, "maxPointsInsideSquare2", "B.txt");
    }


    // 根据半径绝对值大小 从小到到排序
    // 然后慢慢扩大半径
    public int maxPointsInsideSquare(int[][] points, String s) {
        int n = points.length;
        int[][] h = new int[n][3];
        boolean[] vis = new boolean[26];
        for (int i = 0; i < points.length; i++) {
            h[i][0] = points[i][0];
            h[i][1] = points[i][1];
            h[i][2] = s.charAt(i) - 'a';
        }
        Arrays.sort(h, (a, b) -> Math.max(Math.abs(a[0]), Math.abs(a[1])) - Math.max(Math.abs(b[0]), Math.abs(b[1])));
        int cnt = 0;
        for (int i = 0, L, c; i < n; ) {
            c = 0;
            L = Math.max(Math.abs(h[i][0]), Math.abs(h[i][1]));
            while (i < n && L >= Math.max(Math.abs(h[i][0]), Math.abs(h[i][1]))) {
                int pos = h[i][2];
                if (vis[pos]) {
                    return cnt;
                }
                c++;
                vis[pos] = true;
                i++;
            }
            cnt += c;

        }
        return cnt;
    }


    // 二分
    // 对于半径越大就能包括字符久越多 同时 如果遇到 同样字符应该减小半径
    public int maxPointsInsideSquare2(int[][] points, String s) {
        char[] cs = s.toCharArray();
        int r = 0;
        for (int[] point : points) {
            r = Math.max(maxAbs(point), r);
        }
        int l = 0; // 最小半径
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            // check
            if (check(m, points, cs)) {
                l = m + 1; // 增大半径
            } else {
                r = m - 1; // 缩小半径
            }
        }

        int cnt = 0;
        for (int[] point : points) {
            if (maxAbs(point) <= r) {
                cnt++;
            }
        }
        return cnt;
    }

    public static int maxAbs(int... args) {
        int mx = 0;
        for (int arg : args) {
            mx = Math.max(mx, Math.abs(arg));
        }
        return mx;
    }

    public static boolean check(int r, int[][] points, char[] cs) {
        int mask = 0; // 最多26中用来代替boolean数组
        for (int i = 0; i < points.length; i++) {
            if (maxAbs(points[i]) > r) {
                continue;
            }
            int idx = cs[i] - 'a';
            if ((mask >> idx & 1) == 1) {
                return false;
            }
            mask |= 1 << idx;
        }
        return true;
    }


}