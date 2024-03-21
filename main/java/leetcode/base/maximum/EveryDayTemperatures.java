package leetcode.base.maximum;

import code_generation.annotation.Description;
import code_generation.enums.Difficulty;
import code_generation.enums.Type;
import code_generation.proxy.InvocationHandlerMethodTime;
import code_generation.proxy.LogarithmicDevice;
import code_generation.utils.TestUtils;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 */
@Description(value = "每日温度", url = "https://leetcode.cn/problems/daily-temperatures/", diff = Difficulty.SIMPLE, types = {Type.MATH})
public class EveryDayTemperatures implements LogarithmicDevice {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(EveryDayTemperatures.class);
    }

    public int[] getTemperaturesMax(int[] temps) {
        int[] results = new int[temps.length];
        for (int i = 0; i < temps.length; i++) {
            for (int i1 = i + 1; i1 < temps.length; i1++) {
                if (temps[i1] - temps[i] > 0) {
                    results[i] = i1 - i;
                    break; // core ！
                }
            }
        }
        return results;
    }

    @Override
    public void logarithmicDevice() {
        int[] is = {73, 74, 75, 71, 69, 72, 76, 73};
        Integer[] expects = {1, 1, 4, 2, 1, 1, 0, 0};
        System.out.println(Arrays.toString(expects));
        System.out.println(Arrays.toString(getTemperaturesMax(is)));

        Integer[] rs = new Integer[is.length];
        int[] temperaturesMax = getTemperaturesMax(is);
        for (int i = 0; i < rs.length; i++) {
            rs[i] = temperaturesMax[i];
        }
        TestUtils.testArray(rs, expects, "ok");
    }
}
