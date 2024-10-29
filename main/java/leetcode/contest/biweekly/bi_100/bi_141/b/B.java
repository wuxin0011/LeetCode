package leetcode.contest.biweekly.bi_100.bi_141.b;

import code_generation.utils.IoUtil;

import java.util.Arrays;
import java.util.List;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/biweekly-contest-141/problems/construct-the-minimum-bitwise-array-ii">构造最小位运算数组 II</a>
 * @title: 构造最小位运算数组 II
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "minBitwiseArray", "B.txt");
    }

    // 本题属于观察题 可以通过暴力方式求出 当前位置
    // 然后打印 二进制x和求得结果发现从最低位开始第一次遇到0的位置的前一位变成 ^ 即可
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; i++) {
            int x = nums.get(i);
            if (x % 2 == 0) continue;
            int st = 0;
            while (st < 32) {
                if ((x >> st & 1) == 0) break;
                st++;
            }
            st--;
            ans[i] = x ^ (1 << st);
        }
        return ans;
    }


}