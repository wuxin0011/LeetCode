package leetcode.sort.array;

import leetcode.annotation.Description;
import leetcode.utils.InvocationHandlerMethodTime;

/**
 * @author: wuxin0011
 * @Description:
 */
@Description("归并排序")
public class MergeSort implements ArraySort {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(MergeSort.class);
    }

    @Override
    public void sort(int[] arr) {
        mergeSort(arr);
    }

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;
        int[] temp = new int[n]; // 用于临时存储合并结果的数组
        mergeSort(arr, 0, n - 1, temp); // 调用辅助函数进行归并排序
    }

    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid, temp); // 对左半部分进行归并排序
        mergeSort(arr, mid + 1, right, temp); // 对右半部分进行归并排序
        merge(arr, left, mid, right, temp); // 合并左右两部分
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left; // 左半部分起始索引
        int j = mid + 1; // 右半部分起始索引
        int k = 0; // 临时数组的索引

        // 将左右两部分按照从小到大的顺序合并到临时数组中
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // 将剩余的元素复制到临时数组中
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // 将临时数组的元素拷贝回原数组
        for (i = left, k = 0; i <= right; i++, k++) {
            arr[i] = temp[k];
        }
    }
}
