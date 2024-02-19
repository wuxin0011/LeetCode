package leetcode.contest.biweekly.bi_124.c;

import leetcode.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wuxin0011
 * @Description:
 */
public class C {
    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "maxOperations");
    }


    Map<String, Integer> map = new HashMap<>();

    public int maxOperations(int[] nums) {
        int n = nums.length;
        int cnt1 = dfs(2, n - 1, nums[0] + nums[1], nums);
        int cnt2 = dfs(1, n - 2, nums[0] + nums[n - 1], nums);
        int cnt3 = dfs(0, n - 3, nums[n - 2] + nums[n - 1], nums);
        return Math.max(Math.max(cnt1, cnt2), cnt3) + 1;
    }


    public int dfs(int i, int j, int target, int[] nums) {
        if (i >= j) return 0;
        String key = createKey(i,j,target);
        if(map.containsKey(key)){
            return map.get(key);
        }
        int cnt = 0;
        if (target == nums[i] + nums[i + 1]) {
            cnt = Math.max(cnt, dfs(i + 2, j, target, nums) + 1);
        }
        if (target == nums[i] + nums[j]) {
            cnt = Math.max(cnt, dfs(i + 1, j - 1, target, nums) + 1);
        }
        if (target == nums[j] + nums[j - 1]) {
            cnt = Math.max(cnt, dfs(i, j - 2, target, nums) + 1);
        }
        map.put(key, cnt);
        return cnt;
    }

    public static String createKey(int i, int j, int target) {
        return i +
                ":" +
                j +
                ":" +
                target;
    }
}
