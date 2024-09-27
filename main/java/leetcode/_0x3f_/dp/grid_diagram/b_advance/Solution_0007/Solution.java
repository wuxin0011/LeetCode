package leetcode._0x3f_.dp.grid_diagram.b_advance.Solution_0007;

import code_generation.utils.IoUtil;
import leetcode.contest.template.t1.b.B;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/check-if-there-is-a-valid-parentheses-string-path
 * @title: 检查是否有合法括号字符串路径
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "hasValidPath", "in.txt");
    }


    public boolean hasValidPath(char[][] g) {

        this.m = g.length;
        this.n = g[0].length;
        this.g = g;

        if ((m + n - 1) % 2 != 0 || g[0][0] == ')' || g[m - 1][n - 1] == '(') {
            return false;
        }
        memo = new Boolean[m + 1][n + 1][m + n];

        return dfs(0, 0, 0);
    }

    int m, n;
    char[][] g;
    Boolean[][][] memo;


    public boolean dfs(int i, int j, int deep) {
        if (i >= m || j >= n || deep < 0 || deep > (m - i + n - j - 1)) {
            return false;
        }
        if (i == m - 1 && j == n - 1) {
            return deep == 1;
        }
        if(memo[i][j][deep] != null){
            return memo[i][j][deep];
        }
        boolean ans;
        if (g[i][j] == '(') {
            ans = dfs(i + 1, j, deep + 1) || dfs(i, j + 1, deep + 1);
        } else {
            ans = dfs(i + 1, j, deep - 1) || dfs(i, j + 1, deep - 1);
        }
        return memo[i][j][deep] =  ans;
    }


}