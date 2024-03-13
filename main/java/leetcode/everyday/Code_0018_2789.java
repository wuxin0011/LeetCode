package leetcode.everyday;

import leetcode.utils.IoUtil;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;


/**
 * @author: wuxin0011
 * @Description: 按照题目直接模拟，将第一个1放到最右边，然后剩下填充到左边，然后补0
 * @title
 * @url https://leetcode.cn/problems/bulls-and-cows/?envType=daily-question&envId=2024-03-10
 */
@SuppressWarnings("all")
public class Code_0018_2789 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0018_2789.class, "maxArrayValue", "./txt_file/Code_0018_2789.txt");
    }

    public long maxArrayValue(int[] nums) {
        if (nums == null || nums.length  == 0) {
            return 0;
        }
        if(nums.length==1) return nums[0]*1L;
        long ans = 0;
        long[] ns = new long[nums.length];
        for(int i = 0;i<nums.length;i++){
            ns[i] = nums[i]*1L;
        }
        for (int i = ns.length - 2; i >= 0;i--) {
            if (ns[i] <= ns[i+1]) {
                ns[i] += ns[i+1];
            }
            ans = Math.max(ns[i],ans);
        }

        return ans;
    }

}
