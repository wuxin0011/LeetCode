package leetcode.contest.biweekly.bi_100.bi_141.a;

import code_generation.utils.IoUtil;
import template.number.Base;

import java.util.Arrays;
import java.util.List;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/biweekly-contest-141/problems/construct-the-minimum-bitwise-array-i">构造最小位运算数组 I</a>
 * @title: 构造最小位运算数组 I
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "minBitwiseArray", "A.txt");
    }


    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; i++) {
            int x = nums.get(i);
            if (x % 2 == 0) {
                continue;
            }
            ans[i] = find(x);

            // 以对齐方式打印结果能够便于观察
            // Base.printBinaryToString(x,ans[i]);
        }
        return ans;
    }
    // 101
    //


    // 暴力方法
    static int find(int t) {
        for (int i = 0; i < t; i++) {
            if ((i | (i + 1)) == t) {
                return i;
            }
        }
        return 0;
    }
}