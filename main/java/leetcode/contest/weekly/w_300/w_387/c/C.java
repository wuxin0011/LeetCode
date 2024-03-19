package leetcode.contest.weekly.w_300.w_387.c;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @title 在矩阵上写出字母 Y 所需的最少操作次数
 * @Description: 模拟 注意边界
 * @url https://leetcode.cn/problems/minimum-operations-to-write-the-letter-y-on-a-grid/
 */
public class C {
    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "minimumOperationsToWriteY", "in.txt");
    }

    public int minimumOperationsToWriteY(int[][] grid) {
        int n = grid.length;
        int[] cntY = new int[3];
        int[] cntS = new int[3];
        int tot = 0;
        int c = n >> 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 统计Y
                if (i == j && i <= c || i >= c && j == c || i == n - j - 1 && i < c) {
                    cntY[grid[i][j]]++;
                    tot++;
                } else {
                    cntS[grid[i][j]]++;
                }
            }
        }
        int cnt = n * n;
        // System.out.println(tot);
        for (int i = 0; i <= 2; i++) {
            //  Y 中需要变化的 =  Y 所有 - 当前不变
            //  其他需要变化的 = 当前对应的 + min(两个值二选一)
            int u = tot - cntY[i] + cntS[i];
            int s0 = -1, s1 = -1;
            for (int j = 0; j <= 2; j++) {
                if (i != j) {
                    if (s0 == -1) s0 = cntS[j];
                    else s1 = cntS[j];
                }
            }
            cnt = Math.min(cnt, u + Math.min(s0, s1));
        }
        return cnt;
    }
}
