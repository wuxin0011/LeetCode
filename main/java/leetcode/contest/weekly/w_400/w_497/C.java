package leetcode.contest.weekly.w_400.w_497;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: qitongwei
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/weekly-contest-497/problems/longest-balanced-substring-after-one-swap">一次交换后的最长平衡子串</a>
 * @title: 一次交换后的最长平衡子串
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "longestBalanced", "C.txt");
    }

    private static final int MOD = (int) 1e9 + 7;


    static int N = (int) 1e5 + 100, n;
    static int[] pre = new int[N], cnt0 = new int[N], cnt1 = new int[N];
    static Map<Integer, List<Integer>> map;

    public int longestBalanced(String s) {
        map = new HashMap<>();
        n = s.length();
        pre[0] = cnt0[0] = cnt1[0] = 0;
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - '0';
            pre[i + 1] = pre[i] + (c == 1 ? 1 : -1);
            cnt1[i + 1] = cnt1[i] + (c == 1 ? 1 : 0);
            cnt0[i + 1] = cnt0[i] + (c == 0 ? 1 : 0);
        }
        int ans = 0;
        for (int i = 0; i <= n; i++) {
            List<Integer> ids = map.getOrDefault(pre[i], new ArrayList<>());
            if (ids.size() != 0) {
                ans = Math.max(ans, i - ids.get(0));
            }
            // 需要-2 说明1多了检查外面是不是还有多余的0
            ans = Math.max(ans, calc(pre[i] - 2, i, cnt0));
            // 需要+2 说明0多了检查外面是不是还有多余的1
            ans = Math.max(ans, calc(pre[i] + 2, i, cnt1));
            ids.add(i);
            map.put(pre[i], ids);
        }
        return ans;
    }

    private static int calc(int t, int r, int[] cnt) {
        if (!map.containsKey(t)) return 0;
        List<Integer> ids = map.get(t);
        for (int l = 1; l <= 5; l++) {
            if (ids.size() >= l && cnt[n] - (cnt[r] - cnt[ids.get(l - 1)]) > 0) {
                return r - ids.get(l - 1);
            }
        }
        return 0;
    }


}