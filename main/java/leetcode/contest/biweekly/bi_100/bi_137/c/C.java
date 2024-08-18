package leetcode.contest.biweekly.bi_100.bi_137.c;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/biweekly-contest-137/problems/maximum-value-sum-by-placing-three-rooks-i
 * @title: 放三个车的价值之和最大 I
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "maximumValueSum", "C.txt");
    }


    Node[][] nodes;


    static class Node {
        public int r, c;
        public int val;

        Node(int r, int c, int val) {
            this.r = r;
            this.c = c;
            this.val = val;
        }
    }

    public long maximumValueSum(int[][] board) {
        this.m = board.length;
        this.n = board[0].length;
        nodes = new Node[m][3];
        for (int i = 0; i < m; i++) {
            Node[] nodes0 = new Node[n];
            for (int j = 0; j < n; j++) {
                nodes0[j] = new Node(i, j, board[i][j]);
            }
            Arrays.sort(nodes0, (node1, node2) -> node2.val - node1.val);
            for (int k = 0; k < 3 && k < n; k++) {
                nodes[i][k] = nodes0[k];
            }
        }
        return dfs(0, 3);
    }

    int[][] memo;
    static int inf = Integer.MAX_VALUE; 
    boolean[] vis = new boolean[1001];
    int m, n;

    public int dfs(int i, int cnt) {
        if (cnt == 0) {
            return 0;
        }
        if (i == m) {
            return -inf;
        }

        int ans = -inf;
        ans = Math.max(ans, dfs(i + 1, cnt));
        for (Node node : nodes[i]) {
            int val = node.val;
            int col = node.c;
            if (vis[col]) continue;
            vis[col] = true;
            ans = Math.max(ans, dfs(i + 1, cnt - 1) + val);
            vis[col] = false;
        }
        return ans;
    }


}