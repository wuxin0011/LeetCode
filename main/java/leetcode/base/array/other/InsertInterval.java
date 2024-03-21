package leetcode.base.array.other;

import code_generation.annotation.Description;
import code_generation.enums.Difficulty;
import code_generation.enums.Type;
import code_generation.proxy.InvocationHandlerMethodTime;
import code_generation.proxy.LogarithmicDevice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description:
 */

@Description(url = "https://leetcode.cn/problems/insert-interval", value = "合并区间", types = Type.ARRAY, diff = Difficulty.MEDIUM)
public class InsertInterval implements LogarithmicDevice {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(InsertInterval.class);
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ans = new ArrayList<>();
        ans.add(newInterval);
        ans.addAll(Arrays.asList(intervals));
        ans.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        List<int[]> ls = new ArrayList<>();
        int st = -1, ed = -1;
        for (int i = 0; i < ans.size(); i++) {
            int[] a = ans.get(i);
            if (st == -1 && ed == -1) {
                st = a[0];
                ed = a[1];
                continue;
            }
            if (ed >= a[0]) {
                ed = Math.max(ed, a[1]);
            } else {
                ls.add(new int[]{st, ed});
                st = a[0];
                ed = a[1];
            }
        }

        if (st != -1 && ed != -1) ls.add(new int[]{st, ed});

        int[][] t = new int[ls.size()][2];
        for (int i = 0; i < ls.size(); i++) {
            // System.out.println("ans = " + Arrays.toString(ls.get(i)));
            t[i] = ls.get(i);
        }
        return t;
    }


    public static void print(List<int[]> ans) {
        for (int i = 0; i < ans.size(); i++) {
            System.out.println(i + " " + Arrays.toString(ans.get(i)));
        }
    }

    @Override
    public void logarithmicDevice() {
        int[][] intervals = {{1, 3}, {6, 9}};
        int[] newInterval = {2, 5};
        int[][] insert = new InsertInterval().insert(intervals, newInterval);
        System.out.println("result = " + Arrays.deepToString(insert));
        int[][] intervals1 = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newI = {4, 8};
        System.out.println("result = " + Arrays.deepToString(new InsertInterval().insert(intervals1, newI)));
    }
}
