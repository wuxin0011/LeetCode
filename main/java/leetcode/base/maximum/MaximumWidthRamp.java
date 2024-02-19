package leetcode.base.maximum;

import leetcode.annotation.Description;
import leetcode.utils.Bean.Difficulty;
import leetcode.utils.Bean.Type;
import leetcode.utils.InvocationHandlerMethodTime;
import leetcode.utils.LogarithmicDevice;
import leetcode.utils.TestUtils;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: wuxin0011
 * @Description:
 */
@Description(value = "最大宽度坡", url = "https://leetcode.cn/problems/maximum-width-ramp/", diff = Difficulty.MEDIUM, types = {Type.MATH})
public class MaximumWidthRamp implements LogarithmicDevice {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(MaximumWidthRamp.class);
    }

    @Override
    public void logarithmicDevice() {
        int[] roads = {6, 0, 8, 2, 1, 5};
        TestUtils.testBoolean(maxWidthRamp1(roads), 4);
        int[] rods2 = {9, 8, 1, 0, 1, 9, 4, 0, 4, 1};
        TestUtils.testBoolean(maxWidthRamp1(rods2), 7);
    }

    public int maxWidthRamp(int[] nums) {
        int n = nums.length;
        int ans = 0;

        return ans;
    }

    public int maxWidthRamp1(int[] nums) {
        int n = nums.length;
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            if (stk.isEmpty() || nums[stk.peek()] > nums[i]) {
                stk.push(i);
            }
        }
        int ans = 0;
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.isEmpty() && nums[stk.peek()] <= nums[i]) {
                ans = Math.max(ans, i - stk.pop());
            }
            if (stk.isEmpty()) {
                break;
            }
        }
        return ans;
    }
}
