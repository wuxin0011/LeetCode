package leetcode.base.array.sort;

import code_generation.annotation.Description;
import code_generation.utils.InvocationHandlerMethodTime;

/**
 * @author: wuxin0011
 * @Description:
 */
@Description("堆排序")
public class HeapSort implements ArraySort {
    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(HeapSort.class);
    }

    @Override
    public void sort(int[] arr) {
        int n = arr.length;

        // 构建最大堆
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // 逐个取出堆顶元素进行排序
        for (int i = n - 1; i > 0; i--) {
            // 将当前堆顶元素（最大元素）与末尾元素交换
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // 调整剩余元素的堆结构
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i; // 最大元素的索引
        int leftChildIdx = 2 * i + 1; // 左子节点的索引
        int rightChildIdx = 2 * i + 2; // 右子节点的索引

        // 找到左子节点比当前最大元素大的情况
        if (leftChildIdx < n && arr[leftChildIdx] > arr[largest]) {
            largest = leftChildIdx;
        }

        // 找到右子节点比当前最大元素大的情况
        if (rightChildIdx < n && arr[rightChildIdx] > arr[largest]) {
            largest = rightChildIdx;
        }

        // 如果最大元素不是当前节点，则交换它们并继续调整堆结构
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr, n, largest);
        }
    }
}
