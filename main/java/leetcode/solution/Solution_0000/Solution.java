package leetcode.solution.Solution_0000;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/minimum-time-to-visit-a-cell-in-a-grid
 * @title: minimum-time-to-visit-a-cell-in-a-grid
 */
public class Solution {


    public static void main(String[] args) {
        System.out.println(new Solution().getPermutation(4, 2));;
    }

    char[] map = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    char[] path;
    String ans;
    int l, k, n;

    public String getPermutation(int n, int k) {
        this.n = n;
        path = new char[n];
        this.k = k;
        l = 0;
        dfs(0);
        return ans;
    }

    public void dfs(int mask) {
        if (l == n) {
            k--;
            if (k == 0) {
                ans = new String(path);
            }
        }
        for (int i = 1; i <= n && k > 0; i++) {
            if ((mask >> i & 1) == 1) {
                continue;
            }
            path[l++] = map[i];
            dfs(mask | 1 << i);
            l--;
        }
    }
}
