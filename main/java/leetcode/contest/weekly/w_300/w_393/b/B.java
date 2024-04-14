package leetcode.contest.weekly.w_300.w_393.b;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   https://leetcode.cn/contest/weekly-contest-393/problems/maximum-prime-difference
 * @title: 素数的最大距离
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class,"maximumPrimeDifference","B.txt");
    }
     

    public int maximumPrimeDifference(int[] nums) {
        int r = nums.length-1,l = 0;
        boolean rok=false,lok = false;
        while (true) {
            while(!is(nums[r])){
                r--;
            }
            while(!is(nums[l])){
                l++;
            }
            break;

        }
        return r - l;
	}

    public static boolean is(int num){
        if (num <= 1) {
            return false;
        }
        if (num <= 3) {
            return true;
        }
        if (num % 2 == 0 || num % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

  

}