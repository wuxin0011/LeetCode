package leetcode.top_interview_150.valid_sudoku;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 */
public class valid_sudoku {

    public static void main(String[] args) {
        IoUtil.testUtil(valid_sudoku.class, "isValidSudoku", "in.txt", true);
    }

    public boolean isValidSudoku(char[][] board) {
        System.out.println(Arrays.deepToString(board));
        int[] helps1 = new int[10];
        int[] helps2 = new int[10];
        for (int i = 0; i < 9; i++) {
            fill(helps1);
            fill(helps2);
            // System.out.println("row====>");
            for (int j = 0; j < 9; j++) {
                // System.out.println("row-> board["+i+"]["+j+"] = " + board[i][j] + ",cor-> board["+j+"]["+i+"]="+board[j][i]);
                if (board[i][j] != '.') {
                    int val = board[i][j] - '0';
                    helps1[val]++;
                    if (helps1[val] > 1) return false;
                }
                if (board[j][i] != '.') {
                    int val = board[j][i] - '0';
                    helps2[val]++;
                    if (helps2[val] > 1) return false;
                }

            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fill(helps1);
                for (int k = i * 3; k < (i + 1) * 3; k++) {
                    for (int t = j * 3; t < (j + 1) * 3; t++) {
                        if (board[k][t] != '.') {
                            int val = board[k][t] - '0';
                            helps1[val]++;
                            if (helps1[val] > 1) return false;
                        }
                    }
                }
            }
        }

        return true;
    }


    public static void fill(int[] nums) {
        Arrays.fill(nums, 0);
    }
}
