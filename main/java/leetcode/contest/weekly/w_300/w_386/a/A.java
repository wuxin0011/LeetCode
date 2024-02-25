package leetcode.contest.weekly.w_300.w_386.a;

import leetcode.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wuxin0011
 * @Description:
 */
public class A {
    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "isPossibleToSplit");
    }

    public boolean isPossibleToSplit(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int cnt = map.getOrDefault(num, 0) + 1;
            if (cnt > 2) return false;
            map.put(num, cnt);
        }
        return true;
    }


}
