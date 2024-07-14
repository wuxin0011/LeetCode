package leetcode.top_interview_150.rotate_image;

import code_generation.utils.IoUtil;
/**
 *
 *
 * 48. 旋转图像
 *
 * 给定一个 n×n 的二维矩阵matrix 表示一个图像。
 * 请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 *
 * 示例 2：
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 *
 * 提示：
 * 	n == matrix.length == matrix[i].length
 * 	1 <= n <= 20
 * 	-1000 <= matrix[i][j] <= 1000
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/rotate-image
 * @title: 旋转图像
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "rotate", "in.txt");
        IoUtil.testUtil(Solution.class, "rotate1", "in.txt");
    }

    public void rotate(int[][] matrix) {
        // 上下翻转 + 对角线翻转
        int n = matrix.length;
        int mid = n >> 1;
        // 上下反转
        for(int i = 0;i<mid;i++) {
            int[] t = matrix[i];
            matrix[i] = matrix[n - i - 1];
            matrix[n - i - 1] =  t;
        }

        // 对角线
        for(int i = 0;i < n;i++) {
            for(int j = i + 1;j < n;j++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = t;
            }
        }
    }

    public void rotate1(int[][] matrix) {

        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                swap(matrix, i, j, j, i);
            }
        }
        int l = 0, r = n - 1;
        while (l < r) {
            for (int i = 0; i < n; i++) {
                swap(matrix, i, l, i, r);
            }
            l++;
            r--;
        }

    }


    public static void swap(int[][] mat, int i0, int j0, int i1, int j1) {
        int x = mat[i0][j0];
        mat[i0][j0] = mat[i1][j1];
        mat[i1][j1] = x;
    }


}