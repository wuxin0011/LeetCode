package com.wuxin.strings;

import com.wuxin.annotation.Description;
import com.wuxin.utils.Bean.Difficulty;
import com.wuxin.utils.Bean.Tag;
import com.wuxin.utils.Bean.Type;
import com.wuxin.utils.InvocationHandlerMethodTime;
import com.wuxin.utils.LogarithmicDevice;
import com.wuxin.utils.TestUtils;

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
