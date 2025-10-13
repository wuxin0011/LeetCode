package leetcode.contest.weekly.w_400.w_471;

import code_generation.utils.IoUtil;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/weekly-contest-471/problems/longest-balanced-substring-ii">最长的平衡子串 II</a>
 * @title: 最长的平衡子串 II
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class C {

    static class T0 {
        public static void main(String[] args) {
            IoUtil.testUtil(T0.class, "longestBalanced", "C.txt");
//        IoUtil.testUtil(C.class, "longestBalancedSubstring", "C.txt");
        }

        private static final int MOD = (int) 1e9 + 7;

        public int longestBalanced(String s) {
            int ans = 0;
            int[] a = s.chars().map(x -> x - 'a').toArray();
            int n = s.length();
            // 一种
            for (int i = 0; i < n; ) {
                int st = i;
                while (i < n && a[i] == a[st]) {
                    i++;
                }
                ans = Math.max(ans, i - st);
            }


            // 两种

            ans = Math.max(ans, help(0, 1, a));
            ans = Math.max(ans, help(0, 2, a));
            ans = Math.max(ans, help(1, 2, a));


            // 三种
            Map<Pair<Integer, Integer>, Integer> map = new HashMap<>();
            map.put(new Pair<>(0, 0), -1);
            Map<Integer, Integer> cnt = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int x = a[i];
                cnt.merge(x, 1, Integer::sum);
                Pair<Integer, Integer> pair = new Pair<>(get(cnt, 0) - get(cnt, 1), get(cnt, 1) - get(cnt, 2));
                if(map.containsKey(pair)) {
                    ans = Math.max(ans,i - map.get(pair));
                }else{
                    map.put(pair,i);
                }
            }
            return ans;
        }

        static int help(int x, int y, int[] a) {
            int res = 0;
            int n = a.length;
            // 继续分组循环
            Map<Integer, Integer> map;

            for (int i = 0; i < n; ) {
                if (a[i] != x && a[i] != y) {
                    i++;
                    continue;
                }
                // 清空
                map = new HashMap<>();
                map.put(0, i - 1);
                int d = 0;
                while (i < n && (a[i] == x || a[i] == y)) {
                    d += a[i] == x ? 1 : -1;
                    if (map.containsKey(d)) {
                        res = Math.max(res, i - map.get(d));
                    } else {
                        map.put(d, i);
                    }
                    i++;
                }
            }
            return res;
        }

        public int get(Map<Integer, Integer> cnt, int key) {
            return cnt.getOrDefault(key, 0);
        }

    }


    static class T1 {
        public static void main(String[] args) {
            IoUtil.testUtil(T1.class, "longestBalanced", "C.txt");
//        IoUtil.testUtil(C.class, "longestBalancedSubstring", "C.txt");
        }

        private static final int MOD = (int) 1e9 + 7;

        public int longestBalanced(String s) {
            int n = s.length();
            int[] arr = s.chars().map(x -> x - 'a').toArray();
            if (n < 10000) return f(arr);
            int[] preA = new int[n + 1];
            int[] preB = new int[n + 1];
            int[] preC = new int[n + 1];
            for (int i = 0; i < n; i++) {
                preA[i + 1] = preA[i] + (arr[i] == 0 ? 1 : 0);
                preB[i + 1] = preB[i] + (arr[i] == 1 ? 1 : 0);
                preC[i + 1] = preC[i] + (arr[i] == 2 ? 1 : 0);
            }
            if (preA[n] == n || preB[n] == n || preC[n] == n) return n;
            int ans = 0;
            Map<Pair<Integer, Integer>, Integer> map = new HashMap<>();
            map.put(new Pair<>(0, 0), 0);
            for (int i = 1; i <= n; i++) {
                int diffAB = preA[i] - preB[i];
                int diffBC = preB[i] - preC[i];
                Pair<Integer, Integer> key = new Pair<>(diffAB, diffBC);
                if (map.containsKey(key)) {
                    int start = map.get(key);
                    int cntA = preA[i] - preA[start];
                    int cntB = preB[i] - preB[start];
                    int cntC = preC[i] - preC[start];
                    if (cntA > 0 && cntB > 0 && cntC > 0) {
                        ans = Math.max(ans, i - start);
                    }
                } else {
                    map.put(key, i);
                }
            }
            Map<Integer, Integer> mapAB = new HashMap<>();
            mapAB.put(0, 0);
            for (int i = 1; i <= n; i++) {
                int diffAB = preA[i] - preB[i];
                if (mapAB.containsKey(diffAB)) {
                    int start = mapAB.get(diffAB);
                    int cntA = preA[i] - preA[start];
                    int cntB = preB[i] - preB[start];
                    int cntC = preC[i] - preC[start];
                    if (cntA > 0 && cntB > 0 && cntC == 0) {
                        ans = Math.max(ans, i - start);
                    }
                } else {
                    mapAB.put(diffAB, i);
                }
            }
            Map<Integer, Integer> mapBC = new HashMap<>();
            mapBC.put(0, 0);
            for (int i = 1; i <= n; i++) {
                int diffBC = preB[i] - preC[i];
                if (mapBC.containsKey(diffBC)) {
                    int start = mapBC.get(diffBC);
                    int cntA = preA[i] - preA[start];
                    int cntB = preB[i] - preB[start];
                    int cntC = preC[i] - preC[start];
                    if (cntA == 0 && cntB > 0 && cntC > 0) {
                        ans = Math.max(ans, i - start);
                    }
                } else {
                    mapBC.put(diffBC, i);
                }
            }
            Map<Integer, Integer> mapAC = new HashMap<>();
            mapAC.put(0, 0);
            for (int i = 1; i <= n; i++) {
                int diffAC = preA[i] - preC[i];
                if (mapAC.containsKey(diffAC)) {
                    int start = mapAC.get(diffAC);
                    int cntA = preA[i] - preA[start];
                    int cntB = preB[i] - preB[start];
                    int cntC = preC[i] - preC[start];
                    if (cntA > 0 && cntB == 0 && cntC > 0) {
                        ans = Math.max(ans, i - start);
                    }
                } else {
                    mapAC.put(diffAC, i);
                }
            }
            int len = 1;
            for (int i = 1; i < n; i++) {
                if (arr[i] == arr[i - 1]) {
                    len++;
                    ans = Math.max(ans, len);
                } else {
                    len = 1;
                }
            }

            return ans;
        }

        private int f(int[] a) {
            int n = a.length;
            int N = 3;
            int[][] sums = new int[n + 1][N];

            for (int i = 0; i < n; i++) {
                for (int k = 0; k < N; k++) {
                    sums[i + 1][k] = sums[i][k];
                }
                sums[i + 1][a[i]]++;
            }

            int ans = 1;
            for (int i = 0; i < n; i++) {
                for (int j = n - 1; j > i; j--) {
                    if (j - i + 1 <= ans) continue;
                    int flag = -1;
                    boolean ok = true;
                    for (int k = 0; k < N; k++) {
                        int v = sums[j + 1][k] - sums[i][k];
                        if (v == 0) continue;
                        if (flag == -1) flag = v;
                        if (flag != v) {
                            ok = false;
                            break;
                        }
                    }
                    if (ok) ans = Math.max(ans, j - i + 1);
                }
            }
            return ans;
        }

    }
}