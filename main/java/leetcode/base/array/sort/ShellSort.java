package leetcode.base.array.sort;

import code_generation.annotation.Description;
import code_generation.proxy.InvocationHandlerMethodTime;

/**
 * @author: wuxin0011
 * @Description:
 */
@Description("希尔排序 希尔排序是插入排序进阶版 ！ 时间复杂度优化到 O(nlogn)")
public class ShellSort implements ArraySort {
    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(ShellSort.class);
    }

    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int n = arr.length;
        for (int step = n / 2; step > 0; step /= 2) {
            for (int i = step; i < n; i++) {
                int target = arr[i];
                int j = i - step;
                while (j >= 0 && arr[j] > target) {
                    arr[j + step] = arr[j];
                    j -= step;
                }
                arr[j + step] = target;
            }
        }

    }
}
