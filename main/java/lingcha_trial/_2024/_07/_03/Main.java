package lingcha_trial._2024._07._03;

import code_generation.utils.ACM.IO;

/**
 * @author: wuxin0011
 * @Description:
 * @submit url : https://codeforces.com/contest/1849/submission/268702967
 */
public class Main {

    static IO io;
    static int MAXN = (int) 2e5 + 1;

    static int[] a = new int[MAXN];
    static int n;

    public static void main(String[] args) {
        io = new IO();
        int T = io.readInt();
        while (T > 0) {
            solve();
            T--;
        }
        io.close();
    }

    static void solve() {
        n = io.readInt();

        for (int i = 0; i < n; i++) {
            a[i] = io.readInt();
        }
        int ans = 0;
        for (int i = 0; i < n; ) {
            if (a[i] == 0) {
                i++;
                ans++;
            }
            int i0 = i;
            boolean has = false;
            for (; i < n && a[i] > 0; i++) {
                if (a[i] == 2) {
                    has = true;
                }
            }
            ans++;
            if (i0 > 0 && a[i0 - 1] == 0) {
                ans--;
                if (!has) {
                    continue;
                }
            }
            if (i < n) {
                a[i] = 1;
                i++;
            }
        }
        io.println(ans);
    }
}
