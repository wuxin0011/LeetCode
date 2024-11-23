package leetcode._0x3f_.dp.grid_diagram.b_advance.Solution_0005;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/cherry-pickup-ii
 * @title: cherry-pickup-ii
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"cherryPickup","in.txt");
    }


    // https://leetcode.cn/problems/cherry-pickup-ii/submissions/582594786/
     

    public int cherryPickup(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.grid  = grid;
        this.memo = new int[m][n][m][n];
        for(int i = 0;i < m;i++) {
            for(int j = 0;j < n;j++){
                for(int k = 0;k < m;k++) {
                    Arrays.fill(memo[i][j][k],-1);
                }
            }
        }
        return Math.max(0,dfs(0,0,0,n - 1));
	}


    int[][][][] memo;
    int inf = 1 << 30,m,n,grid[][];


    // 两个题差不多题目数量量不大 ，因此可以使用记忆化搜索 就是修改一下 dir搜索方向

    static  int[][] dirs = {{1,-1},{1,0},{1,1}};
    boolean check(int i,int j) {
        return 0 <= i && i < m && 0 <= j && j < n && grid[i][j] != -1;
    }


    int dfs(int i,int j,int i0,int j0) {
        if((!check(i,j) || !check(i0,j0))){
            return -inf;
        }
        if(i == m - 1 ){
            return grid[i][j] + (i == i0 && j == j0 ? 0 : grid[i0][j0]);
        }
        if(memo[i][j][i0][j0] != -1){
            return memo[i][j][i0][j0];
        }
        int ans = -inf;
        for(int[] dir1 : dirs) {
            for(int[] dir2 : dirs) {
                ans = Math.max(ans,dfs(i + dir1[0],j + dir1[1],i0 + dir2[0],j0 + dir2[1]));
            }
        }
        ans += grid[i][j] + (i == i0 && j == j0 ? 0 : grid[i0][j0]);
        return memo[i][j][i0][j0] = ans;
    }






  

}