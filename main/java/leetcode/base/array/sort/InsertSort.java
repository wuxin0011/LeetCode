package leetcode.base.array.sort;

import leetcode.annotation.Description;
import leetcode.utils.InvocationHandlerMethodTime;

/**
 * @author: wuxin001
 * @Description: 插入排序
 */
@Description("插入排序")
public class InsertSort implements ArraySort {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(InsertSort.class);
    }

    @Override
    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int target = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > target) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = target;
        }
    }


}
