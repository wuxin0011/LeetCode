package com.wuxin.sort;

import com.wuxin.annotation.Description;
import com.wuxin.utils.InvocationHandlerMethodTime;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 */
@Description("桶排序")
public class BucketSort implements ArraySort {
    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(BucketSort.class);
    }
}
