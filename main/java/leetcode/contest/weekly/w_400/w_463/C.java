package leetcode.contest.weekly.w_400.w_463;

import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-463/problems/minimum-sum-after-divisible-sum-deletions">删除可整除和后的最小数组和</a>
 * @title: 删除可整除和后的最小数组和
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class,"minArraySum","C.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    static int inf = 0x3ffffff;

    public long minArraySum(int[] a, int k) {
        int n = a.length;
        long s = 0,f = 0;
        Map<Long,Long> minF = new HashMap<>();
        minF.put(0L,0L);
        for (int x : a) {
            s = (((1L * x + s)%k + k)%k);
            // f 表示以i结尾最小前缀和
            // 不删除x + 删除x，
            f = Math.min(f + x,minF.getOrDefault(s,Long.MAX_VALUE));
            minF.put(s,f);
        }
        return f;
    }

  

}