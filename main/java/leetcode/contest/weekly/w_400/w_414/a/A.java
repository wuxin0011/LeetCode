package leetcode.contest.weekly.w_400.w_414.a;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-414/problems/convert-date-to-binary
 * @title: 将日期转换为二进制表示
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "convertDateToBinary", "A.txt");
    }


    public String convertDateToBinary(String date) {
        String s = "";
        s += Integer.toBinaryString(Integer.parseInt(date.substring(0, 4)));
        s += "-";
        s += Integer.toBinaryString(Integer.parseInt(date.substring(5, 7)));
        s += "-";
        s += Integer.toBinaryString(Integer.parseInt(date.substring(8, 10)));
        return s;
    }


}