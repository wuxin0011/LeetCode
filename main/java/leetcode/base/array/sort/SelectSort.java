package leetcode.base.array.sort;

import code_generation.annotation.Description;
import code_generation.utils.InvocationHandlerMethodTime;
import code_generation.LogarithmicDevice;
import code_generation.utils.NumberUtils;

/**
 * @author: wuxin001
 * @Description:
 */
@Description("选择排序")
public class SelectSort implements LogarithmicDevice, ArraySort {
    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(SelectSort.class);
    }

    @Override
    public void sort(int[] arr) {
        int minIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            minIndex = i;
            for (int i1 = i; i1 < arr.length; i1++) {
                if (arr[minIndex] > arr[i1]) {
                    minIndex = i1;
                }
            }
            if (i != minIndex) {
                NumberUtils.swap(arr, i, minIndex);
            }
        }
    }

    @Override
    public void logarithmicDevice() {
        NumberUtils.printArray(this);
    }
}
