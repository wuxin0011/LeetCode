package leetcode.top_interview_150.graph;

import leetcode.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 */
public class surrounded_regions {

    public static void main(String[] args) {
        IoUtil.testUtil(surrounded_regions.class,"solve","./txt_file/surrounded_regions.txt");
    }


    public void solve(char[][] board) {
        if (board == null) return;
        // System.out.println(Arrays.deepToString(board));
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                f(board, m, n, i, 0);
            }
            if (board[i][n - 1] == 'O') {
                f(board, m, n, i, n - 1);
            }

        }
        for (int i = 0; i < n; i++) {
            if (board[0][i] == 'O') {
                f(board, m, n, 0, i);
            }
            if (board[m - 1][i] == 'O') {
                f(board, m, n, m - 1, i);
            }
        }


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                }
            }
        }
    }


    public static void f(char[][] board, int m, int n, int x, int y) {
        if (x < 0 || x >= m || y < 0 || y >= n) {
            return;
        }
        if (board[x][y] == 'X' || board[x][y] == 'A') {
            return;
        }
        board[x][y] = 'A';
        f(board, m, n, x - 1, y);
        f(board, m, n, x + 1, y);
        f(board, m, n, x, y - 1);
        f(board, m, n, x, y + 1);
    }
}
