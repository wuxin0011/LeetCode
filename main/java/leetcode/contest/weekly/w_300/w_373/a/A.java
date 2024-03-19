package leetcode.contest.weekly.w_300.w_373.a;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description: 循环移位后的矩阵相似检查
 * @url https://leetcode.cn/problems/matrix-similarity-after-cyclic-shifts/
 */
public class A {
    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "areSimilar", "in.txt");
    }

    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        k = k % n;
        if (k == 0) return true;
        for (int[] ints : mat) {
            for (int j = 0; j < n; j++) {
                int real = (j + k) % n;
                if (ints[real] != ints[j]) return false;
            }
        }
        return true;
    }


}
