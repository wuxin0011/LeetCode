package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 1329. 将矩阵按对角线排序
 * <p>
 * 矩阵对角线 是一条从矩阵最上面行或者最左侧列中的某个元素开始的对角线，沿右下方向一直到矩阵末尾的元素。
 * 例如，矩阵 mat 有 6 行 3 列，从 mat[2][0] 开始的 矩阵对角线 将会经过 mat[2][0]、mat[3][1] 和 mat[4][2] 。
 * 给你一个m * n的整数矩阵mat，请你将同一条 矩阵对角线 上的元素按升序排序后，返回排好序的矩阵。
 * <p>
 * 示例 1：
 * 输入：mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
 * 输出：[[1,1,1,1],[1,2,2,2],[1,2,3,3]]
 * <p>
 * 示例 2：
 * 输入：mat = [[11,25,66,1,69,7],[23,55,17,45,15,52],[75,31,36,44,58,8],[22,27,33,25,68,4],[84,28,14,11,5,50]]
 * 输出：[[5,17,4,1,52,7],[11,11,25,45,8,69],[14,23,25,44,58,15],[22,27,31,36,50,66],[84,28,75,33,55,68]]
 * <p>
 * 提示：
 * m ==mat.length
 * n ==mat[i].length
 * 1 <= m, n<= 100
 * 1 <= mat[i][j] <= 100
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/sort-the-matrix-diagonally
 * @title: 将矩阵按对角线排序
 */
public class Code_0059_1329 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0059_1329.class, "diagonalSort", "txt_file\\Code_0059_1329.txt");
    }


    public static boolean check(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public static void update(int[][] mat, Integer[] h, int r, int c, int m, int n, int k) {
        int or = r, oc = c;
        while (check(r, c, m, n)) {
            h[k++] = mat[r][c];
            r++;
            c++;
        }
        Arrays.sort(h, 0, k, (a, b) -> a - b);
        r = or;
        c = oc;
        int j = 0;
        while (check(r, c, m, n) && j < k) {
            mat[r][c] = h[j++];
            r++;
            c++;
        }
    }

    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        Integer[] h = new Integer[m + n + 1];
        for (int i = m - 1; i >= 0; i--) {
            update(mat, h, i, 0, m, n, 0);
        }

        for (int i = 1; i < n; i++) {
            update(mat, h, 0, i, m, n, 0);
        }
        return mat;
    }


}