package leetcode.contest.biweekly.bi_100.bi_187;

import code_generation.utils.IoUtil;
import code_generation.annotation.TestCaseGroup;
import java.util.*;
/**
 * @author: qitongwei
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/biweekly-contest-187/problems/maximum-value-of-an-alternating-sequence">交替数列的最大元素</a>
 * @title: 交替数列的最大元素
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class,"maximumValue","B.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    
    public long maximumValue(int n, int s, int m) {
        if(n==1)return s;
        if(n==2)return s + m;
        return Math.max(f1(n,s,m),f2(n,s,m));
	}

    public static long calc(long a1,long t,long d){
        return a1 + (t-1) * d;
    }
    public static long f1(int n,int s,int m){

        int t = n / 2;
        long a1 = s;
        long a2 = s + m;
        long diff = m - 1;
        if((n % 2) == 1){
            return Math.max(calc(a1,t + 1,diff),calc(a2,t,diff));
        }else{
            return Math.max(calc(a1,t,diff),calc(a2,t,diff));
        }
    }
    public static long f2(int n,int s,int m){
        int t = n / 2;
        long a1 = s;
        long a2 = s + m -1;
        long diff = m - 1;
        if((n % 2) == 1){
            return Math.max(calc(a1,t + 1,diff),calc(a2,t,diff));
        }else{
            return Math.max(calc(a1,t,diff),calc(a2,t,diff));
        }
    }

  

}