package leetcode._0x3f_.bitwise_operations.and_or.and_or_0007;

import code_generation.utils.IoUtil;

import java.util.HashSet;

/**
 * 898. 子数组按位或操作
 * <p>
 * 我们有一个非负整数数组arr。
 * 对于每个（连续的）子数组sub = [arr[i], arr[i + 1], ..., arr[j]]（i <= j），我们对sub中的每个元素进行按位或操作，获得结果arr[i] | arr[i + 1] | ... | arr[j]。
 * 返回可能结果的数量。 多次出现的结果在最终答案中仅计算一次。
 * <p>
 * 示例 1：
 * 输入：arr = [0]
 * 输出：1
 * 解释：
 * 只有一个可能的结果 0 。
 * <p>
 * 示例 2：
 * 输入：arr = [1,1,2]
 * 输出：3
 * 解释：
 * 可能的子数组为 [1]，[1]，[2]，[1, 1]，[1, 2]，[1, 1, 2]。
 * 产生的结果为 1，1，2，1，3，3 。
 * 有三个唯一值，所以答案是 3 。
 * <p>
 * 示例3：
 * 输入：arr = [1,2,4]
 * 输出：6
 * 解释：
 * 可能的结果是 1，2，3，4，6，以及 7 。
 * <p>
 * 提示：
 * 1 <= nums.length <= 5 * 10^4
 * 0 <= nums[i]<= 10^9
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/bitwise-ors-of-subarrays
 * @title: 子数组按位或操作
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "subarrayBitwiseORs", "in.txt");
    }


    public int subarrayBitwiseORs(int[] nums) {
        int mx = 0;
        int zero = 0;
        int tot = 0;
        for (int num : nums) {
            mx = Math.max(mx, num);
            if (num == 0) zero++;
            tot |= num;
        }
        if (mx == 0) {
            return 1;
        }
        if (mx == 1) {
            return zero == 0 ? 1 : 2;
        }
        int mask = 1;
        while (mask < mx) {
            mask <<= 1;
        }
        mask--;
        HashSet<Integer> set = new HashSet<>();
        set.add(mx);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i, v = 0; j >= 0; j--) {
                v |= nums[j];
                set.add(v);
                if (v == tot || v == mask) break;
            }
        }
        return set.size();
    }


}