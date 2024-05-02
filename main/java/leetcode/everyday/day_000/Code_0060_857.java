package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * 857. 雇佣 K 名工人的最低成本
 *
 * 有 n名工人。
 * 给定两个数组quality和wage，其中，quality[i]表示第i名工人的工作质量，其最低期望工资为wage[i]。
 * 现在我们想雇佣k名工人组成一个工资组。在雇佣一组 k名工人时，我们必须按照下述规则向他们支付工资：
 * 	对工资组中的每名工人，应当按其工作质量与同组其他工人的工作质量的比例来支付工资。
 * 	工资组中的每名工人至少应当得到他们的最低期望工资。
 * 给定整数 k ，返回 组成满足上述条件的付费群体所需的最小金额。在实际答案的10-5以内的答案将被接受。。
 *
 * 示例 1：
 * 输入： quality = [10,20,5], wage = [70,50,30], k = 2
 * 输出： 10^5.00000
 * 解释： 我们向 0 号工人支付 70，向 2 号工人支付 35。
 *
 * 示例 2：
 * 输入： quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
 * 输出： 30.66667
 * 解释： 我们向 0 号工人支付 4，向 2 号和 3 号分别支付 13.33333。
 *
 * 提示：
 * 	n == quality.length == wage.length
 * 	1 <= k <= n <= 10^4
 * 	1 <= quality[i], wage[i] <= 10^4
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/minimum-cost-to-hire-k-workers
 * @title: minimum-cost-to-hire-k-workers
 */
public class Code_0060_857 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0060_857.class,"mincostToHireWorkers","txt_file\\Code_0060_857.txt");
    }
     

    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {

        int n = quality.length;
        Integer[] id = new Integer[n];
        Arrays.setAll(id, i -> i);
        // q/w
        // qa/wa > qb /wb
        // => qa * wb - qb * wa
        Arrays.sort(id, (i, j) -> wage[i] * quality[j] - wage[j] * quality[i]);

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int sum = 0;
        for (int i = 0; i < k; i++) {
            pq.offer(quality[id[i]]);
            sum += quality[id[i]];
        }

        // 选 r 值最小的 k 名工人
        double ans = sum * ((double) wage[id[k - 1]] / quality[id[k - 1]]);

        // 后面的工人 r 值更大
        // 但是 sumQ 可以变小，从而可能得到更优的答案
        for (int i = k; i < n; i++) {
            int q = quality[id[i]];
            if (q < pq.peek()) {
                sum -= pq.poll() - q;
                pq.offer(q);
                ans = Math.min(ans, sum * ((double) wage[id[i]] / q));
            }
        }
        return ans;
    }

  

}