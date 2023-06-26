package com.wuxin.sort;

import com.wuxin.annotation.Description;
import com.wuxin.utils.InvocationHandlerMethodTime;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 */
@Description("计数排序")
public class CountSort implements ArraySort{
    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(CountSort.class);
    }
}
