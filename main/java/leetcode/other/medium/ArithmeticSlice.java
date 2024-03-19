package leetcode.other.medium;

import code_generation.annotation.Description;
import code_generation.enums.Difficulty;
import code_generation.enums.Type;
import code_generation.utils.InvocationHandlerMethodTime;
import code_generation.LogarithmicDevice;
import code_generation.utils.TestUtils;

/**
 * @author: wuxin0011
 * @Description:
 */
@Description(
        value = "等差数列划分",
        url = "https://leetcode.cn/problems/arithmetic-slices/",
        diff = Difficulty.MEDIUM,
        customTag = "数学",
        types = {Type.MATH, Type.DB}
)
public class ArithmeticSlice implements LogarithmicDevice {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(ArithmeticSlice.class);
    }

    public int numberOfArithmeticSlices(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];

        // 从第二项开始
        for (int i = 2; i < n; ++i) {
            // 成为等差条件
            if (nums[i] + nums[i - 2] == (nums[i - 1] << 1)) {
                // 连续的等序列
                // 当前项结果为前面之和 类似于动态规划问题
                dp[i] = 1 + dp[i - 1];
            }
        }
        int res = 0;
        for (int e : dp) {
            res += e;
        }
        return res;
    }

    @Override
    public void logarithmicDevice() {
        TestUtils.testBoolean(this.numberOfArithmeticSlices(new int[]{}), 1, "等差数列划分测试通过！！！");
    }
}
