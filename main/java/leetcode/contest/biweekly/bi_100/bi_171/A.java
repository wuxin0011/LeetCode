package leetcode.contest.biweekly.bi_100.bi_171;

import code_generation.utils.IoUtil;
import code_generation.annotation.TestCaseGroup;
import java.util.*;
/**
 * @author: agitated-curranfnd
 * @url:   <a href="https://leetcode.cn/contest/biweekly-contest-171/problems/complete-prime-number">完全质数</a>
 * @title: 完全质数
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"completePrime","A.txt");
    }
    private static final int MOD = (int)1e9 + 7;

    public static boolean isp(int x){
        if(x<2)return false;
        for(int i = 2;i*i<=x;i++){
            if(x%i==0)return false;
        }
        return true;
    }

    public boolean completePrime(int num) {
        String s = String.valueOf(num);
        int n = s.length();
        for (int i = 0; i <= n; i++) {
            String pre = s.substring(0,i);
            String suf = s.substring(i,n);
//            System.out.println(pre + " " + suf);
            if(pre.length()>0&&!isp(Integer.parseInt(pre))) return false;
            if(suf.length()>0&&!isp(Integer.parseInt(suf))) return false;
        }
        return true;
    }

  

}