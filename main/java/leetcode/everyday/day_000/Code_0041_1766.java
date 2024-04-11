package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 * 1766. 互质树
 *
 * 给你一个 n个节点的树（也就是一个无环连通无向图），节点编号从 0到 n - 1，且恰好有 n - 1条边，每个节点有一个值。树的 根节点为 0 号点。给你一个整数数组nums和一个二维数组edges来表示这棵树。nums[i]表示第i个点的值，edges[j] = [uj, vj]表示节点uj和节点vj在树中有一条边。当gcd(x, y) == 1，我们称两个数x 和y是 互质的，其中gcd(x, y)是 x和 y的 最大公约数。从节点i到 根最短路径上的点都是节点 i的祖先节点。一个节点 不是 它自己的祖先节点。请你返回一个大小为 n的数组 ans，其中ans[i]是离节点i最近的祖先节点且满足nums[i] 和nums[ans[i]]是 互质的，如果不存在这样的祖先节点，ans[i]为 -1。
 *
 * 示例 1：输入：nums = [2,3,3,2], edges = [[0,1],[1,2],[1,3]]输出：[-1,0,0,1]解释：上图中，每个节点的值在括号中表示。- 节点 0 没有互质祖先。- 节点 1 只有一个祖先节点 0 。它们的值是互质的（gcd(2,3) == 1）。- 节点 2 有两个祖先节点，分别是节点 1 和节点 0 。节点 1 的值与它的值不是互质的（gcd(3,3) == 3）但节点 0 的值是互质的(gcd(2,3) == 1)，所以节点 0 是最近的符合要求的祖先节点。- 节点 3 有两个祖先节点，分别是节点 1 和节点 0 。它与节点 1 互质（gcd(3,2) == 1），所以节点 1 是离它最近的符合要求的祖先节点。
 *
 * 示例 2：输入：nums = [5,6,10,2,3,6,15], edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]]输出：[-1,0,-1,0,0,0,-1]
 *
 * 提示：
 *
 *
 * 	nums.length == n
 * 	1 <= nums[i] <= 50
 * 	1 <= n <= 105
 * 	edges.length == n - 1
 * 	edges[j].length == 2
 * 	0 <= uj, vj < n
 * 	uj != vj
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/tree-of-coprimes
 * @title: 互质树
 */
public class Code_0041_1766 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0041_1766.class,"getCoprimes","txt_file\\Code_0041_1766.txt");
    }
     

    public int[] getCoprimes(int[] nums, int[][] edges) {    

        return null; 
	}

  

}