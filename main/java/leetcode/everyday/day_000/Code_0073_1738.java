package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/find-kth-largest-xor-coordinate-value
 * @title: find-kth-largest-xor-coordinate-value
 */
public class Code_0073_1738 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0073_1738.class, "kthLargestValue", "txt_file\\Code_0073_1738.txt");
    }


    public int kthLargestValue(int[][] matrix, int k) {
        // 读题题 二维前缀和
        // PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b.compareTo(a));
        int m = matrix.length, n = matrix[0].length;
        int[] a = new int[m * n];
        int size = 0;
        int[][] sums = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sums[i][j] = sums[i - 1][j - 1] ^ sums[i][j - 1] ^ sums[i - 1][j] ^ matrix[i - 1][j - 1];
                a[size++] = sums[i][j];
            }
        }
        Arrays.sort(a);
        return a[size - k];
    }

}