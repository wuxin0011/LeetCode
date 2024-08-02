package leetcode._0x3f_.grid_diagram.dfs.Solution_0003;

import code_generation.utils.IoUtil;

/**
 *
 *
 * 463. 岛屿的周长
 *
 * 给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。
 * 网格中的格子 水平和垂直 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 10^0 。计算这个岛屿的周长。
 *
 * 示例 1：
 * 输入：grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
 * 输出：16
 * 解释：它的周长是上面图片中的 16 个黄色的边
 *
 * 示例 2：
 * 输入：grid = [[1]]
 * 输出：4
 *
 * 示例 3：
 * 输入：grid = [[1,0]]
 * 输出：4
 *
 * 提示：
 * 	row == grid.length
 * 	col == grid[i].length
 * 	1 <= row, col <= 10^0
 * 	grid[i][j] 为 0 或 1
 *
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/island-perimeter
 * @title: 岛屿的周长
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"islandPerimeter","in.txt");
    }
     

    public int islandPerimeter(int[][] grid) {

        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j]==1){
                    ans += 4;
                    if(i>0 && grid[i-1][j]==1){
                        ans--;
                    }
                    if(i+1<grid.length && grid[i+1][j]==1) {
                        ans--;
                    }
                    if(j>0 && grid[i][j-1]==1){
                        ans--;
                    }
                    if(j+1<grid[0].length && grid[i][j+1]==1){
                        ans--;
                    }
                }
            }
        }
        return ans;
    }

  

}