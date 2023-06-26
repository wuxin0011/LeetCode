package com.wuxin.array;

import com.wuxin.sort.BubbleSort;
import com.wuxin.utils.InvocationHandlerMethodTime;
import com.wuxin.utils.LogarithmicDevice;
import com.wuxin.utils.NumberUtils;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: wuxin0011
 * @Description: 合并有序数组
 * @see https://leetcode.cn/problems/merge-sorted-array/
 */
public class MergeSortedArray implements LogarithmicDevice {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(MergeSortedArray.class);
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        // n == 0 不做任何处理
        if (n == 0) {
            return;
        }

        // 走到这一步 n != 0 n==0 直接将 nums2内容拷贝到num1 中就ok
        if (m == 0) {
            while (n >= 0) {
                nums1[n] = nums2[n];
                n--;
            }
            return;
        }
        // 如果 num2 的最小值都比 num1 最大值大 直接拷贝
        if (nums1[m - 1] < nums2[0]) {
            int index = 0;
            while (index > n) {
                nums1[m + index] = nums2[index++];
            }
            return;
        }

        int[] ints = new int[m + n];
        int p1 = 0, p2 = 0;
        int index = ints.length - 1;
        int i = 0;

        while (i <= index && p1 < m && p2 < n) {
            if (p1 == m - 1) {
                while (p2 < n && i <= index) {
                    ints[i++] = nums2[p2++];
                }
                break;
            }
            if (p2 == n - 1) {
                while (p1 < m && i <= index) {
                    ints[i++] = nums1[p1++];
                }
                break;
            }

            // p1 指向位置大 将p2 指向内容拷贝到 ints
            if (nums1[p1] > nums2[p2]) {
                ints[i++] = nums2[p2++];
                // p2 指向位置大 将p1 指向内容拷贝到 ints
            } else if (nums1[p1] < nums2[p2]) {
                ints[i++] = nums1[p1++];
            }

            // 由于上面最后一次 p1 或者 p2 没有比较
            // p2 指向位置大 将p1 指向内容拷贝到 ints
            if (p1 == m - 1 && p2 < n || (p2 == n - 1 && p1 < m)) {
                if (nums1[p1] > nums2[p2]) {
                    ints[i++] = nums2[p2];
                } else if (nums1[p1] < nums2[p2]) {
                    ints[i++] = nums1[p1];
                }
            }
        }

        // 拷贝
        while (index >= 0) {
            nums1[index] = ints[index];
            index--;
        }

    }

    @Override
    public void logarithmicDevice() {
        MergeSortedArray sortedArray = new MergeSortedArray();

        boolean success = true;
        int fail1 = 0;
        int randomCount = 100;
        int randomArrSize = 100;
        int randomSize = 100;

        for (int i = 0; i < new Random().nextInt(randomCount); i++) {
            // 随机长度
            int len = new Random().nextInt(randomArrSize) + 1;

            int m = len - new Random().nextInt(len);
            int n = len - m;
            // 合并值之前数组
            int[] megerArrayBefore = NumberUtils.getInt(randomSize, len);

            // 生成数组
            int[] nums1 = new int[len];
            int[] nums2 = new int[n];


            if (m >= 1) {
                // 数组初始化
                for (int j = 0; j < m; j++) {
                    nums1[j] = megerArrayBefore[j];
                }
            }
            if (n >= 1) {
                for (int j = m; j < len; j++) {
                    nums2[j - m] = megerArrayBefore[j];
                }
            }

            // 排序

            new BubbleSort().sort(nums1);
            new BubbleSort().sort(nums2);

            System.out.println("测试案例");
            System.out.println(Arrays.toString(nums1));
            System.out.println(Arrays.toString(nums2));

            // 合并
            sortedArray.merge(nums1, m, nums2, n);
            System.out.println("合并之后结果:");
            System.out.println(Arrays.toString(nums1));

            // 测试
            if (nums1.length >= 2) {
                for (int i1 = 1; i1 < nums1.length; i1++) {
                    if (nums1[i1 - 1] > nums1[i1]) {
                        success = false;
                        fail1 = i1;
                        break;
                    }
                }
            }

        }

        if (success) {
            System.out.println("测试全部通过！");
        } else {
            System.err.println("测试失败！");
            System.out.println("失败用例:" + fail1);
        }
    }
}

