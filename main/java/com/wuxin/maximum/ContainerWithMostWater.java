package com.wuxin.maximum;

import com.wuxin.annotation.Description;
import com.wuxin.utils.Bean.Difficulty;
import com.wuxin.utils.InvocationHandlerMethodTime;
import com.wuxin.utils.LogarithmicDevice;
import com.wuxin.utils.TestUtils;

/**
 * @author: wuxin0011
 * @Description:
 */
@Description(url = "https://leetcode.cn/problems/container-with-most-water/", value = "升水最多容器", diff = Difficulty.MEDIUM)
public class ContainerWithMostWater implements LogarithmicDevice {

    public static void main(String[] args) {

        InvocationHandlerMethodTime.getRunTime(ContainerWithMostWater.class);
    }

    public int maxArea(int[] height) {
        // 不能装水
        if (height == null || height.length <= 1) {
            return 0;
        }

        int l = 0, r = height.length - 1;

        int result = 0;
        while (l < r) {
            int max = Math.min(height[l],height[r])*(r-l);
            result =  Math.max(result,max);
            // 如果左边高度小于右边 左边移动
            if( height[l] < height[r]){
                l++;
            }else{
                r--;
            }
        }

        return result;
    }


    @Override
    public void logarithmicDevice() {
        int ints[] = new int[]{1, 2, 3, 4, 5};
        TestUtils.testBoolean(6,maxArea(ints));
    }
}
