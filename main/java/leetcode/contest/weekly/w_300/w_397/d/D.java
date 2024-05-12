package leetcode.contest.weekly.w_300.w_397.d;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 100312. 找出分数最低的排列
 * <p>
 * <p>
 * 给你一个数组 nums ，它是 [0, 1, 2, ..., n - 1] 的一个排列 。
 * 对于任意一个 [0, 1, 2, ..., n - 1] 的排列 perm ，其 分数 定义为：
 * score(perm) = |perm[0] - nums[perm[1]]| + |perm[1] - nums[perm[2]]| + ... + |perm[n - 1] - nums[perm[0]]|
 * 返回具有 最低 分数的排列 perm 。如果存在多个满足题意且分数相等的排列，则返回其中字典序最小的一个。
 * <p>
 * 示例 1：
 * 输入：nums = [1,0,2]
 * 输出：[0,1,2]
 * 解释：
 * 字典序最小且分数最低的排列是 [0,1,2]。这个排列的分数是 |0 - 0| + |1 - 2| + |2 - 1| = 2 。
 * <p>
 * 示例 2：
 * 输入：nums = [0,2,1]
 * 输出：[0,2,1]
 * 解释：
 * 字典序最小且分数最低的排列是 [0,2,1]。这个排列的分数是 |0 - 1| + |2 - 2| + |1 - 0| = 2 。
 * <p>
 * 提示：
 * 2 <= n == nums.length <= 14
 * nums 是 [0, 1, 2, ..., n - 1] 的一个排列。
 *
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-397/problems/find-the-minimum-cost-array-permutation
 * @title: 找出分数最低的排列
 */
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "findPermutation", "D.txt");
    }


    public int[] findPermutation(int[] nums) {

        // score(perm) = |perm[0] - nums[perm[1]]| + |perm[1] - nums[perm[2]]| + ... + |perm[n - 1] - nums[perm[0]]|
        int[] map = new int[15];
        this.nums = nums;
        this.map = map;
        for (int num : nums) {
            map[num]++;
        }
        // 2 <= n == nums.length <= 14
        int[] path = new int[nums.length];
        dfs(1, path, 0);

//        System.out.println("nums = " + Arrays.toString(nums));
//        System.out.println("prem = " + Arrays.toString(prem));
//        for (int[] l : ls) {
//            System.out.println(Arrays.toString(l));
//        }
        return prem;
    }

    int[] prem;
    int[] nums;
    int[] map;

    List<int[]> ls = new ArrayList<>();

    long mi = Integer.MAX_VALUE;

    public void dfs(int i, int[] path, long sc) {
        if (sc > mi) {
            return;
        }
        //
        if (i == path.length) {
            // ls.add(Arrays.copyOf(path, path.length));
            int[] t = Arrays.copyOf(path, path.length);
            if (prem == null) {
                prem = t;
            } else {
                if (check(prem,t)) {
                    prem = t; // update small
                }
            }
            mi = sc;
            return;
        }
        for (int k = 0; k < map.length; k++) {
            if (map[k] == 0) {
                continue;
            }
            map[k]--;
            path[i] = k;
            if (i == path.length - 1) {
                sc += Math.abs(nums[path[0]] - k);
            } else {
                if(i>0) {
                    sc += Math.abs(nums[k] - path[i - 1]);
                }

            }
            dfs(i + 1, path, sc);
            map[k]++;
        }

    }

    // a > b ?
    public static boolean check(int[] a, int[] b) {
        int i = 0;
        while (i < a.length) {
            if (a[i] > b[i]) {
                return true;
            }
            i++;
        }
        return false;
    }


}