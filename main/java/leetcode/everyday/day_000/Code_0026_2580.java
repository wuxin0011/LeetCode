package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/count-ways-to-group-overlapping-ranges
 * @title: count-ways-to-group-overlapping-ranges
 */
public class Code_0026_2580 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0026_2580.class, "countWays", "txt_file\\Code_0026_2580.txt");
    }

    private static final int MOD = (int) 1e9 + 7;


    public int countWays(int[][] ranges) {

        Arrays.sort(ranges, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int st = ranges[0][0], ed = ranges[0][1];
        int cnt = 2;
        for (int i = 1; i < ranges.length; i++) {
            int[] v = ranges[i];
            if (in(ed, v[0])) {
                ed = Math.max(v[1], ed);
            } else {
                st = v[0];
                ed = v[1];
                if (cnt >= Integer.MAX_VALUE / 2) {
                    cnt %= MOD;
                }
                // type++;
                cnt *= 2;
            }
        }

        return (int) (cnt % MOD);
    }

    public static boolean in(int a, int b) {
        return a >= b;
    }
}
