package leetcode._0x3f_.data_struct.heap.base.Solution_0015;

import code_generation.utils.IoUtil;

import java.util.PriorityQueue;
/**
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/maximum-spending-after-buying-items
 * @title: 购买物品的最大开销
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"maxSpending","in.txt");
    }
     

    public long maxSpending(int[][] values) {
        // {i1,j1,val1}
        // {i2,j2,val2}

        // i1 == i2 ? j1 - j2 : val2 *
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : b[2] - a[2]);
        int m = values.length, n = values[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                q.add(new int[]{i, j, values[i][j]});
            }
        }
        long day = (long) m * n;
        long tot = 0;
        while (day > 0 && !q.isEmpty()) {
            tot += day * q.poll()[2];
            day--;
        }
        return tot;
    }

  

}