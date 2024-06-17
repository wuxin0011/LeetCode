package leetcode.contest.weekly.w_400.w_402.c;

import code_generation.annotation.TestCaseGroup;
import code_generation.utils.IoUtil;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-402/problems/maximum-total-damage-with-spell-casting
 * @title: 施咒的最大总伤害
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "maximumTotalDamage0", "C.txt");
//        IoUtil.testUtil(C.class, "maximumTotalDamage", "C.txt");
//        IoUtil.testUtil(C.class, "maximumTotalDamage1", "C.txt");
    }


    // 双指针
    public long maximumTotalDamage0(int[] power) {
        Arrays.sort(power);
        int n = power.length;
        long[] max = new long[n];
        long[] dp = new long[n];
        //System.out.println(Arrays.toString(power));
        long maxNum = 0;
        int pos = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0 && power[i] == power[i - 1]) {
                dp[i] = dp[i - 1] + power[i];
            } else {
                while (power[i] - power[pos] > 2) {
                    pos++;
                }
                // 循环结束是 power[i] - power[pos] == 2
                // 因此 pos - 1 才是 > 2的位置
//                System.out.println("pos[i] = " + pos + ",val = " + power[i]);
                if (pos - 1 < 0) {
                    dp[i] = power[i];
                } else {
                    dp[i] = power[i] + max[pos-1];
                }
            }
            maxNum = Math.max(dp[i], maxNum);
            max[i] = maxNum;
        }
        return max[n - 1];
    }


    // 二分
    @TestCaseGroup(start = 3)
    public long maximumTotalDamage(int[] power) {
        Arrays.sort(power);
        int n = power.length;
        long[] max = new long[n];
        long[] dp = new long[n];
        long maxNum = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0 && power[i] == power[i - 1]) {
                dp[i] = dp[i - 1] + power[i];
            } else {
                // power[i] - 2 找到等于2的第一个位置 然后 - 1 才是 小于 2
                // 就是 < 2 的第一个位置
                int pos = lower_bound(power, power[i] - 2) - 1;
                if (pos < 0) {
                    dp[i] = power[i];
                } else {
                    // System.out.println("pos =" + pos + ", pos val = " + power[pos] + ",val = " + power[i]);
                    dp[i] = power[i] + max[pos];
                }
            }
            maxNum = Math.max(dp[i], maxNum);
            max[i] = maxNum;
        }
        return max[n - 1];
    }

    public int lower_bound(int[] p, int v) {
        int l = 0, r = p.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (mid == -1) {
                return -1;
            }
            if (p[mid] >= v) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    // 用红黑树替代

    public long maximumTotalDamage1(int[] power) {
        Arrays.sort(power);
        int n = power.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            map.put(power[i], i);
        }
        long[] max = new long[n];
        long[] dp = new long[n];
        long maxNum = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0 && power[i] == power[i - 1]) {
                dp[i] = dp[i - 1] + power[i];
            } else {
                Map.Entry<Integer, Integer> item = map.lowerEntry(power[i] - 2);
                if (item == null) {
                    dp[i] = power[i];
                } else {
                    dp[i] = power[i] + max[item.getValue()];
                }
            }
            maxNum = Math.max(dp[i], maxNum);
            max[i] = maxNum;
        }
        return max[n - 1];
    }

}