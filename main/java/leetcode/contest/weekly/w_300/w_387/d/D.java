package leetcode.contest.weekly.w_300.w_387.d;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: wuxin0011
 * @url https://leetcode.cn/problems/distribute-elements-into-two-arrays-ii/
 * @title 将元素分配到两个数组中 II
 * @Description:
 */
public class D {
    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "resultArray", "in.txt");
    }



    private static class Fenwick {
        private final int[] tree;

        public Fenwick(int n) {
            tree = new int[n];
        }

        // 把下标为 i 的元素增加 1
        public void add(int i) {
            while (i < tree.length) {
                tree[i] += 1;
                i += i & -i;
            }
        }

        // 返回下标在 [1,i] 的元素之和
        public int pre(int i) {
            int res = 0;
            while (i > 0) {
                res += tree[i];
                i &= i - 1;
            }
            return res;
        }
    }

    public int[] resultArray(int[] nums) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted); // 只排序不去重
        int n = nums.length;

        List<Integer> a = new ArrayList<>(n); // 预分配空间
        List<Integer> b = new ArrayList<>();
        a.add(nums[0]);
        b.add(nums[1]);

        Fenwick t1 = new Fenwick(n + 1);
        Fenwick t2 = new Fenwick(n + 1);
        t1.add(Arrays.binarySearch(sorted, nums[0]) + 1);
        t2.add(Arrays.binarySearch(sorted, nums[1]) + 1);

        for (int i = 2; i < nums.length; i++) {
            int x = nums[i];
            int v = Arrays.binarySearch(sorted, x) + 1;
            int gc1 = a.size() - t1.pre(v); // greaterCount(a, v)
            int gc2 = b.size() - t2.pre(v); // greaterCount(b, v)
            if (gc1 > gc2 || gc1 == gc2 && a.size() <= b.size()) {
                a.add(x);
                t1.add(v);
            } else {
                b.add(x);
                t2.add(v);
            }
        }
        a.addAll(b);
        for (int i = 0; i < n; i++) {
            nums[i] = a.get(i);
        }
        return nums;
    }
}
