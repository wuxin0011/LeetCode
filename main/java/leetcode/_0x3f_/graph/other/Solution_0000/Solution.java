package leetcode._0x3f_.graph.other.Solution_0000;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 *
 * 1697. 检查边长度限制的路径是否存在
 *
 * 给你一个 n个点组成的无向图边集edgeList，其中edgeList[i] = [ui, vi, disi]表示点ui 和点vi之间有一条长度为disi的边。
 * 请注意，两个点之间可能有 超过一条边。
 * 给你一个查询数组queries，其中queries[j] = [pj, qj, limitj]，你的任务是对于每个查询queries[j]，判断是否存在从pj到qj的路径，且这条路径上的每一条边都 严格小于limitj。
 * 请你返回一个 布尔数组answer，其中answer.length == queries.length，当queries[j]的查询结果为true时，answer 第j个值为true，否则为false。
 *
 * 示例 1：
 * 输入：n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
 * 输出：[false,true]
 * 解释：上图为给定的输入数据。注意到 0 和 1 之间有两条重边，分别为 2 和 16 。
 * 对于第一个查询，0 和 1 之间没有小于 2 的边，所以我们返回 false 。
 * 对于第二个查询，有一条路径（0 -> 1 -> 2）两条边都小于 5 ，所以这个查询我们返回 true 。
 *
 * 示例 2：
 * 输入：n = 5, edgeList = [[0,1,10],[1,2,5],[2,3,9],[3,4,13]], queries = [[0,4,14],[1,4,13]]
 * 输出：[true,false]
 * 解释：上图为给定数据。
 *
 * 提示：
 * 	2 <= n <= 10^5
 * 	1 <= edgeList.length, queries.length <= 10^5
 * 	edgeList[i].length == 3
 * 	queries[j].length == 3
 * 	0 <= ui, vi, pj, qj <= n - 1
 * 	ui != vi
 * 	pj != qj
 * 	1 <= disi, limitj <= 10^9
 * 	两个点之间可能有 多条边。
 *
 *
 * @author: wuxin0011
 * @Description: 最小生成树 + 并查集
 * @url:   https://leetcode.cn/problems/checking-existence-of-edge-length-limited-paths
 * @title: 检查边长度限制的路径是否存在
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"distanceLimitedPathsExist","in.txt");
    }
    public static int max = 100001;

    public static int[] fa = new int[max];

    public static int[][] q = new int[max][4];

    public static void build(int n) {
        for (int i = 0; i < n; i++) {
            fa[i] = i;
        }
    }

    public static int find(int i) {
        while (i != fa[i]) {
            fa[i] = fa[fa[i]];
            i = fa[i];
        }
        return fa[i];
    }

    public static boolean isSame(int x, int y) {
        return find(x) == find(y);
    }

    public static void union(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if(fx != fy) {
            fa[fy] = fx;
        }
    }

    public static boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {

        int m = edgeList.length;
        int k = queries.length;
        build(n);
        Arrays.sort(edgeList, (a, b) -> a[2] - b[2]);
        for (int i = 0; i < k; i++) {
            q[i][0] = queries[i][0];
            q[i][1] = queries[i][1];
            q[i][2] = queries[i][2];
            q[i][3] = i;
        }

        Arrays.sort(q, 0, k, (a, b) -> a[2] - b[2]);

        boolean[] ans = new boolean[k];
        for (int i = 0, j = 0; i < k; i++) {
            for (; j < m && edgeList[j][2] < q[i][2]; j++) {
                union(edgeList[j][0], edgeList[j][1]);
            }
            ans[q[i][3]] = isSame(q[i][0], q[i][1]);
        }
        return ans;

    }



}