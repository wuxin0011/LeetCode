package com.wuxin.sort;

import com.wuxin.annotation.Description;
import com.wuxin.utils.InvocationHandlerMethodTime;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 */
@Description("堆排序")
public class HeapSort implements ArraySort {
    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(HeapSort.class);
    }
}
