package com.wuxin.sort;

import java.util.Arrays;

/**
 * @author: wuxin001
 * @Description:
 */
public class SelectSort {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.printf("选择排序第%d次遍历\n", i);
            int[] arr = NumberUtils.getInt();
            sort(arr);
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void sort(int[] arr) {
        int minIndex = 0;
        for (int i = 0; i < arr.length; i++) {
           minIndex = i;
            for (int i1 = i; i1 < arr.length; i1++) {
                if (arr[minIndex] > arr[i1]) {
                    minIndex = i1;
                }
            }
            if (i != minIndex) {
                NumberUtils.swap(arr,i,minIndex);
            }
        }
    }
}
