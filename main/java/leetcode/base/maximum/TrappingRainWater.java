package leetcode.base.maximum;

import code_generation.annotation.Description;
import code_generation.enums.Difficulty;
import code_generation.proxy.InvocationHandlerMethodTime;
import code_generation.LogarithmicDevice;
import code_generation.utils.TestUtils;

/**
 * @author: wuxin0011
 * @Description: 接雨水
 */
@Description(value = "接雨水", url = "https://leetcode.cn/problems/trapping-rain-water/", diff = Difficulty.HARD)
public class TrappingRainWater implements LogarithmicDevice {


    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(TrappingRainWater.class);
    }

    /**
     * 动态规划
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        // 不能装雨水情况
        if (height == null || height.length <= 2) {
            return 0;
        }
        int len = height.length;
        int[] left = new int[len];
        int[] right = new int[len];

        // 初始化
        left[0] = height[0];
        right[len - 1] = height[len - 1];

        // 遍历一次
        // i = 1 开始是因为最左边不可能装水
        for (int i = 1; i < len; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
            right[len - i - 1] = Math.max(right[len - i], height[len - i - 1]);
        }

        // 返回结果
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += Math.min(left[i], right[i]) - height[i];
        }

        return sum;
    }


    @Override
    public void logarithmicDevice() {
        int heights1[] = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        TestUtils.testBoolean(trap(heights1), 6, "测试ok！");
        int heights2[] = {4, 2, 0, 3, 2, 5};
        TestUtils.testBoolean(trap(heights2), 9, "测试ok！");

    }
}
