package leetcode._0x3f_.data_struct.heap.base.Solution_0003;

import code_generation.utils.IoUtil;

import java.util.PriorityQueue;

/**
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/maximal-score-after-applying-k-operations
 * @title: maximal-score-after-applying-k-operations
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "maxKelements", "in.txt");
    }


    public long maxKelements(int[] nums, int k) {
        long ans = 0;
        PriorityQueue<int[]> queue  = new PriorityQueue<int[]>((a,b)->b[0]-a[0]);
        for (int i = 0; i < nums.length; i++) {
            queue.add(new int[]{nums[i],i});
        }
        while(k>0 && !queue.isEmpty()) {
            int[] p = queue.poll();
            ans += p[0];
            p[0] = ceil(p[0],3);
            queue.add(p);
            k--;
        }
        return ans;
    }


    public static int ceil(int a, int b) {
        if (a % b == 0) return a / b;
        return (a + b - 1) / b;
    }


}