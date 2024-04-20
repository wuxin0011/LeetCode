package leetcode.top_interview_150.remove_duplicates_from_sorted_array_ii;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii
 * @title: remove-duplicates-from-sorted-array-ii
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"removeDuplicates","in.txt");
    }
     

    public int removeDuplicates(int[] nums) {
        if(nums==null){
            return 0;
        }
        if(nums.length<=2){
            return nums.length;
        }
        int i = 2,j = 2,n = nums.length;
        while(j<n){
            if(nums[i-2] != nums[j]){
                nums[i] = nums[j];
                i++;
            }
            j++;
        }
        return i;
	}

  

}