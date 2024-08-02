package leetcode._0x3f_.data_struct.tree_array.Solution_0000;

import code_generation.contest.ParseCodeInfo;
import code_generation.utils.IoUtil;

/**
 * 树状数组 区间查询 区间范围内更新
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/range-sum-query-mutable
 * @title: range-sum-query-mutable
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(NumArray.class, ParseCodeInfo.ConstructorClass, "in.txt");
    }


    /**
     * @vis: https://leetcode.cn/problems/range-sum-query-mutable/solutions/2524481/dai-ni-fa-ming-shu-zhuang-shu-zu-fu-shu-lyfll/
     */
    public static class NumArray {
        private int[] nums;
        private int[] tree;

        public static int lowbit(int x) {
            return x & -x;
        }

        public NumArray(int[] nums) {
            int n = nums.length;
            this.nums = new int[n];
            tree = new int[n + 1];
            for (int i = 0; i < n; i++) {
                update(i, nums[i]);
            }
        }

        public void update(int index, int val) {
            int diff = val - nums[index];
            nums[index] = val;
            int i = index + 1;
            while (i < tree.length) {
                tree[i] += diff;
                i += lowbit(i);
            }
        }

        private int prefixSum(int i) {
            int s = 0;
            while (i > 0) {
                s += tree[i];
                i -= lowbit(i);
            }
            return s;
        }

        public int sumRange(int left, int right) {
            return prefixSum(right + 1) - prefixSum(left);
        }
    }
}