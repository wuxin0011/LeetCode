package leetcode._0x3f_.data_struct.monotonic_stacks.contribution_method.Solution_0001;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/sum-of-subarray-ranges
 * @title: 子数组范围和
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"subArrayRanges","in.txt");
    }
     

    public long subArrayRanges(int[] a) {
        Deque<Integer> sk = new ArrayDeque<>();
        int n = a.length;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            // 大 -> 小
            while (!sk.isEmpty() && a[i] < a[sk.peek()]) {
                int min = a[sk.pop()];
                ans += (a[i] - min);
            }
            sk.push(i);
        }
        while (!sk.isEmpty()) {
            int cur = sk.pop();
            int left = sk.isEmpty() ? 0 : sk.peek();
            ans = (ans + (cur - left) * 1L * (n - cur) *  a[cur]) ;
        }
        return (int) (ans );
	}

  

}