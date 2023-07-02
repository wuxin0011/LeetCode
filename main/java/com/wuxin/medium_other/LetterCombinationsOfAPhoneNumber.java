package com.wuxin.medium_other;

import com.wuxin.annotation.Description;
import com.wuxin.utils.Bean.Difficulty;
import com.wuxin.utils.InvocationHandlerMethodTime;
import com.wuxin.utils.LogarithmicDevice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description:
 */

@Description(value = "电话号码的字母组合", url = "https://leetcode.cn/problems/letter-combinations-of-a-phone-number/", diff = Difficulty.MEDIUM)
public class LetterCombinationsOfAPhoneNumber implements LogarithmicDevice {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(LetterCombinationsOfAPhoneNumber.class);
    }

    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<String>();
        if (isEmpty(digits)) {
            return list;
        }
        // 获取字符串返回结果
        List<String> strings = new ArrayList<String>();
        for (int i = 0; i < digits.length(); i++) {
            String str = getStr(digits.charAt(i));
            if (!isEmpty(str)) {
                strings.add(str);
            }
        }
        // 如何知道遍历对少次呢？
        getResult(strings, list);

        return list;
    }


    public static String getResult(List<String> strings, List<String> list) {

        int size = strings.size();
        for (int i1 = 0; i1 == 0; i1++) {
            char s = strings.get(i1).charAt(0);
        }

        return null;
    }

    public static String getResult(List<String> strings, int index, List<String> list) {

        int size = strings.size();
        for (int i1 = 0; i1 == 0; i1++) {

        }

        return null;
    }

    public static boolean isEmpty(String digits) {
        return "".equals(digits) || digits == null;
    }

    public static String getStr(char c) {
        switch (c) {
            case '2':
                return "abc";
            case '3':
                return "def";
            case '4':
                return "ghi";
            case '5':
                return "jkl";
            case '6':
                return "mno";
            case '7':
                return "pqrs";
            case '8':
                return "tuv";
            case '9':
                return "wxyz";
            default:
                return "";
        }
    }

    @Override
    public void logarithmicDevice() {

        String s = "223";
        List<String> strings = this.letterCombinations(s);
        System.out.println(strings);
    }
}
