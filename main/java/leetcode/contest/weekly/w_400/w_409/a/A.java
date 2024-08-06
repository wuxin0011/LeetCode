package leetcode.contest.weekly.w_400.w_409.a;

import code_generation.contest.ParseCodeInfo;
import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-409/problems/design-neighbor-sum-service
 * @title: 设计相邻元素求和服务
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(neighborSum.class, ParseCodeInfo.ConstructorClass, "A.txt");
    }


    public static class neighborSum {

        Map<Integer, int[]> map = new HashMap<>();
        int[][] grid;
        int m, n;

        public neighborSum(int[][] grid) {
            this.grid = grid;
            m = grid.length;
            n = grid[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    map.put(grid[i][j], new int[]{i, j});
                }
            }
        }

        public int adjacentSum(int value) {
            int top = 0, bottom = 0, right = 0, left = 0;
            int[][] dir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
            int x = map.get(value)[0], y = map.get(value)[1];
            int s = 0;
            for (int i = 0; i < 4; i++) {
                int x0 = x + dir[i][0];
                int y0 = y + dir[i][1];
                if (x0 < 0 || x0 >= m || y0 < 0 || y0 >= n) {
                    continue;
                }
                s += grid[x0][y0];
            }
            return s;
        }

        public int diagonalSum(int value) {
            int top = 0, bottom = 0, right = 0, left = 0;
            int[][] dirs = {
                    {-1, -1}, {-1, 1}
                    ,
                    {1, -1}, {1, 1},
            };
            int x = map.get(value)[0], y = map.get(value)[1];
            int s = 0;

            for (int[] dir : dirs) {
                int x0 = x + dir[0];
                int y0 = y + dir[1];
                if (x0 < 0 || x0 >= m || y0 < 0 || y0 >= n) {
                    continue;
                }
                s += grid[x0][y0];
            }
            return s;
        }


    }

}