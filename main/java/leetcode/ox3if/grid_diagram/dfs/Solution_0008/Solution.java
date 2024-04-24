package leetcode.ox3if.grid_diagram.dfs.Solution_0008;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 * 130. 被围绕的区域
 *
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的'O' 用 'X' 填充。
 *
 * 示例 1：
 * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的'O'都不会被填充为'X'。 任何不在边界上，或不与边界上的'O'相连的'O'最终都会被填充为'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *
 * 示例 2：
 * 输入：board = [["X"]]
 * 输出：[["X"]]
 *
 * 提示：
 * 	m == board.length
 * 	n == board[i].length
 * 	1 <= m, n <= 200
 * 	board[i][j] 为 'X' 或 'O'
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/surrounded-regions
 * @title: 被围绕的区域
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"solve","in.txt");
    }


    public void solve(char[][] board) {
        if( board == null) return;
        int m = board.length,n = board[0].length;
        for(int i = 0;i<m;i++){
            if(board[i][0] == 'O'){
                f(board,m,n,i,0);
            }
            if(board[i][n-1] == 'O'){
                f(board,m,n,i,n-1);
            }

        }
        for(int i = 0;i<n;i++){
            if(board[0][i] == 'O'){
                f(board,m,n,0,i);
            }
            if(board[m-1][i] == 'O'){
                f(board,m,n,m-1,i);
            }
        }


        for(int i = 0;i<m;i++){
            for(int j = 0;j<n;j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }else if(board[i][j] == 'A'){
                    board[i][j] = 'O';
                }
            }
        }
    }


    public static void f(char[][] board,int m,int n,int x,int y){
        if( x < 0 || x >= m|| y<0 || y >= n){
            return;
        }
        if( board[x][y] == 'X' || board[x][y] == 'A'){
            return;
        }
        board[x][y] = 'A';
        f(board,m,n,x-1,y);
        f(board,m,n,x+1,y);
        f(board,m,n,x,y-1);
        f(board,m,n,x,y+1);
    }

  

}