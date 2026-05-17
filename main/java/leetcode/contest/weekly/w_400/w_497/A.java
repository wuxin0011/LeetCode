package leetcode.contest.weekly.w_400.w_497;

import code_generation.utils.IoUtil;
import code_generation.annotation.TestCaseGroup;
import java.util.*;
/**
 * @author: qitongwei
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-497/problems/find-the-degree-of-each-vertex">统计每个顶点的度</a>
 * @title: 统计每个顶点的度
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"findDegrees","A.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    
    public int[] findDegrees(int[][] matrix) {
        int n = matrix.length;
        int[] ans = new int[n];
        for(int i = 0;i<n;i++) {
            int[] row = matrix[i];
            for(int j = 0;j<n;j++) {
                if(row[j] == 0) {
                    continue;
                }
                ans[i]++;
            }
        }
        return ans;
	}

  

}