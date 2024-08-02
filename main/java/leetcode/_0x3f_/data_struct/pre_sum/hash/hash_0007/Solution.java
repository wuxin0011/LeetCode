package leetcode._0x3f_.data_struct.pre_sum.hash.hash_0007;

import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wuxin0011
 * @Description: 特殊处理 这种线的相等的可以转化为-1/-1形式
 * @url: https://leetcode.cn/problems/contiguous-array
 * @title: 连续数组
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "findMaxLength", "in.txt");
    }


    // @TestCaseGroup(start = 4)
    public int findMaxLength(int[] a) {
        int n = a.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + (a[i] > 0 ? 1 : -1);
        }
        // System.out.println(Arrays.toString(s));
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int ans = 0;
        for (int i = 1; i < s.length; i++) {
            if (map.containsKey(s[i])) {
                // System.out.println("ok");
                // System.out.println(x + ",get(X) = " + map.get(x) + ",i = " + i);
                ans = Math.max(i - map.get(s[i]), ans);
            } else {
                map.put(s[i], i);
            }
        }
        return ans;
    }


}