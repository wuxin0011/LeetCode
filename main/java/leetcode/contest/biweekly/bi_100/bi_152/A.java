package leetcode.contest.biweekly.bi_100.bi_152;

import code_generation.utils.IoUtil;
import code_generation.annotation.TestCaseGroup;
import java.util.*;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/biweekly-contest-152/problems/unique-3-digit-even-numbers">不同三位偶数的数目</a>
 * @title: 不同三位偶数的数目
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"totalNumbers","A.txt");
    }
     

    public int totalNumbers(int[] a) {
        int n = a.length;
        Set<Integer> set = new HashSet<>();
        for(int i = 0;i < n;i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if(i == j || i == k || j == k) continue;
                    if(a[i]== 0 ) continue;
                    int x = a[i] * 100 + a[j] * 10 + a[k];
                    if(x % 2 == 0 && 100 <= x && x < 1000) {
                       set.add(x);
                    }
                }
            }
        }
        return set.size();
    }

  

}