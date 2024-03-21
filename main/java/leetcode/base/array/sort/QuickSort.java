package leetcode.base.array.sort;

import code_generation.annotation.Description;
import code_generation.proxy.InvocationHandlerMethodTime;
import code_generation.proxy.LogarithmicDevice;
import code_generation.utils.NumberUtils;

/**
 * @author: wuxin001
 * @Description:
 */
@Description("快速排序")
public class QuickSort implements LogarithmicDevice, ArraySort {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(QuickSort.class);
    }

    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int i1 = i + 1; i1 < arr.length; i1++) {
                if (arr[i] > arr[i1]) {
                    NumberUtils.swap(arr, i, i1);
                }
            }
        }
    }

    @Override
    public void sortOptimize(int[] arr) {
        ArraySort.super.sortOptimize(arr);
    }

    @Override
    public void logarithmicDevice() {
        NumberUtils.printArray("快速排序", new QuickSort());
    }
}
