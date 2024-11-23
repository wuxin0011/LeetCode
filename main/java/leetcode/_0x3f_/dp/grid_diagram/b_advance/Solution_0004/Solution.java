package leetcode._0x3f_.dp.grid_diagram.b_advance.Solution_0004;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 *
 * 741. 摘樱桃
 *
 * 给你一个 n x n 的网格 grid ，代表一块樱桃地，每个格子由以下三种数字的一种来表示：
 * 	0 表示这个格子是空的，所以你可以穿过它。
 * 	1 表示这个格子里装着一个樱桃，你可以摘到樱桃然后穿过它。
 * 	-1 表示这个格子里有荆棘，挡着你的路。
 * 请你统计并返回：在遵守下列规则的情况下，能摘到的最多樱桃数：
 * 	从位置(0, 0) 出发，最后到达 (n - 1, n - 1) ，只能向下或向右走，并且只能穿越有效的格子（即只可以穿过值为 0 或者 1 的格子）；
 * 	当到达 (n - 1, n- 1) 后，你要继续走，直到返回到 (0, 0) ，只能向上或向左走，并且只能穿越有效的格子；
 * 	当你经过一个格子且这个格子包含一个樱桃时，你将摘到樱桃并且这个格子会变成空的（值变为 0 ）；
 * 	如果在 (0, 0) 和 (n - 1, n - 1) 之间不存在一条可经过的路径，则无法摘到任何一个樱桃。
 *
 * 示例 1：
 * 输入：grid = [[0,1,-1],[1,0,-1],[1,1,1]]
 * 输出：5
 * 解释：玩家从 (0, 0) 出发：向下、向下、向右、向右移动至 (2, 2) 。
 * 在这一次行程中捡到 4 个樱桃，矩阵变成 [[0,1,-1],[0,0,-1],[0,0,0]] 。
 * 然后，玩家向左、向上、向上、向左返回起点，再捡到 1 个樱桃。
 * 总共捡到 5 个樱桃，这是最大可能值。
 *
 * 示例 2：
 * 输入：grid = [[1,1,-1],[1,-1,1],[-1,1,1]]
 * 输出：0
 *
 * 提示：
 * 	n == grid.length
 * 	n == grid[i].length
 * 	1 <= n <= 50
 * 	grid[i][j]为-1、0或1
 * 	grid[0][0] != -1
 * 	grid[n - 1][n - 1] != -1
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/cherry-pickup
 * @title: 摘樱桃
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"cherryPickup","in.txt");
    }


    // https://leetcode.cn/problems/cherry-pickup/submissions/582594109/
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
        return Math.max(0,dfs(0,0,0,0));
    }


    int[][][][] memo;
    int inf = 1 << 30,m,n,grid[][];



    // 两个题差不多题目数量量不大 ，因此可以使用记忆化搜索 就是修改一下 dir搜索方向
    static  int[][] dirs = {{1,0},{0,1}};
    boolean check(int i,int j) {
        return 0 <= i && i < m && 0 <= j && j < n && grid[i][j] != -1;
    }


    int dfs(int i,int j,int i0,int j0) {
        if((!check(i,j) || !check(i0,j0))){
            return -inf;
        }
        if(i == m - 1 && j == n - 1){
            return grid[i][j];
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