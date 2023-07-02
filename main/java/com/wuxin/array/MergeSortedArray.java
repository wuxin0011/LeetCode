package com.wuxin.array;

import com.wuxin.annotation.Description;
import com.wuxin.utils.Bean.Difficulty;
import com.wuxin.utils.Bean.Tag;
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
@Description(value = "合并有序数组", url = " https://leetcode.cn/problems/merge-sorted-array/", tag = Tag.ARRAY, diff = Difficulty.MEDIUM)
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
            while (n >= 1) {
                nums1[n-1] = nums2[n-1];
                n--;
            }
            return;
        }
        // 如果 num2 的最小值都比 num1 最大值大 直接拷贝
        if (nums1[m - 1] <= nums2[0]) {
            int index = 0;
            while (index < n) {
                nums1[m + index] = nums2[index];
                index++;
            }
            return;
        }
        int[] ints = new int[m + n];
        int p1 = 0, p2 = 0;
        int index = ints.length - 1;
        int i = 0;
        while (i <= index && (p1 != m || p2 != n)) {
            // 当 p1 == m 时候 p2 ！= n
            // p1 遍历 nums1 完毕 p2 没有遍历完毕 说明 p2 剩下直接拷贝到 ints中就 OK
            if (p1 == m) {
                while (p2 != n) {
                    ints[i] = nums2[p2];
                    p2++;
                    i++;
                }
                break;
            } else if (p2 == n) { // 如上面相反
                while (p1 != m) {
                    ints[i] = nums1[p1];
                    p1++;
                    i++;
                }
                break;
            }
            if (nums1[p1] > nums2[p2]) {
                ints[i] = nums2[p2];
                p2++;
            } else if (nums1[p1] <= nums2[p2]) {
                ints[i] = nums1[p1];
                p1++;
            }

            i++;

        }

        // 拷贝
        while (index >= 0) {
            nums1[index] = ints[index];
            index--;
        }
    }

    @Override
    public void logarithmicDevice() {

        boolean success = true;
        int[] numsFail1 = null;
        int[] numsFail2 = null;
        int[] numsExpect = null;
        int fail1 = 0;
        int randomCount = 109;
        int randomArrSize = 100;
        int randomSize = 100;

        for (int i = 0; i < new Random().nextInt(randomCount); i++) {
            if (!success) {
                break;
            }
            // 随机长度
            int len = new Random().nextInt(randomArrSize) + 1;

            int m = len - new Random().nextInt(len);
            int n = len - m;
            // 合并值之前数组
            int[] megerArrayBefore = NumberUtils.getInt(randomSize, len);
            // 生成数组
            int[] nums1 = new int[len];
            int[] nums2 = new int[n];

            int[] temp = new int[m];

            if (m >= 1) {
                // 数组初始化
                System.arraycopy(megerArrayBefore, 0, temp, 0, m);
                Arrays.sort(temp);
                // copy
                NumberUtils.clone(nums1, temp);
            }
            if (n >= 1) {
                if (len - m >= 0) System.arraycopy(megerArrayBefore, m, nums2, 0, len - m);
                Arrays.sort(nums2);
            }
            Arrays.sort(megerArrayBefore);

            this.merge(nums1, m, nums2, n);
            success = NumberUtils.isEqual(megerArrayBefore, nums1);
            if (!success) {
                numsFail1 = nums1;
                numsExpect = megerArrayBefore;
            }
        }
        NumberUtils.printArray(success, "测试", numsFail1, numsExpect);
    }
}

