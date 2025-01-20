package code_generation.utils;

import leetcode.base.array.sort.ArraySort;

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



    public static int[] clone(int[] arr) {
        int[] ints = new int[arr.length];
        System.arraycopy(arr, 0, ints, 0, arr.length);
        return ints;
    }

    public static void clone(int[] src, int[] dist) {
        for (int i = 0; i < dist.length; i++) {
            src[i] = dist[i];
        }
    }

    public static boolean isEqual(int[] alreadySort, int[] noSort) {
        if (alreadySort.length != noSort.length) {
            return false;
        }
        if (alreadySort == noSort) {
            return true;
        }
        Arrays.sort(noSort);

        boolean flag = true;
        for (int i = 0; i < alreadySort.length; i++) {
            if (!flag) {
                return false;
            }
            if (alreadySort[i] != noSort[i]) {
                flag = false;
            }
        }

        return true;
    }

    public static void printArray(boolean flag, String message, int[] arr1, int[] arr2) {
        if (message == null || "".equals(message)) {
            message = "测试";
        }
        if (flag) {
            System.out.println(message + " 通过!");
        } else {
            System.out.println("测试失败！失败结果");
            printArray(arr1);
            System.out.println("期望结果！");
            printArray(arr2);
        }
    }

    public static void printArray(ArraySort sort) {
        NumberUtils.printArray("本次案例测试", sort);
    }

    public static void printArray(String message, ArraySort sort) {
        NumberUtils.printArray(message, sort, 10);
    }


    public static void printArray(String message, ArraySort sort, int testCount) {
        printArray(message, sort, testCount, 100000, 100000);
    }

    public static void printArray(String message, ArraySort sort, int testCount, int bound, int size) {
        boolean flag = false;
        int[] arr1 = null;
        int[] arr2 = null;
        long time = 0;
        for (int i = 0; i < testCount; i++) {
            int[] arr = getInt(bound, size);
            int[] clone = clone(arr);
            long l1 = System.currentTimeMillis();
            sort.sort(arr);
            long l2 = System.currentTimeMillis();
            time += (l2 - l1);
            flag = NumberUtils.isEqual(arr, clone);
            if (!flag) {
                arr1 = arr;
                arr2 = clone;
                break;
            }
        }
        // 平均耗时
        double t = (double) (time / testCount);
        message += "测试:" + testCount + " 次，平均耗时:" + t + "ms ";
        NumberUtils.printArray(flag, message, arr1, arr2);
    }


    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}
