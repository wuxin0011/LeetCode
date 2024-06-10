package leetcode.contest.weekly.w_400.w_401.a;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-401/problems/find-the-child-who-has-the-ball-after-k-seconds
 * @title: 找出 K 秒后拿着球的孩子
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "numberOfChild", "A.txt");
    }


    public int numberOfChild(int n, int k) {
        int i = 0;
        boolean is = true;
        while (k > 0) {

            if (is) {
                i++;
            } else {
                i--;
            }

            if (i == n-1) {
                is = false;
            } else if (i == 0) {
                is = true;
            }

            k--;
        }
        return i;
    }


}