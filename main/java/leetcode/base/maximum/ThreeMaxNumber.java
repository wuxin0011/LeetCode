package leetcode.base.maximum;

import leetcode.annotation.Description;
import leetcode.utils.Bean.Difficulty;
import leetcode.utils.Bean.Type;
import leetcode.utils.InvocationHandlerMethodTime;
import leetcode.utils.LogarithmicDevice;
import leetcode.utils.TestUtils;

/**
 * @author: wuxin0011
 * @Description:
 */
@Description(value = "第三大数", url = "https://leetcode.cn/problems/third-maximum-number/", diff = Difficulty.SIMPLE, types = {Type.MATH})
public class ThreeMaxNumber implements LogarithmicDevice {
    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(ThreeMaxNumber.class);
    }

    public int thirdMax(int[] nums) {
        long m1 = Long.MIN_VALUE;
        long m2 = Long.MIN_VALUE;
        long m3 = Long.MIN_VALUE;
        for (int num : nums) {
            if (num == m1 || num == m2 || num == m3) {
                continue;
            }
            if (num > m1) {
                m3 = m2;
                m2 = m1;
                m1 = num;
            } else if (num > m2) {
                m3 = m2;
                m2 = num;
            } else if (num > m3) {
                m3 = num;
            }
        }
        return (int) (m3 != Long.MIN_VALUE ? m3 : m1);
    }

    @Override
    public void logarithmicDevice() {
        TestUtils.testBoolean(this.thirdMax(new int[]{1, 2, 3}), 1, "ok");
        TestUtils.testBoolean(this.thirdMax(new int[]{2, 2, 2}), 2, "ok");
        TestUtils.testBoolean(this.thirdMax(new int[]{3, 2}), 2, "ok");
    }
}
