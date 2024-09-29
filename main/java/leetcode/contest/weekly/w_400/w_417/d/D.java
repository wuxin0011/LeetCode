package leetcode.contest.weekly.w_400.w_417.d;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-417/problems/find-the-k-th-character-in-string-game-ii
 * @title: 找出第 K 个字符 II
 */
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "kthCharacter", "D.txt");
    }


    public char kthCharacter(long k, int[] operations) {
        // n 不需要太大，可以和 k-1 的二进制长度取最小值，详细解释见下文
        int n = Math.min(operations.length, 64 - Long.numberOfLeadingZeros(k - 1));
        return f(k, operations, n - 1);
    }

    private char f(long k, int[] operations, int i) {
        if (i < 0) {
            return 'a';
        }
        int op = operations[i];
        if (k <= (1L << i)) { // k 在左半边
            return f(k, operations, i - 1);
        }
        // k 在右半边
        char ans = f(k - (1L << i), operations, i - 1);
        return (char) ('a' + (ans - 'a' + op) % 26);
    }
}