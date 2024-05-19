package leetcode.contest.weekly.w_300.w_398.c;

import code_generation.utils.IoUtil;

/**
 * 100300. 所有数对中数位不同之和
 * <p>
 * 车尔尼有一个数组nums，它只包含 正整数，所有正整数的数位长度都 相同。
 * 两个整数的 数位不同指的是两个整数 相同位置上不同数字的数目。
 * 请车尔尼返回 nums中 所有整数对里，数位不同之和。
 * <p>
 * 示例 1：
 * 输入：nums = [13,23,12]
 * 输出：4
 * 解释：
 * 计算过程如下：
 * -13 和23 的数位不同为1 。
 * - 13 和 12的数位不同为1 。
 * -23 和12的数位不同为2 。
 * 所以所有整数数对的数位不同之和为1 + 1 + 2 = 4。
 * <p>
 * 示例 2：
 * 输入：nums = [10,10,10,10]
 * 输出：0
 * 解释：
 * 数组中所有整数都相同，所以所有整数数对的数位不同之和为 0 。
 * <p>
 * 提示：
 * 2 <= nums.length <= 10^5
 * 1 <= nums[i] < 10^9
 * nums中的整数都有相同的数位长度。
 *
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-398/problems/sum-of-digit-differences-of-all-pairs
 * @title: 所有数对中数位不同之和
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "sumDigitDifferences", "C.txt");
    }


    public long sumDigitDifferences(int[] nums) {
        int m = nums.length, n = String.valueOf(nums[0]).length();
        int[][] map = new int[n][10];
        char[][] chars = new char[m][n];
        for (int i = 0; i < m; i++) {
            chars[i] = String.valueOf(nums[i]).toCharArray();
        }
        long ans = 0;

        // 统计美列相同数字出现次数
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < m; j++) { // 相当于行 从 上到下
                int id = chars[j][i] - '0';
                ans += j - map[i][id];
                map[i][id]++;
            }
        }
//        for (int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(map[i]));
//        }

        return ans;
    }


}