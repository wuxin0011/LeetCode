package leetcode.top_interview_150.sudoku_solver;

import code_generation.utils.IoUtil;

/**
 * 37. 解数独
 * <p>
 * 编写一个程序，通过填充空格来解决数独问题。
 * 数独的解法需 遵循如下规则：
 * 数字1-9在每一行只能出现一次。
 * 数字1-9在每一列只能出现一次。
 * 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。（请参考
 * 示例图）
 * 数独部分空格内已填入了数字，空白格用'.'表示。
 * <p>
 * 示例 1：
 * 输入：board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
 * 输出：[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
 * 解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
 * <p>
 * 提示：
 * board.length == 9
 * board[i].length == 9
 * board[i][j] 是一位数字或者 '.'
 * 题目数据 保证 输入数独仅有一个解
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/sudoku-solver
 * @title: 解数独
 */
public class Solution {


    // 位运算方式
    static class s0 {

        public static void main(String[] args) {
            IoUtil.testUtil(s0.class, "solveSudoku", "in.txt");
        }

        // 解答本题之前先处理验证有效数独
        // 暴力枚举每个格子填写的策略
        static char[] map = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        int[] rows = new int[10];
        int[] cols = new int[10];
        int[] center = new int[10];

        public void solveSudoku(char[][] board) {
            init(board);
            dfs(board);
        }

        void init(char[][] board) {
            int n = board.length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == '.') continue;
                    add(i, j, board[i][j] - '0');
                }
            }

        }

        public boolean dfs(char[][] board) {
            int n = board.length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] != '.') continue;
                    for (int v = 1; v <= 9; v++) {
                        if (check(i, j, v)) {
                            board[i][j] = map[v];
                            add(i, j, v);
                            // 深度搜索
                            if (dfs(board)) {
                                return true;
                            }
                            // 回溯部分
                            board[i][j] = '.';
                            remove(i, j, v);
                        }
                    }
                    return false;
                }
            }
            return true;
        }


        // 验证有效数独部分内容


        public boolean check(int i, int j, int v) {
            // check col

            if ((rows[i] >> v & 1) == 1) {
                return false;
            }
            // check rol
            if ((cols[j] >> v & 1) == 1) {
                return false;
            }
            // check 3 * 3
            // 获得中心点
            return (center[getId(i, j)] >> v & 1) != 1;
        }

        public void add(int i, int j, int v) {
            // check col
            rows[i] |= 1 << v;
            cols[j] |= 1 << v;
            center[getId(i, j)] |= 1 << v;
        }

        public void remove(int i, int j, int v) {
            // check col
            rows[i] &= ~(1 << v);
            cols[j] &= ~(1 << v);
            center[getId(i, j)] &= ~(1 << v);
        }

        public int getId(int i, int j) {
            int x = i / 3 * 3 + 1;
            int y = j / 3 * 3 + 1;
            if (x == 1) {
                return y == 1 ? 1 : y == 4 ? 2 : 3;
            }
            if (x == 4) {
                return y == 1 ? 4 : y == 4 ? 5 : 6;
            }
            if (x == 7) {
                return y == 1 ? 7 : y == 4 ? 8 : 9;
            }
            return -1;
        }
    }


    static class s1 {

        public static void main(String[] args) {
            IoUtil.testUtil(s1.class, "solveSudoku", "in.txt");
        }

        // 解答本题之前先处理验证有效数独
        // 暴力枚举每个格子填写的策略
        static char[] map = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        public void solveSudoku(char[][] board) {
            dfs(board);
        }

        public static boolean dfs(char[][] board) {
            int n = board.length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] != '.') continue;
                    for (int v = 1; v <= 9; v++) {
                        if (check(board, i, j, map[v])) {
                            board[i][j] = map[v];
                            // 深度搜索
                            if (dfs(board)) {
                                return true;
                            }
                            // 回溯部分
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
            return true;
        }


        // 验证有效数独部分内容


        public static boolean check(char[][] board, int i, int j, char v) {
            // check row
            for (int c = 0; c < 9; c++) {
                if (board[i][c] == v) {
                    return false;
                }
            }
            // check col
            for (int r = 0; r < 9; r++) {
                if (board[r][j] == v) {
                    return false;
                }
            }
            // check 3 * 3
            // 获得中心点
            int x = i / 3 * 3 + 1;
            int y = j / 3 * 3 + 1;
            for (int r = x - 1; r <= x + 1; r++) {
                for (int c = y - 1; c <= y + 1; c++) {
                    if (board[r][c] == v) {
                        return false;
                    }
                }
            }
            return true;
        }
    }


}