package leetcode.everyday;

import code_generation.utils.IoUtil;

/*
*
*   使用动态规划，推理当前状态
*
 */


/**
 * @author: wuxin0011
 * @Description:  简单模拟 读懂题目意思
 * @title 找出数组中的 K-or 值
 * @url https://leetcode.cn/problems/find-the-k-or-of-an-array/description/?envType=daily-question&envId=2024-03-06
 */
@SuppressWarnings("all")
public class Code_0012_2917 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0012_2917.class, "findKOr", "./txt_file/Code_0012_2917.txt");
    }


    public int findKOr(int[] nums, int k) {
        int[] ans = new int[32];
        int cnt = 0;
        for(int num : nums){
            int i = 31;
            while( num != 0){
                ans[i] += num % 2;
                num /=2;
                i--;
            }
        }
        // System.out.println(Arrays.toString(ans));
        for(int i = 31,j = 0 ;i>=0;i--,j++){
            //System.out.println("ans[i] = " + ans[i]);
            if(ans[i] >= k ) {
                cnt |=  1 << j;
            }
        }
        return cnt;
    }


}
