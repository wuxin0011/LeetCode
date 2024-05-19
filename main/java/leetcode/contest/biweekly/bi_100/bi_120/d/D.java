package leetcode.contest.biweekly.bi_100.bi_120.d;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 * 2973. 树中每个节点放置的金币数目
 *
 * 给你一棵n个节点的无向树，节点编号为0到n - 1，树的根节点在节点0处。
 * 同时给你一个长度为 n - 1的二维整数数组edges，其中edges[i] = [ai, bi]表示树中节点ai 和bi之间有一条边。
 * 给你一个长度为 n下标从 0开始的整数数组cost，其中cost[i]是第 i个节点的 开销。
 * 你需要在树中每个节点都放置金币，在节点 i处的金币数目计算方法如下：
 * 	如果节点 i对应的子树中的节点数目小于3，那么放1个金币。
 * 	否则，计算节点 i 对应的子树内 3 个不同节点的开销乘积的 最大值 ，并在节点 i 处放置对应数目的金币。如果最大乘积是 负数，那么放置 0个金币。
 * 请你返回一个长度为 n的数组coin，coin[i]是节点i处的金币数目。
 *
 * 示例 1：
 * 输入：edges = [[0,1],[0,2],[0,3],[0,4],[0,5]], cost = [1,2,3,4,5,6]
 * 输出：[120,1,1,1,1,1]
 * 解释：在节点 0 处放置 6 * 5 * 4 = 120 个金币。所有其他节点都是叶子节点，子树中只有 1 个节点，所以其他每个节点都放 1 个金币。
 *
 * 示例 2：
 * 输入：edges = [[0,1],[0,2],[1,3],[1,4],[1,5],[2,6],[2,7],[2,8]], cost = [1,4,2,3,5,7,8,-4,2]
 * 输出：[280,140,32,1,1,1,1,1,1]
 * 解释：每个节点放置的金币数分别为：
 * - 节点 0 处放置 8 * 7 * 5 = 280 个金币。
 * - 节点 1 处放置 7 * 5 * 4 = 140 个金币。
 * - 节点 2 处放置 8 * 2 * 2 = 32 个金币。
 * - 其他节点都是叶子节点，子树内节点数目为 1 ，所以其他每个节点都放 1 个金币。
 *
 * 示例 3：
 * 输入：edges = [[0,1],[0,2]], cost = [1,2,-2]
 * 输出：[0,1,1]
 * 解释：节点 1 和 2 都是叶子节点，子树内节点数目为 1 ，各放置 1 个金币。节点 0 处唯一的开销乘积是 2 * 1 * -2 = -4 。所以在节点 0 处放置 0 个金币。
 *
 * 提示：
 * 	2 <= n <= 2 * 10^4
 * 	edges.length == n - 1
 * 	edges[i].length == 2
 * 	0 <= ai, bi < n
 * 	cost.length == n
 * 	1 <= |cost[i]| <= 10^4
 * 	edges一定是一棵合法的树。
 *
 * @author: agitated-curranfnd
 * @Description:
 * @url:   https://leetcode.cn/contest/biweekly-contest-120/problems/find-number-of-coins-to-place-in-tree-nodes
 * @title: 树中每个节点放置的金币数目
 */
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class,"placedCoins","D.txt");
    }
     

    public long[] placedCoins(int[][] edges, int[] cost) {    

        return null; 
	}

  

}