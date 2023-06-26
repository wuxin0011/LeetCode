package com.wuxin.sort;

import com.wuxin.annotation.Description;
import com.wuxin.utils.InvocationHandlerMethodTime;
import com.wuxin.utils.LogarithmicDevice;

/**
 * @author: wuxin001
 * @Description: 插入排序
 */
@Description("插入排序")
public class InsertSort implements ArraySort {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(InsertSort.class);
    }

    @Override
    public void sort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }


}
