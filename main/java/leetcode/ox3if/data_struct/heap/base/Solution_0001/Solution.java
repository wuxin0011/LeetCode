package leetcode.ox3if.data_struct.heap.base.Solution_0001;

import code_generation.utils.IoUtil;

import java.util.PriorityQueue;
/**
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/take-gifts-from-the-richest-pile
 * @title: 从数量最多的堆取走礼物
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"pickGifts","in.txt");
    }


    public long pickGifts(int[] gifts, int k) {

        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int gift : gifts) {
            queue.add(gift);
        }
        while (k > 0 && !queue.isEmpty()) {
            int cur = queue.poll();
            int rest = (int) Math.floor(Math.sqrt(cur * 1.0));
            queue.add(rest);
            k--;
        }
        long tot = 0;
        while (!queue.isEmpty()) {
            tot += queue.poll();
        }
        return tot;
    }

  

}