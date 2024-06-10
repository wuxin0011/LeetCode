package leetcode.contest.weekly.w_300.w_393.c;

import code_generation.utils.IoUtil;

import java.util.*;

/**
 * 3116. 单面值组合的第 K 小金额
 *
 * 给你一个整数数组 coins 表示不同面额的硬币，另给你一个整数 k 。
 * 你有无限量的每种面额的硬币。但是，你 不能 组合使用不同面额的硬币。
 * 返回使用这些硬币能制造的 第 kth 小 金额。
 *
 * 示例 1：
 * 输入： coins = [3,6,9], k = 3
 * 输出： 9
 * 解释：给定的硬币可以制造以下金额：
 * 3元硬币产生3的倍数：3, 6, 9, 12, 15等。
 * 6元硬币产生6的倍数：6, 12, 18, 24等。
 * 9元硬币产生9的倍数：9, 18, 27, 36等。
 * 所有硬币合起来可以产生：3, 6, 9, 12, 15等。
 *
 * 示例 2：
 * 输入：coins = [5,2], k = 7
 * 输出：12
 * 解释：给定的硬币可以制造以下金额：
 * 5元硬币产生5的倍数：5, 10, 15, 20等。
 * 2元硬币产生2的倍数：2, 4, 6, 8, 10, 12等。
 * 所有硬币合起来可以产生：2, 4, 5, 6, 8, 10, 12, 14, 15等。
 *
 * 提示：
 * 	1 <= coins.length <= 15
 * 	1 <= coins[i] <= 25
 * 	1 <= k <= 2 * 10^9
 * 	coins 包含两两不同的整数。
 *
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-393/problems/kth-smallest-amount-with-single-denomination-combination
 * @title: 单面值组合的第K小金额
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "findKthSmallest", "C.txt");
        IoUtil.testUtil(C.class, "findKthSmallestLong", "C.txt");
    }


    public long findKthSmallest(int[] coins, int k) {


        return 0;
    }




}