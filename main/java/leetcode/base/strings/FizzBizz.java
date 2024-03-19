package leetcode.base.strings;

import code_generation.annotation.Description;
import code_generation.enums.Difficulty;
import code_generation.enums.Tag;
import code_generation.enums.Type;
import code_generation.utils.InvocationHandlerMethodTime;
import code_generation.LogarithmicDevice;
import code_generation.utils.TestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description:
 */
@Description(value = "获取指定内容", url = "https://leetcode.cn/problems/fizz-buzz/", tag = Tag.STRING, diff = Difficulty.SIMPLE, types = {Type.STRING})
public class FizzBizz implements LogarithmicDevice {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(FizzBizz.class);
    }


    public List<String> fizzBuzz(int n) {
        ArrayList<String> strings = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            String s = "";
            if (i % 3 == 0) {
                s += "Fizz";
            } else if (i % 5 == 0) {
                s += "Buzz";
            } else {
                s += i;
            }
            strings.add(s);
        }

        return strings;
    }


    @Override
    public void logarithmicDevice() {
        TestUtils.testArray(this.fizzBuzz(3).toArray(), Arrays.asList("1", "2", "Fizz").toArray(), "ok");
        TestUtils.testArray(this.fizzBuzz(5).toArray(), Arrays.asList("1", "2", "Fizz","4","Buzz").toArray(), "ok");
        TestUtils.testArray(this.fizzBuzz(6).toArray(), Arrays.asList("1", "2", "Fizz","4","Buzz","Fizz").toArray(), "ok");
    }
}
