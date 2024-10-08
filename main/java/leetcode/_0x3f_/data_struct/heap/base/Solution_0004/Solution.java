package leetcode._0x3f_.data_struct.heap.base.Solution_0004;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/minimum-operations-to-exceed-threshold-value-ii
 * @title: minimum-operations-to-exceed-threshold-value-ii
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"minOperations","in.txt");
    }
     

    public int minOperations(int[] nums, int k) {
        PriorityQueue<Long> queue = new PriorityQueue<>(Long::compare);
        for (int num : nums) {
            queue.add(num*1L);
        }
        int cnt = 0;
        while(queue.size()>=2) {
            if(queue.peek()>=k) {
                break;
            }
            long x = queue.poll();
            assert !queue.isEmpty();
            long y = queue.poll();
            long v = y + x * 2;
            queue.add(v);
            cnt++;
        }
        return cnt;
    }

  

}