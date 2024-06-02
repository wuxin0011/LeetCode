package leetcode.contest.biweekly.bi_100.bi_131.a;

import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 3158. 求出出现两次数字的 XOR 值
 *
 *
 *               给你一个数组nums，数组中的数字 要么 出现一次，要么出现两次。
 * 请你返回数组中所有出现两次数字的按位XOR值，如果没有数字出现过两次，返回 0 。
 *
 * 示例 1：
 * 输入：nums = [1,2,1,3]
 * 输出：1
 * 解释：
 * nums中唯一出现过两次的数字是 1 。
 *
 * 示例 2：
 * 输入：nums = [1,2,3]
 * 输出：0
 * 解释：
 * nums中没有数字出现两次。
 *
 * 示例 3：
 * 输入：nums = [1,2,2,1]
 * 输出：3
 * 解释：
 * 数字 1 和2 出现过两次。1 XOR 2 == 3。
 *
 * 提示：
 * 	1 <= nums.length <= 50
 * 	1 <= nums[i] <= 50
 * 	nums中每个数字要么出现过一次，要么出现过两次。
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url https://leetcode.cn/contest/biweekly-contest-131/problems/find-the-xor-of-numbers-which-appear-twice/
 * @title
 */
public class A {
    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "duplicateNumbersXOR", "A.txt");
        // IoUtil.testUtil(A.class, "duplicateNumbersXOR1", "A.txt");
    }

    public int duplicateNumbersXOR(int[] nums) {
        int[] map = new int[51];
        int ans = 0;
        for (int num : nums) {
            map[num]++;
            if(map[num] ==2) {
                ans ^= num;
            }
        }
        return ans;
    }


    public int duplicateNumbersXOR1(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();
        boolean find = false;
        int ans = 0;
        for (int num : nums) {
            int cnt = map.getOrDefault(num, 0) + 1;
            map.put(num, cnt);
            if (cnt >= 2) {
                find = true;
                ans = num;
            }
        }
        if (!find) {
            return 0;
        }
        int val = ans;
        for (Map.Entry<Integer, Integer> item : map.entrySet()) {
            if (item.getValue() >= 2 && item.getKey() != val) {
                ans ^= item.getKey();
            }
        }
        return ans;
    }

}
