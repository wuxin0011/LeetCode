package nowcoder.exam.bidirectional_search.Solution01;

import code_generation.utils.ACM.IO;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://www.nowcoder.com/practice/bf877f837467488692be703735db84e6?tpId=122&tqId=33698
 */
public class Solution {

    public static int MAXN = 40;

    public static int MAXM = 1 << 20;

    public static long[] arr = new long[MAXN];

    public static long[] lsum = new long[MAXM];

    public static long[] rsum = new long[MAXM];

    public static int n;

    public static long limit;

    public static void main(String[] args) throws IOException {
        IO io = new IO();
        n = io.readInt();
        limit = io.readLong();
        for (int i = 0; i < n; i++) {
            arr[i] = io.readLong();
        }
        io.println(compute());
        io.close();
    }

    public static long compute() {
        int l = dfs(0, n >> 1, 0, lsum, 0);
        int r = dfs(n >> 1, n, 0, rsum, 0);
        Arrays.sort(lsum, 0, l);
        Arrays.sort(rsum, 0, r);
        long ans = 0;
        for (int i = l - 1, j = 0; i >= 0; i--) {
            while (j < r && lsum[i] + rsum[j] <= limit) {
                j++;
            }
            ans += j;
        }
        return ans;
    }

    public static int dfs(int i, int e, long tot, long[] ans, int j) {
        if (tot > limit) {
            return j;
        }
        if (i == e) {
            ans[j++] = tot;
        } else {
            j = dfs(i + 1, e, tot, ans, j);
            j = dfs(i + 1, e, tot + arr[i], ans, j);
        }
        return j;
    }
}
