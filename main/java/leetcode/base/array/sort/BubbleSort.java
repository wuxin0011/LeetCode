package leetcode.base.array.sort;

import leetcode.annotation.Description;
import leetcode.utils.IoUtil;
import leetcode.utils.NumberUtils;

/**
 * @author: wuxin001
 * @Description: 冒泡排序
 */
@Description("冒泡排序")
public class BubbleSort implements  ArraySort {

    public static void main(String[] args) {
        // InvocationHandlerMethodTime.getRunTime(BubbleSort.class);
        IoUtil.testUtil(BubbleSort.class, "sort");
    }


    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int i1 = i; i1 < arr.length; i1++) {
                if (arr[i] > arr[i1]) {
                    NumberUtils.swap(arr, i, i1);
                }
            }
        }
    }

    public void sortOptimize(int[] arr) {
        boolean isSwap = true;
        for (int i = 0; i < arr.length; i++) {
            // 判断标志
            isSwap = false;
            for (int i1 = i; i1 < arr.length; i1++) {
                if (arr[i] > arr[i1]) {
                    NumberUtils.swap(arr, i, i1);
                    // 发生过交换标志
                    isSwap = true;
                }
            }
            // 如果没有发生一次交换说明不需要遍历了 退出本次玄幻
            if (!isSwap) {
                break;
            }
        }
    }


    public void sortOptimize2(int[] arr) {
        // 记录最后一次交换边界
        int laseSwapIndex = arr.length;
        // 本次交换的边界
        int newSwapIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            newSwapIndex = 0;
            for (int i1 = i; i1 < laseSwapIndex; i1++) {
                if (arr[i] > arr[i1]) {
                    NumberUtils.swap(arr, i, i1);
                    // 更新最后一次交换边界
                    newSwapIndex = i1;
                }
            }
            laseSwapIndex = newSwapIndex;
        }
    }



}
