package leetcode.contest.weekly.w_300.w_392.c;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   https://leetcode.cn/contest/weekly-contest-392/problems/minimum-operations-to-make-median-of-array-equal-to-k
 * @title: 使数组中位数等于K的最少操作数
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class,"minOperationsToMakeMedianK","C.txt");
    }


    public long minOperationsToMakeMedianK(int[] nums, int k) {
        Arrays.sort(nums);
        long sum = 0;
        long count = 0;

        int n = nums.length;

        int tot = n >> 1;

        if(nums[tot] == k){
            return 0;
        }

        if(nums[tot] > k){
            while(tot >= 0 && nums[tot] > k){
                sum += nums[tot];
                tot--;
                count++;
            }

            return sum - count * k;
        }

        if(nums[tot] < k){
            while(tot < n && nums[tot] < k){
                sum += nums[tot];
                tot++;
                count++;
            }

            return count * k - sum;
        }

        return -1;
    }

  

}