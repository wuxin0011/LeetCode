package com.wuxin.maximum;

import com.wuxin.annotation.Description;
import com.wuxin.utils.Bean.Difficulty;
import com.wuxin.utils.Bean.Type;
import com.wuxin.utils.InvocationHandlerMethodTime;
import com.wuxin.utils.LogarithmicDevice;
import com.wuxin.utils.TestUtils;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 */
@Description(value = "子数组的最小值之和", url = "https://leetcode.cn/problems/sum-of-subarray-minimums/", diff = Difficulty.MEDIUM, types = {Type.MATH})
public class SumOSubarrayMinimums implements LogarithmicDevice {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(SumOSubarrayMinimums.class);
    }

    @Override
    public void logarithmicDevice() {
        int[] is = {3, 1, 2, 4};
        TestUtils.testBoolean(sumSubarrayMins(is), 17);
        int[] is2 = {11, 81, 94, 43, 3};
        TestUtils.testBoolean(sumSubarrayMins(is2), 444);
    }


    /**
     * 本质是求一个最小值个区间中最小值的个数
     *
     * @param arr
     * @return
     */
    public int sumSubarrayMins(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        int res = 0;
        int l, r = 0;
        System.out.println("arrays = " + Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            // 假设arr 中 l 和 l 两个区间断点默认从 i位置开始
            l = r = i;
            // 左右两个区间默认开始分别向左右连个端点移动
            // 直到找到 比 arr[i] < arr[l-1]
            while (l > 0 && arr[i] <= arr[l - 1]) {
                l--;
            }

            // 直到找到 比 arr[i] < arr[r]
            while (r < arr.length - 1 && arr[i] < arr[r + 1]) {
                r++;
            }
            // 这样就形成了 (l,r) 以 arr[i] 的最小值区间中，注意是开区间
            // 比如 (1,3) 实际上只有一个结果
            // 统计结果
            System.out.println("(" + l + " ," + r + "),[" + i + "] =" + arr[i] + " res = " + ((r - i + 1) * (i - l + 1) * arr[i]));
            res += (r - i + 1) * (i - l + 1) * arr[i];
        }
        return res;
    }
}
