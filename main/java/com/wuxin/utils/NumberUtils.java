package com.wuxin.utils;

import java.util.Arrays;
import java.util.Random;

/**
 * 生成数组工具
 *
 * @author: wuxin001
 * @Description:
 */
public class NumberUtils {


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(Arrays.toString(getInt()));
        }
    }

    public static int[] getInt() {
        return getInt(1000, 10);
    }

    public static int[] getInt(int bound, int size) {
        Random random = new Random();
        int[] ints = new int[size];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = random.nextInt(bound);
        }
        return ints;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void swap1(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
