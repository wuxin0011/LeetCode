package com.wuxin.sort;

import com.wuxin.annotation.Description;
import com.wuxin.utils.InvocationHandlerMethodTime;

/**
 * @author: wuxin0011
 * @Description:
 */
@Description("基数排序")
public class RadixSort implements ArraySort {
    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(RadixSort.class);
    }
}
