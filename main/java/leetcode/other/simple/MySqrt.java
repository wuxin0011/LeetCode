package leetcode.other.simple;

import code_generation.annotation.Description;
import code_generation.proxy.InvocationHandlerMethodTime;
import code_generation.proxy.LogarithmicDevice;

/**
 * @author: wuxin0011
 * @Description:
 */

@Description("获取一个数的平方根")
public class MySqrt implements LogarithmicDevice {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(MySqrt.class);
    }

    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }
        int i = 0;
        int result = x;
        while (result > 1) {
            i++;
            result = x - i * i;
        }
        if (result < 0) {
            return i - 1;
        }
        return i;
    }

    public int mySqrt2(int x) {
        if (x <= 1) {
            return x;
        }
        int i = 0;
        int result = x;
        while (result > 1) {
            i++;
            result = x - i * i;
        }
        if (result < 1) {
            return i - 1;
        }

        return i;
    }

    @Override
    public void logarithmicDevice() {
        MySqrt mySqrt = new MySqrt();

        boolean flag = true;
        int fail = 0;
        int result = 0;
        for (int i = 0; i < 100000; i++) {
            if (!flag) {
                break;
            }
            if (Math.floor(Math.sqrt((double) i)) != mySqrt.mySqrt(i)) {
                flag = false;
                fail = i;
                result = mySqrt.mySqrt(i);
            }
        }
        if (flag) {
            System.out.println("测试案例全部通过");
        } else {
            System.out.println("测试失败！ result = " + result + " 输入数 = " + fail);
        }
    }
}
