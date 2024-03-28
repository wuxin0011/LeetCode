package leetcode.contest.weekly.w_300.w_390.c;

import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
/**
 * @author: wuxin0011
 * @Description:
 * @url
 * @title
 */
public class C {
    public static void main(String[] args) {

        // IoUtil.testUtil(C.class, "mostFrequentIDs", "C.txt");
        IoUtil.testUtil(C.class, "mostFrequentIDs2", "C.txt");
    }

    public long[] mostFrequentIDs(int[] nums, int[] freq) {
        Map<Integer, Long> cnt = new HashMap<>();
        TreeMap<Long, Integer> m = new TreeMap<>();
        int n = nums.length;
        long[] ans = new long[n];
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (cnt.containsKey(x) && m.containsKey(cnt.get(x)) && m.merge(cnt.get(x), -1, Integer::sum) == 0) { // --m[cnt[x]] == 0
                m.remove(cnt.get(x));
            }
            long c = cnt.merge(x, (long) freq[i], Long::sum); // cnt[x] += freq[i]
            m.merge(c, 1, Integer::sum); // ++m[cnt[x]]
            ans[i] = m.lastKey();
        }
        return ans;
    }


    public long[] mostFrequentIDs2(int[] nums, int[] freq) {
        int n = nums.length;
        long[] ans = new long[n];
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        long[] f = new long[max + 1];
        int m = 0;
        for (int i = 0; i < n; i++) {
            f[nums[i]] += freq[i];
            if (m == nums[i] && freq[i] < 0) {
                for (int j = 0; j < i; j++) {
                    if (f[nums[j]] > f[m]) {
                        m = nums[j];
                    }
                }
            } else if (f[nums[i]] > f[m]) {
                m = nums[i];
            }
            ans[i] = f[m];
        }
        return ans;
    }

}
