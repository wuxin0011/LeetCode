package leetcode.bitwise_operations.Bitwise_0001;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/single-number-ii
 * @title: single-number-ii
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"singleNumber","in.txt");
    }

    // 在所有三个数字择找到唯一出现一次的数字
    // 可以利用数位统计 然后取模
    public int singleNumber(int[] nums) {
        int[] h = new int[32];
        for (int num : nums) {
            for (int i = 0; i < h.length; i++) {
                // 向右移动检查
                h[i] += num>>i & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < h.length; i++) {
            if(h[i] % 3 != 0 ){
                ans |= 1 << i;
            }
        }
        return ans;
    }


    // 更一般的
    // 对于任意数组 其他数都出现 m 次，找出唯一数字
    public int singleNumberAny(int[] nums,int m) {
        int[] h = new int[32];
        for (int num : nums) {
            for (int i = 0; i < h.length; i++) {
                // 向右移动检查
                h[i] += (num>>i & 1);
            }
        }
        int ans = 0;
        for (int i = 0; i < h.length; i++) {
            if(h[i] % m != 0 ){
                // 当前位置不为0
                ans |= 1 << i;
            }
        }
        return ans;
    }

}