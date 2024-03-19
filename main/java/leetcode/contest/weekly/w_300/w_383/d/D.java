package leetcode.contest.weekly.w_300.w_383.d;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description: 将单词恢复初始状态所需的最短时II
 */
public class D {
    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "minimumTimeToInitialState");
    }


    /**
     * z function 解决
     * @param S
     * @param k
     * @return
     */
    public int minimumTimeToInitialState_z_function(String S, int k) {
        char[] s = S.toCharArray();
        int n = s.length;
        int[] z = new int[n];
        int l = 0, r = 0;
        for (int i = 1; i < n; i++) {
            if (i <= r) {
                z[i] = Math.min(z[i - l], r - i + 1);
            }
            while (i + z[i] < n && s[z[i]] == s[i + z[i]]) {
                l = i;
                r = i + z[i];
                z[i]++;
            }
            if (i % k == 0 && z[i] >= n - i) {
                return i / k;
            }
        }
        return (n - 1) / k + 1;
    }


    /**
     * 字符串hash
     * @param word
     * @param k
     * @return
     */

    public int minimumTimeToInitialState(String word, int k) {
        if (k > word.length()) {
            return 1;
        }
        build(word);
        int n = word.length();
        int t = 1;
        for (int i = k; i < n; i += k) {
            if (getHash(n - i) == getHash(i, n)) {
                return t;
            }
            t++;
        }
        return (n - 1) / k + 1;
    }

    private static final int MAX = (int) 1e6 + 1;
    private static final int BASE = 499;
    private static long[] pow = new long[MAX];
    private static long[] hash = new long[MAX];

    private static int right;

    public static long build(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        return build(s.toCharArray());
    }


    public static long build(char[] ss) {
        if (ss == null || ss.length == 0) {
            return 0;
        }

        if (ss.length > MAX) {
            throw new RuntimeException(" out ! ss.length =  " + ss.length + ",max = " + MAX);
        }
        int n = ss.length;
        pow[0] = 1;
        right = ss.length;
        hash[0] = ss[0] - 'a' + 1;
        for (int i = 1; i < n; i++) {
            pow[i] = pow[i - 1] * BASE;
            hash[i] = hash[i - 1] * BASE + ss[i] - 'a' + 1;
        }
        return hash[right - 1];
    }

    public static long getHash() {
        return getHash(0, right);
    }


    public static long getHash(int r) {
        return getHash(0, r);
    }

    // [l,r)
    public static long getHash(int l, int r) {
        if (r <= 0) {
            return 0;
        }
        if (r > MAX) {
            throw new RuntimeException("  right > max ! place check max");
        }
        long ans = hash[r - 1];
        if (l > 0) {
            ans -= hash[l - 1] * pow[r - l];
        }
        return ans;
    }

    public static int getFirst(long h, int len) {
        return getIdx(h, len);
    }

    public static int getIdx(long h, int len) {
        for (int i = 0; i < right - len + 1; i++) {
            if (h == getHash(i, i + len)) {
                return i;
            }
        }
        return -1;
    }

    public static int getLast(long h, int len) {
        for (int i = right; i - len >= 0; i--) {
            if (h == getHash(i - len, i)) {
                return i - len;
            }
        }
        return -1;
    }

    public static List<Integer> getIds(long h, int len) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < right - len + 1; i++) {
            if (h == getHash(i, i + len)) {
                ans.add(i);
            }
        }
        return ans;
    }
}
