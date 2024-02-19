package leetcode.base.array.sort;

import leetcode.annotation.Description;
import leetcode.utils.InvocationHandlerMethodTime;

/**
 * @author: wuxin0011
 * @Description:
 */
@Description("基数排序")
public class RadixSort implements ArraySort {
    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(RadixSort.class);
    }

    @Override
    public void sort(int[] arr) {

        if (arr == null || arr.length <= 1) {
            return;
        }

        // 获取数组中最大值
        int maxVal = getMaxValue(arr);

        // 对每个位数进行计数排序
        for (int exp = 1; maxVal / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }

    private static int getMaxValue(int[] arr) {
        int maxVal = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxVal) {
                maxVal = arr[i];
            }
        }
        return maxVal;
    }

    private static void countingSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10]; // 0到9共10个数字

        // 统计每个数字出现的次数
        for (int i = 0; i < n; i++) {
            int idx = (arr[i] / exp) % 10;
            count[idx]++;
        }

        // 计算累计出现次数
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // 根据计数结果将元素放入输出数组中
        for (int i = n - 1; i >= 0; i--) {
            int idx = (arr[i] / exp) % 10;
            output[count[idx] - 1] = arr[i];
            count[idx]--;
        }

        // 将输出数组复制回原数组
        System.arraycopy(output, 0, arr, 0, n);
    }
}
