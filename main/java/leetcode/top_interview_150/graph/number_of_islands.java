package leetcode.top_interview_150.graph;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 */
public class number_of_islands {

    public static void main(String[] args) {
        IoUtil.testUtil(number_of_islands.class, "numIslands", "./txt_file/number_of_islands.txt", true);
    }


    public int numIslands(char[][] nums) {
        int cnt = 0;
        int m = nums.length, n = nums[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (nums[i][j] == '1') {
                    cnt++;
                    f(nums, m, n, i, j);
                }
            }
        }
        return cnt;
    }

    public void f(char[][] nums, int m, int n, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || nums[i][j] != '1') {
            return;
        }
        nums[i][j] = '0';

        f(nums, m, n, i + 1, j);
        f(nums, m, n, i - 1, j);
        f(nums, m, n, i, j - 1);
        f(nums, m, n, i, j + 1);
    }
}
