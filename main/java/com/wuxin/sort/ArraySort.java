package com.wuxin.sort;

import com.wuxin.utils.LogarithmicDevice;
import com.wuxin.utils.NumberUtils;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 */
public interface ArraySort extends LogarithmicDevice {

    /**
     * 默认排序
     *
     * @param arr arr
     */
    default void sort(int[] arr) {
        Arrays.sort(arr);
    }


    /**
     * 优化后排序
     *
     * @param arr arr
     */
    default void sortOptimize(int[] arr) {
        Arrays.sort(arr);
    }

    @Override
    default void logarithmicDevice() {
        Class<? extends ArraySort> aClass = this.getClass();
        String name = "";
        if (aClass.equals(InsertSort.class)) {
            name = "插入";
        } else if (aClass.equals(BubbleSort.class)) {
            name = "冒泡";
        } else if (aClass.equals(QuickSort.class)) {
            name = "快速";
        } else if (aClass.equals(BucketSort.class)) {
            name = "桶";
        } else if (aClass.equals(CountSort.class)) {
            name = "计数";
        } else if (aClass.equals(HeapSort.class)) {
            name = "堆";
        } else if (aClass.equals(MergeSort.class)) {
            name = "归并";
        } else if (aClass.equals(RadixSort.class)) {
            name = "基数";
        } else if (aClass.equals(ShellSort.class)) {
            name = "希尔";
        } else {
            name = aClass.getSimpleName();
        }
        NumberUtils.printArray(name + "排序", this, 30, 100, 1000);
    }
}
