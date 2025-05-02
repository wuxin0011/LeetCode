package leetcode.contest.weekly.w_400.w_447;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 3534. 针对图的路径存在性查询 II
 * <p>
 * <p>
 * 给你一个整数 n，表示图中的节点数量，这些节点按从 0 到 n - 1编号。
 * 同时给你一个长度为 n 的整数数组 nums，以及一个整数 maxDiff。
 * 如果满足 |nums[i] - nums[j]| <= maxDiff（即 nums[i] 和 nums[j] 的绝对差至多为 maxDiff），则节点 i 和节点 j 之间存在一条无向边。
 * 此外，给你一个二维整数数组 queries。对于每个 queries[i] = [ui, vi]，找到节点 ui 和节点 vi 之间的最短距离。如果两节点之间不存在路径，则返回 -1。
 * 返回一个数组 answer，其中 answer[i] 是第 i 个查询的结果。
 * 注意：节点之间的边是无权重（unweighted）的。
 * <p>
 * 示例 1：
 * 输入: n = 5, nums = [1,8,3,4,2], maxDiff = 3, queries = [[0,3],[2,4]]
 * 输出: [1,1]
 * 解释:
 * 生成的图如下：
 * <p>
 * <p>
 * 查询
 * 最短路径
 * 最短距离
 * <p>
 * <p>
 * [0, 3]
 * 0 → 3
 * 1
 * <p>
 * <p>
 * [2, 4]
 * 2 → 4
 * 1
 * <p>
 * <p>
 * 因此，输出为 [1, 1]。
 * <p>
 * 示例 2：
 * 输入: n = 5, nums = [5,3,1,9,10], maxDiff = 2, queries = [[0,1],[0,2],[2,3],[4,3]]
 * 输出: [1,2,-1,1]
 * 解释:
 * 生成的图如下：
 * <p>
 * <p>
 * 查询
 * 最短路径
 * 最短距离
 * <p>
 * <p>
 * [0, 1]
 * 0 → 1
 * 1
 * <p>
 * <p>
 * [0, 2]
 * 0 → 1 → 2
 * 2
 * <p>
 * <p>
 * [2, 3]
 * 无
 * -1
 * <p>
 * <p>
 * [4, 3]
 * 3 → 4
 * 1
 * <p>
 * <p>
 * 因此，输出为 [1, 2, -1, 1]。
 * <p>
 * 示例 3：
 * 输入: n = 3, nums = [3,6,1], maxDiff = 1, queries = [[0,0],[0,1],[1,2]]
 * 输出: [0,-1,-1]
 * 解释:
 * 由于以下原因，任意两个节点之间都不存在边：
 * 节点 0 和节点 1：|nums[0] - nums[1]| = |3 - 6| = 3 > 1
 * 节点 0 和节点 2：|nums[0] - nums[2]| = |3 - 1| = 2 > 1
 * 节点 1 和节点 2：|nums[1] - nums[2]| = |6 - 1| = 5 > 1
 * 因此，不存在任何可以到达其他节点的节点，输出为 [0, -1, -1]。
 * <p>
 * 提示：
 * 1 <= n == nums.length <= 10^5
 * 0 <= nums[i] <= 10^5
 * 0 <= maxDiff <= 10^5
 * 1 <= queries.length <= 10^5
 * queries[i] == [ui, vi]
 * 0 <= ui, vi < n
 *
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/weekly-contest-447/problems/path-existence-queries-in-a-graph-ii">针对图的路径存在性查询 II</a>
 * @title: 针对图的路径存在性查询 II
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class,"pathExistenceQueries","D.txt");
    }
     

    public int[] pathExistenceQueries(int n, int[] a, int dis, int[][] q) {

        // 排序
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, i -> i);
        Arrays.sort(ids, (i,j)->a[i]-a[j]);

        int power = (int) (Math.log(n) / Math.log(2) + 1);
        int[][] jump = new int[n][power + 1];


        // 映射原来坐标
        int[] rk = new int[n];
        for (int i = 0; i < n; i++) {
            rk[ids[i]] = i;
        }



        for (int r = 0, l = 0; r < n; r++) {
            while (a[ids[r]] - a[ids[l]] > dis) l++;
            jump[r][0] = l;
        }

        // 倍增
        for (int j = 0; j < power - 1; j++) {
            for (int x = 0; x < n; x++) {
                int fa = jump[x][j];
                jump[x][j + 1] = jump[fa][j];
            }
        }

        int m = q.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int u = q[i][0], v = q[i][1];
            if (u==v) {
                ans[i] = 0;
            } else {
                int l = rk[u];
                int r = rk[v];
                if(l>r){
                    int temp=l;l=r;r=temp;
                }
                int res = 0;
                for (int j = power-1; j >= 0; j--) {
                    if (jump[r][j] > l) {
                        res |= 1 << j;
                        r = jump[r][j];
                    }
                }
                ans[i] = jump[r][0]>l ? -1 : (res + 1);
            }
        }
        return ans;
    }







}