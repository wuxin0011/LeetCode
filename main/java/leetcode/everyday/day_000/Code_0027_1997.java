package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;
/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/first-day-where-you-have-been-in-all-the-rooms
 * @title: first-day-where-you-have-been-in-all-the-rooms
 */
public class Code_0027_1997 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0027_1997.class, "firstDayBeenInAllRooms", "txt_file\\Code_0027_1997.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public int firstDayBeenInAllRooms(int[] nextVisit) {
        int n = nextVisit.length;
        long[] s = new long[n];
        for (int i = 0; i < n - 1; i++) {
            int j = nextVisit[i];
            s[i + 1] = (s[i] * 2 - s[j] + 2 + MOD) % MOD; // + MOD 避免算出负数
        }
        return (int) s[n - 1];
    }

}