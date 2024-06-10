package leetcode.ox3if.dp.digital.digital_dp_0011;

import code_generation.annotation.TestCaseGroup;
import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 2999. 统计强大整数的数目
 * <p>
 * 给你三个整数start，finish和limit。
 * 同时给你一个下标从0开始的字符串s，表示一个 正整数。
 * 如果一个 正整数x 末尾部分是s（换句话说，s是 x的 后缀），且 x中的每个数位至多是 limit，那么我们称 x是 强大的。
 * 请你返回区间[start..finish]内强大整数的总数目。
 * 如果一个字符串 x是 y中某个下标开始（包括0），到下标为y.length - 1结束的子字符串，那么我们称x是y的一个后缀。比方说，25是5125的一个后缀，但不是512的后缀。
 * <p>
 * 示例 1：
 * 输入：start = 1, finish = 6000, limit = 4, s = "124"
 * 输出：5
 * 解释：区间 [1..6000] 内的强大数字为 124 ，1124 ，2124 ，3124 和 4124 。这些整数的各个数位都 <= 4 且 "124" 是它们的后缀。注意 5124 不是强大整数，因为第一个数位 5 大于 4 。
 * 这个区间内总共只有这 5 个强大整数。
 * <p>
 * 示例 2：
 * 输入：start = 15, finish = 215, limit = 6, s = "10"
 * 输出：2
 * 解释：区间 [15..215] 内的强大整数为 110 和 210 。这些整数的各个数位都 <= 6 且 "10" 是它们的后缀。
 * 这个区间总共只有这 2 个强大整数。
 * <p>
 * 示例 3：
 * 输入：start = 1000, finish = 2000, limit = 4, s = "3000"
 * 输出：0
 * 解释：区间 [1000..2000] 内的整数都小于 3000 ，所以 "3000" 不可能是这个区间内任何整数的后缀。
 * <p>
 * 提示：
 * 1 <= start <= finish <= 10^15
 * 1 <= limit <= 9
 * 1 <= s.length <= floor(log10(finish)) + 1
 * s数位中每个数字都小于等于limit。
 * s不包含任何前导 0 。
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/count-the-number-of-powerful-integers
 * @title: 统计强大整数的数目
 */
@TestCaseGroup(start = 1, end = 1)
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "numberOfPowerfulInt", "in.txt");
    }


    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        long end = Long.parseLong(s);
        if (end > finish) {
            return 0;
        }
        init(start, finish, s, limit);
        long res = dfs(0, true, true);
        System.out.println("result = " + res);
        return res;
    }

    char[] finishChar;
    char[] startChar;

    public void init(long start, long finish, String s, int limit) {
        this.s = s;
        this.limit = limit;
        this.finishChar = String.valueOf(finish).toCharArray();
        char[] startChar = String.valueOf(start).toCharArray();
        int n = finishChar.length;
        int diff = finishChar.length - startChar.length;
        if (diff == 0) {
            this.startChar = startChar;
        } else {
            char[] ss = new char[finishChar.length];
            for (int i = 0; i < ss.length; i++) {
                if (i < diff) {
                    ss[i] = '0';
                } else {
                    ss[i] = startChar[i - diff];
                }
            }
            this.startChar = ss;
        }
        this.diff = n - s.length();
        this.memo = new long[finishChar.length];
        Arrays.fill(this.memo, -1);
    }

    long[] memo;
    int diff;
    String s;
    int limit;

    int v = 0;

    // 为什么这里不需要参数 isNumber ？ 因为有低位限制！！！

    public long dfs(int i, boolean isLow, boolean isLimit) {
        if (i == finishChar.length) {
            return 1;
        }
        if (!isLow && !isLimit && memo[i] != -1) {
            return memo[i];
        }

        long res = 0;

        int lo = isLow ? startChar[i] - '0' : 0;
        int hi = isLimit ? finishChar[i] - '0' : 9;
        if (i < diff) {
            // 注意这里不能将 Math.min(hi, limit) 提出来
            // hi = Math.min(hi, limit)
            // 因为 d == hi 的条件会随着 hi改而修改 调试了好久
            for (int d = lo; d <= Math.min(hi, limit); d++) {
                v = v * 10 + d;
//                 System.out.println("val = " + v + ",lo = " + lo + ",hi = " + hi);
                res += dfs(i + 1, isLow && d == lo, isLimit && d == hi);
                v = (v - d) / 10;
            }
        } else {
            int d = s.charAt(i - diff) - '0';
            if (lo <= d && d <= Math.min(hi, limit)) {
                v = v * 10 + d;
//                 System.out.println("val = " + v + ",lo = " + lo + ",hi = " + hi );
                res += dfs(i + 1, isLow && d == lo, isLimit && d == hi);
                v = (v - d) / 10;
            }
        }
        if (!isLow && !isLimit) {
            memo[i] = res;
        }
        return res;
    }


}