package com.wuxin.sort;

import com.wuxin.annotation.Description;
import com.wuxin.utils.InvocationHandlerMethodTime;
import com.wuxin.utils.LogarithmicDevice;
import com.wuxin.utils.NumberUtils;

import java.util.Arrays;

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
