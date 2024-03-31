package leetcode.contest.weekly.w_300.w_391.a;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: wuxin0011
 * @Description:
 * @url
 * @title
 */
public class A {
    public static void main(String[] args) {
        IoUtil.testUtil(A.class,IoUtil.DEFAULT_METHOD_NAME,"A.txt");
    }
    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int v = x;
        int tot = 0;
        while(x !=0){
            int a = x % 10;
            tot += a;
            x /= 10;
        }
        return v % tot == 0 ? tot : -1;

    }
}
