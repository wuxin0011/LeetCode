package leetcode.ox3if.dp.state_compression.adjacent_unrelated.adjacent_unrelated_0002;

import code_generation.utils.IoUtil;

/**
 *
 *
 * 2850. 将石头分散到网格图的最少移动次数
 *
 * 给你一个大小为 3 * 3，下标从 0开始的二维整数矩阵grid，分别表示每一个格子里石头的数目。
 * 网格图中总共恰好有9个石头，一个格子里可能会有 多个石头。
 * 每一次操作中，你可以将一个石头从它当前所在格子移动到一个至少有一条公共边的相邻格子。
 * 请你返回每个格子恰好有一个石头的 最少移动次数。
 *
 * 示例 1：
 * 输入：grid = [[1,1,0],[1,1,1],[1,2,1]]
 * 输出：3
 * 解释：让每个格子都有一个石头的一个操作序列为：
 * 1 - 将一个石头从格子 (2,1) 移动到 (2,2) 。
 * 2 - 将一个石头从格子 (2,2) 移动到 (1,2) 。
 * 3 - 将一个石头从格子 (1,2) 移动到 (0,2) 。
 * 总共需要 3 次操作让每个格子都有一个石头。
 * 让每个格子都有一个石头的最少操作次数为 3 。
 *
 * 示例 2：
 * 输入：grid = [[1,3,0],[1,0,0],[1,0,3]]
 * 输出：4
 * 解释：让每个格子都有一个石头的一个操作序列为：
 * 1 - 将一个石头从格子 (0,1) 移动到 (0,2) 。
 * 2 - 将一个石头从格子 (0,1) 移动到 (1,1) 。
 * 3 - 将一个石头从格子 (2,2) 移动到 (1,2) 。
 * 4 - 将一个石头从格子 (2,2) 移动到 (2,1) 。
 * 总共需要 4 次操作让每个格子都有一个石头。
 * 让每个格子都有一个石头的最少操作次数为 4 。
 *
 * 提示：
 * 	grid.length == grid[i].length == 3
 * 	0 <= grid[i][j] <= 9
 * 	grid中元素之和为9 。
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/minimum-moves-to-spread-stones-over-grid
 * @title: 将石头分散到网格图的最少移动次数
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"minimumMoves","in.txt");
    }
     

    public int minimumMoves(int[][] grid) {    

        return 0; 
	}

  

}