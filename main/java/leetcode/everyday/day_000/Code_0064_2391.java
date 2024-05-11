package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;
/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/minimum-amount-of-time-to-collect-garbage
 * @title: 收集垃圾的最少总时间
 */
public class Code_0064_2391 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0064_2391.class, "garbageCollection", "txt_file\\Code_0064_2391.txt");
    }


    public int garbageCollection(String[] garbage, int[] travel) {
        int n = travel.length;
        // 找到最后一次就行
        int g = -1, m = -1, p = -1;
        int[] sum = new int[travel.length + 1];
        for (int i = 0; i < travel.length; i++) {
            sum[i + 1] = sum[i] + travel[i];
        }
        int cost = 0;
        for (int i = garbage.length-1; i>=0; i--) {
            String cur = garbage[i];
            cost += cur.length();
            if (g == -1 && cur.indexOf('G') != -1) {
                g = i;
            }
            if (m == -1 && cur.indexOf('M') != -1) {
                m = i;
            }
            if (p == -1 && cur.indexOf('P') != -1) {
                p = i;
            }
        }
        int tot = 0;
        if (p != -1) {
            tot += sum[p];
        }
        if (m != -1) {
            tot += sum[m];
        }
        if (g != -1) {
            tot += sum[g];
        }

        return tot + cost;
    }


}