package leetcode.ox3if.data_struct.heap.base.Solution_0002;

import code_generation.contest.ParseCodeInfo;
import code_generation.utils.IoUtil;

import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/smallest-number-in-infinite-set
 * @title: 无限集中的最小数字
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(SmallestInfiniteSet.class,ParseCodeInfo.ConstructorClass,"in.txt");
    }
     

    public static class SmallestInfiniteSet {

        private TreeSet<Integer> set;
        public SmallestInfiniteSet() {
            set = new TreeSet<Integer>((a,b)->a-b);
            for (int i = 1; i <= 1001; i++) {
                set.add(i);
            }
        }

        public int popSmallest() {
            int ans = set.first();
            set.remove(ans);
            return ans;
        }

        public void addBack(int num) {
            set.add(num);
        }


    }

}