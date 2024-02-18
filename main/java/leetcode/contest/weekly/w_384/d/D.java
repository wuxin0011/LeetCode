package leetcode.contest.weekly.w_384.d;

import leetcode.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 */
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class,"countMatchingSubarrays");
    }

    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        if (pattern == null || nums == null || pattern.length > nums.length) {
            return 0;
        }
        return kmp(nums, pattern);
    }


    public static int kmp(int[] text, int[] pattern) {
        int[] nexts = new int[pattern.length];
        int count = 0;
        for (int i = 1; i < pattern.length; i++) {
            while (count > 0 && pattern[i] != pattern[count]) {
                count = nexts[count - 1];
            }
            if (pattern[i] == pattern[count]) {
                count++;
            }
            nexts[i] = count;
        }
        count = 0;
        int cnt = 0;
        for (int i = 1; i < text.length ; i++) { // 注意要-1因为是使用  nums 作为文本串
            int v = Integer.compare(text[i], text[i - 1]);
            while (count > 0 && v != pattern[count]) {
                count = nexts[count - 1];
            }
            if (v == pattern[count]) {
                count++;
            }
            if (count == pattern.length) {

                cnt++;
                count = nexts[count - 1];
            }
        }
        return cnt;
    }
}
