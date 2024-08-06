package leetcode.contest.weekly.w_400.w_407.c;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-407/problems/maximum-number-of-operations-to-move-ones-to-the-end
 * @title: 将 1 移动到末尾的最大操作次数
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "maxOperations", "C.txt");
    }


    public int maxOperations(String s) {
        char[] a = s.toCharArray();
        int n = a.length;
        int cnt = 0;
        int x = 0, o = 0;
        for (int i = 0; i + 1 < n; i++) {
            if (a[i] == '1') {
                x++;
            }
            if (a[i] == '1' && a[i + 1] == '0') {
                cnt += x;
            }
        }
        return cnt;
    }


}