package leetcode.base.array.sort;

import code_generation.annotation.Description;
import code_generation.proxy.InvocationHandlerMethodTime;

/**
 * @author: wuxin0011
 * @Description:
 */
@Description("计数排序")
public class CountSort implements ArraySort{
    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(CountSort.class);
    }

    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length <= 1)
            return;

        int n = arr.length;

        // 找出待排序数组的最大值
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // 创建计数数组，并根据元素的值进行统计
        int[] count = new int[max + 1];
        for (int i = 0; i < n; i++) {
            count[arr[i]]++;
        }

        // 根据计数数组重构原数组
        int index = 0;
        for (int i = 0; i <= max; i++) {
            while (count[i] > 0) {
                arr[index++] = i;
                count[i]--;
            }
        }
    }
}
