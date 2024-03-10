package leetcode.contest.weekly.w_300.w_388.a;

import leetcode.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 * @title
 * @url https://leetcode.cn/problems/apple-redistribution-into-boxes/
 */
public class A {
    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "minimumBoxes", "in.txt");
    }

    public int minimumBoxes(int[] apple, int[] capacity) {
        int tot = 0;
        for (int n : apple) tot += n;
        Arrays.sort(capacity);
        int cnt = 0;
        for (int i = capacity.length - 1; i >= 0; i--) {
            tot -= capacity[i];
            cnt++;
            if (tot <= 0) break;
        }
        return cnt;
    }
}
