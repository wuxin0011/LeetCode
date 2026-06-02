package leetcode.contest.biweekly.bi_100.bi_183;

import code_generation.utils.IoUtil;
import code_generation.annotation.TestCaseGroup;
import java.util.*;
/**
 * @author: qitongwei
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/biweekly-contest-183/problems/maximum-path-intersection-sum-in-a-grid">矩阵中最大共享路径和</a>
 * @title: 矩阵中最大共享路径和
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class,"maxScore","C.txt");
    }
    private static final int MOD = (int)1e9 + 7; 


    // 公共位置的最大和
    public int maxScore(int[][] grid) {
        int ans = Integer.MIN_VALUE;
        int m = grid.length,n = grid[0].length;

        // 为1
        for(int i = 1;i < m - 1;i++){
            for(int j = 1;j < n - 1;j++){
                ans=Math.max(ans,grid[i][j]);
            }
        }
        // 计算行
        for(int i = 0;i<m;i++){
            ans = Math.max(ans,calc(grid[i]));
        }

        // 计算列
        int[] temp = new int[m];
        for(int j = 0;j < n;j++){
            for(int i = 0;i < m;i++){
                temp[i] = grid[i][j];
            }
            ans = Math.max(ans,calc(temp));
        }
        return ans;
	}

    private static int calc(int[] a) {
        int n = a.length;
        int ans = Integer.MIN_VALUE;
        int s = a[0];
        for(int i = 1;i<n;i++){
            ans = Math.max(ans,s + a[i]);
            s = Math.max(s,0) + a[i];
        }
        return ans;
    }

  

}