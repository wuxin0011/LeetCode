package leetcode.contest.weekly.w_400.w_407.a;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   https://leetcode.cn/contest/weekly-contest-407/problems/number-of-bit-changes-to-make-two-integers-equal
 * @title: 使两个整数相等的位更改次数
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"minChanges","A.txt");
    }
     

    public int minChanges(int n, int k) {
//        System.out.println(Integer.toBinaryString(n));
//        System.out.println(Integer.toBinaryString(k) + "\n");
        if(n == k){
            return 0;
        }
        int cnt = 0;
        for(int i = 0;i < 32;i++) {
            int x = n >> i & 1;
            int y = k >> i & 1;
            if(x == y){
                continue;
            }
            if(x == 1) {
                cnt++;
            }else{
                return -1;
            }

        }
        return cnt;
    }

  

}