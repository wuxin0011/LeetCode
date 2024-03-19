package leetcode.everyday;

import leetcode.utils.IoUtil;

import java.util.ArrayDeque;
import java.util.Deque;


/**
 * @author: wuxin0011
 * @Description:
 * @title
 * @url https://leetcode.cn/problems/maximum-number-of-moves-in-a-grid/description/
 */
@SuppressWarnings("all")
public class Code_0020_310 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0020_310.class, "maxMoves", "./txt_file/Code_0020_310.txt");
    }

    int[][] dirs = {{-1,1},{0,1},{1,1}};
    public int maxMoves(int[][] grid) {
        int m = grid.length,n = grid[0].length;
        // 三个单元格中任一满足值 严格 大于当前单元格的单元格。
        boolean f = false;
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] vis = new boolean[m][n];
        // 第一列入队
        for(int i = 0;i<m;i++){
            q.add(new int[]{i,0});
        }
        int cnt = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(;size>0;size--){
                int[] p = q.poll();
                for(int k = 0;k<dirs.length;k++){
                    int y = dirs[k][1] + p[1],x = dirs[k][0] + p[0];
                    if(x >=0 && x<m && y>=0 && y<n && !vis[x][y] && grid[x][y] > grid[p[0]][p[1]]){
                        vis[x][y] = true;
                        q.add(new int[]{x,y});
                    }
                }
            }
            if(!q.isEmpty()) cnt++;
        }
        return cnt;
    }

}
