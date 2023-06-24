package com.wuxin.sort;

import java.util.Arrays;

/**
 * @author: wuxin001
 * @Description: 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.printf("冒泡排序第%d次遍历\n", i);
            int[] arr = NumberUtils.getInt();
            sort(arr);
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int i1 = i; i1 < arr.length; i1++) {
                if (arr[i] > arr[i1]) {
                    NumberUtils.swap(arr, i, i1);
                }
            }
        }
    }
}
