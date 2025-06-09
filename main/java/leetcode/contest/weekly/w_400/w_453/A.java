package leetcode.contest.weekly.w_400.w_453;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-453/problems/transform-array-to-all-equal-elements">数组元素相等转换</a>
 * @title: 数组元素相等转换
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"canMakeEqual","A.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    public boolean canMakeEqual(int[] nums, int k) {

        class Info {
            public boolean check(int t) {
                int m = k;
                int[] a = Arrays.copyOf(nums, nums.length);
                int n  = a.length;
                for (int i = 0; i < n - 1 && m > 0; i++) {
                    if(a[i] != t) {
                        a[i] = t;
                        a[i + 1] *= -1;
                        m--;
                    }
                }
                for (int i = 0; i < n; i++) {
                    if(a[i] != t) return false;
                }
                return true;
            }
        }

        return new Info().check(1) || new Info().check(-1);
    }




  

}