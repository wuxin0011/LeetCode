package leetcode.contest.biweekly.bi_100.bi_165;

import code_generation.utils.IoUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/biweekly-contest-165/problems/smallest-absent-positive-greater-than-average">大于平均值的最小未出现正整数</a>
 * @title: 大于平均值的最小未出现正整数
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"smallestAbsent","A.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    public int smallestAbsent(int[] a) {
        int n = a.length;
        int s = 0;
        Set<Integer> set = new HashSet<>();
        for(int x : a) {
            s += x;
            set.add(x);
        }
        int target = Math.max(1,s % n == 0 ? (s /n + 1) : (s + n - 1) / n);
        for(int x = target;;x++) {
            if(!set.contains(x)) {
                return x;
            }
        }
	}

  

}