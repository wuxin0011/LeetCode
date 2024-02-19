package leetcode.base.array.sort;

import leetcode.annotation.Description;
import leetcode.utils.IoUtil;

import java.util.ArrayList;

/**
 * @author: wuxin0011
 * @Description:
 */
@Description("桶排序")
public class BucketSort implements ArraySort {
    public static void main(String[] args) {
        // InvocationHandlerMethodTime.getRunTime(BucketSort.class);
        IoUtil.testUtil(BucketSort.class, "sort","1.txt", true);
    }

    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length <= 1)
            return;

        int n = arr.length;

        // 确定桶的数量和范围
        int maxVal = arr[0];
        int minVal = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > maxVal) {
                maxVal = arr[i];
            }
            if (arr[i] < minVal) {
                minVal = arr[i];
            }
        }
        int bucketNum = (maxVal - minVal) / n + 1;

        // 初始化桶
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < bucketNum; i++) {
            buckets.add(new ArrayList<>());
        }

        // 将元素分配到对应的桶中
        for (int i = 0; i < n; i++) {
            int bucketIndex = (arr[i] - minVal) / n;
            buckets.get(bucketIndex).add(arr[i]);
        }

        // 对每个桶中的元素进行排序
        for (int i = 0; i < bucketNum; i++) {
            ArrayList<Integer> bucket = buckets.get(i);
            insertionSort(bucket);
        }

        // 合并每个桶中的元素得到排序结果
        int index = 0;
        for (int i = 0; i < bucketNum; i++) {
            ArrayList<Integer> bucket = buckets.get(i);
            for (int num : bucket) {
                arr[index++] = num;
            }
        }
    }

    private static void insertionSort(ArrayList<Integer> bucket) {
        for (int i = 1; i < bucket.size(); i++) {
            int temp = bucket.get(i);
            int j = i - 1;
            while (j >= 0 && bucket.get(j) > temp) {
                bucket.set(j + 1, bucket.get(j));
                j--;
            }
            bucket.set(j + 1, temp);
        }
    }
}
