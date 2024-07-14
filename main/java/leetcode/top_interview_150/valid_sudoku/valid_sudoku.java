package leetcode.top_interview_150.valid_sudoku;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 */
public class valid_sudoku {



    static class S0 {
        public static void main(String[] args) {
            IoUtil.testUtil(S0.class, "isValidSudoku", "in.txt", true);
        }

        public boolean isValidSudoku(char[][] board) {
            return checkCenter(board) && checkCol(board) && checkRow(board);
        }

        // 3 * 3 可以枚举 3 * 3 的中心点 这样好写一点
        public static boolean checkCenter(char[][] board) {
            // 012 345 678
            for (int i = 1; i <= 7; i += 3) {
                for (int j = 1; j <= 7; j += 3) {
                    if (!(checkCenter(board, i, j))) {
                        return false;
                    }
                }
            }
            return true;
        }

        public static boolean checkCenter(char[][] board, int x, int y) {
            int[] h = new int[10];
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if(board[i][j] == '.') continue;
                    if (h[board[i][j] - '0'] > 0) {
                        return false;
                    }
                    h[board[i][j] - '0']++;
                }
            }
            return true;
        }

        // 枚举行
        public static boolean checkRow(char[][] board) {
            for (int i = 0; i < 9; i++) {
                int[] h = new int[10];
                for (int j = 0; j < 9; j++) {
                    if(board[j][i] == '.') continue;
                    if (h[board[j][i] - '0'] > 0) {
                        return false;
                    }
                    h[board[j][i] - '0']++;
                }
            }
            return true;
        }

        // 枚举行
        public static boolean checkCol(char[][] board) {
            for (int i = 0; i < 9; i++) {
                int[] h = new int[10];
                for (int j = 0; j < 9; j++) {
                    if(board[i][j] == '.') continue;
                    if (h[board[i][j] - '0'] > 0) {
                        return false;
                    }
                    h[board[i][j] - '0']++;
                }
            }
            return true;
        }


    }

    static class S1 {
        public static void main(String[] args) {
            IoUtil.testUtil(S1.class, "isValidSudoku", "in.txt", true);
        }

        public boolean isValidSudoku(char[][] board) {
            // System.out.println(Arrays.deepToString(board));
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
}
