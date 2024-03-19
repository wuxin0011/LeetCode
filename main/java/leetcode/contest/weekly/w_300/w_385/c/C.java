package leetcode.contest.weekly.w_300.w_385.c;

import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author: wuxin0011
 * @Description:
 */
public class C {
    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "mostFrequentPrime");
    }

    // 东，东南，南，西南，西，西北，北，东北。
    private static final int[][] dirs = {{1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}};

    public int mostFrequentPrime(int[][] mat) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> notSet = new HashSet<>();
        int[] st = {0, 0};
        int m = mat.length, n = mat[0].length;
        int mxcnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                for (int[] dir : dirs) {
                    int v = mat[i][j];
                    int x = i + dir[0], y = j + dir[1];
                    while (x >= 0 && x < m && y >= 0 && y < n) {
                        v = 10 * v + mat[x][y];
                        if (isPrime(v)) {
                            int cnt = map.getOrDefault(v, 0)+1;
                            mxcnt = Math.max(cnt, mxcnt);
                            map.put(v, cnt);
                        }
                        x += dir[0];
                        y += dir[1];
                    }
                }

            }
        }
        int max = -1;
        for (Map.Entry<Integer, Integer> item : map.entrySet()) {
            if (item.getValue().equals(mxcnt)) {
                max = Math.max(max, item.getKey());
            }
        }
        return max;
    }

    private boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}
