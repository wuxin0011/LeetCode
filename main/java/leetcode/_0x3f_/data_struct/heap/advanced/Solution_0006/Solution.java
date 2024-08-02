package leetcode._0x3f_.data_struct.heap.advanced.Solution_0006;

import code_generation.utils.IoUtil;

import java.util.PriorityQueue;

/**
 * 1354. 多次求和构造目标数组
 * <p>
 * 给你一个整数数组target 。
 * 一开始，你有一个数组A ，它的所有元素均为 1 ，你可以执行以下操作：
 * 令x为你数组里所有元素的和
 * 选择满足0 <= i < target.size的任意下标i，并让A数组里下标为i处的值为x。
 * 你可以重复该过程任意次
 * 如果能从A开始构造出目标数组target，请你返回 True ，否则返回 False 。
 * <p>
 * 示例 1：
 * 输入：target = [9,3,5]
 * 输出：true
 * 解释：从 [1, 1, 1] 开始
 * [1, 1, 1], 和为 3 ，选择下标 1
 * [1, 3, 1], 和为 5， 选择下标 2
 * [1, 3, 5], 和为 9， 选择下标 0
 * [9, 3, 5] 完成
 * <p>
 * 示例 2：
 * 输入：target = [1,1,1,2]
 * 输出：false
 * 解释：不可能从 [1,1,1,1] 出发构造目标数组。
 * <p>
 * 示例 3：
 * 输入：target = [8,5]
 * 输出：true
 * <p>
 * 提示：
 * N == target.length
 * 1 <= target.length<= 5 * 10^4
 * 1 <= target[i] <= 10^9
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/construct-target-array-with-multiple-sums
 * @title: 多次求和构造目标数组
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "isPossible", "in.txt");
    }

    //[1,1]
    //[2,1]
    //[3,1]
    //[4,1]
    //[5,1]
    //

    public boolean isPossible(int[] target) {
        if (target.length == 1) {
            return true;
        }
        PriorityQueue<Long> pq = new PriorityQueue<>((a,b)->b.compareTo(a));
        long sum = 0;
        for (int i = 0; i < target.length; i++) {
            sum += target[i];
            pq.offer((long)target[i]);
        }
        //如果此时队列为空或者最大值就是1，直接return true
        if (pq.isEmpty() || pq.peek() == 1) {
            return true;
        }
        while (true) {
            //取出最大的那个
            Long poll = pq.poll();
            //如果此时堆中最大的为1
            if (!pq.isEmpty() && pq.peek() == 1) {
                //直接看它满足或不满足公式
                return (poll - 1) % (sum - poll) == 0;
            } else {
                //需要计算多少轮才能比第二小的数小
                long n = (poll - pq.peek()) / (sum - poll) + 1;
                //得到这个数字
                long x = poll - n * (sum - poll);
                if (x <= 0) {
                    return false;
                }
                //更新sum
                sum = poll - (sum - poll) * (n - 1);
                pq.offer(x);
            }
        }

    }


}