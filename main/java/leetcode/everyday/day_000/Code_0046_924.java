package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 * 924. 尽量减少恶意软件的传播
 * 给出了一个由 n 个节点组成的网络，用 n × n 个邻接矩阵图graph表示。在节点网络中，
 * 当 graph[i][j] = 1时，表示节点i能够直接连接到另一个节点 j。
 * 一些节点initial最初被恶意软件感染。只要两个节点直接连接，且其中至少一个节点受到恶意软件的感染，
 * 那么两个节点都将被恶意软件感染。这种恶意软件的传播将继续，直到没有更多的节点可以被这种方式感染。
 * 假设 M(initial) 是在恶意软件停止传播之后，整个网络中感染恶意软件的最终节点数。
 * 如果从initial中移除某一节点能够最小化 M(initial)， 返回该节点。如果有多个节点满足条件
 * 就返回索引最小的节点。请注意，如果某个节点已从受感染节点的列表 initial 中删除，
 * 它以后仍有可能因恶意软件传播而受到感染。
 * 
 * 示例 1：输入：graph = [[1,1,0],[1,1,0],[0,0,1]], initial = [0,1]输出：0
 * 示例 2：输入：graph = [[1,0,0],[0,1,0],[0,0,1]], initial = [0,2]输出：0
 * 示例 3：输入：graph = [[1,1,1],[1,1,1],[1,1,1]], initial = [1,2]输出：1
 *
 * 提示：
 *
 *
 *
 * 	n == graph.length
 * 	n == graph[i].length
 * 	2 <= n <= 300
 * 	graph[i][j]==0或1.
 * 	graph[i][j] == graph[j][i]
 * 	graph[i][i] == 1
 * 	1 <= initial.length <= n
 * 	0 <= initial[i] <= n - 1
 * 	initial中所有整数均不重复
 *
 *
 * @author: wuxin0011
 * @Description: 并查集
 * @url:   https://leetcode.cn/problems/minimize-malware-spread
 * @title: 尽量减少恶意软件的传播
 */
public class Code_0046_924 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0046_924.class,"minMalwareSpread","txt_file\\Code_0046_924.txt");
    }

    private int nodeId, size;
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        boolean[] vis = new boolean[n];
        boolean[] isInitial = new boolean[n];
        int mn = Integer.MAX_VALUE;
        for (int x : initial) {
            isInitial[x] = true;
            mn = Math.min(mn, x);
        }

        int ans = -1;
        int maxSize = 0;
        // 找到感染的联动块
        for (int x : initial) {
            if (vis[x]) {
                continue;
            }
            nodeId = -1;
            size = 0;
            dfs(x, graph, vis, isInitial);
            // size > maxSize 找到感染数最多的
            // size == maxSize && nodeId < ans 感染数量一致 找到节点Id最小
            if (nodeId >= 0 && (size > maxSize || size == maxSize && nodeId < ans)) {
                ans = nodeId;
                maxSize = size;
            }
        }
        return ans < 0 ? mn : ans;
    }

    private void dfs(int x, int[][] graph, boolean[] vis, boolean[] isInitial) {
        vis[x] = true;
        size++;
        // 按照状态机更新 nodeId
        if (nodeId != -2 && isInitial[x]) {
            nodeId = nodeId == -1 ? x : -2;
        }
        for (int y = 0; y < graph[x].length; y++) {
            if (graph[x][y] == 1 && !vis[y]) {
                dfs(y, graph, vis, isInitial);
            }
        }
    }
  

}