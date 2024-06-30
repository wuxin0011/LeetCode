package leetcode.contest.weekly.w_400.w_404.d;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 * 100318. 合并两棵树后的最小直径
 *
 * 给你两棵 无向树，分别有n 和m个节点，节点编号分别为0到n - 1和0到m - 1。
 * 给你两个二维整数数组edges1 和edges2，长度分别为n - 1 和m - 1，其中edges1[i] = [ai, bi]表示在第一棵树中节点ai 和bi之间有一条边，edges2[i] = [ui, vi]表示在第二棵树中节点ui 和vi之间有一条边。
 * 你必须在第一棵树和第二棵树中分别选一个节点，并用一条边连接它们。
 * 请你返回添加边后得到的树中，最小直径为多少。
 * 一棵树的 直径指的是树中任意两个节点之间的最长路径长度。
 *
 * 示例 1：
 * 输入：edges1 = [[0,1],[0,2],[0,3]], edges2 = [[0,1]]
 * 输出：3
 * 解释：
 * 将第一棵树中的节点 0 与第二棵树中的任意节点连接，得到一棵直径为 3 得树。
 *
 * 示例 2：
 * 输入：edges1 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]], edges2 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]]
 * 输出：5
 * 解释：
 * 将第一棵树中的节点 0 和第二棵树中的节点 0 连接，可以得到一棵直径为 5 的树。
 *
 * 提示：
 * 	1 <= n, m <= 10^5
 * 	edges1.length == n - 1
 * 	edges2.length == m - 1
 * 	edges1[i].length == edges2[i].length == 2
 * 	edges1[i] = [ai, bi]
 * 	0 <= ai, bi < n
 * 	edges2[i] = [ui, vi]
 * 	0 <= ui, vi < m
 * 	输入保证edges1 和edges2分别表示一棵合法的树。
 *
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url  https://leetcode.cn/problems/find-minimum-diameter-after-merging-two-trees/description/
 * @title
 */
public class D {
    public static void main(String[] args) {
        IoUtil.testUtil(D.class,"minimumDiameterAfterMerge","D.txt");
    }

    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        return 0;
    }
}
