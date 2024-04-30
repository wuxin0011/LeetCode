package leetcode.contest.weekly.w_300.w_395.c;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-395/problems/minimum-array-end
 * @title: 数组最后一个元素的最小值
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "minEnd", "C.txt");
        // System.out.println(check(6, 4, 3));
        // System.out.println(check(3, 1, 2));
    }


    public long minEnd(int n, int x) {
        if (n == 1) return x;
        n--;
        int i = 0, j = 0;
        while ((n >> j) == 1) {
            if ((x >> i & 1) == 0) {
                int b = x >> j & 1;
                x |= b << 1;
                j++;
            }
            i++;
        }
        return x;
    }
}