package leetcode.contest.biweekly.bi_100.bi_169;

import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/biweekly-contest-169/problems/count-subarrays-with-majority-element-ii">统计主要元素子数组数目 II</a>
 * @title: 统计主要元素子数组数目 II
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "countMajoritySubarrays", "D.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public long countMajoritySubarrays(int[] a, int t) {
        int n = a.length;
        boolean ok=false;
        for (int x : a) {
            if(x==t){
                ok=true;
                break;
            }
        }
        if(!ok)return 0;
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = (a[i] == t) ? 1 : -1;
        }
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + s[i];
        }
        Set<Integer> st = new TreeSet<>();
        for (int p : pre) {
            st.add(p);
        }
        Map<Integer, Integer> cnt = new HashMap<>();
        int idx = 0;
        for (int val : st) {
            cnt.put(val, idx++);
        }
        int m = st.size();
        Fenwick f = new Fenwick(m);
        f.add(cnt.get(0), 1);
        long ans = 0;
        for (int j = 0; j < n; j++) {
            int p0 = pre[j + 1];
            int id = cnt.get(p0);
            if (id > 0) {
                ans += f.sums(id - 1);
            }
            f.add(id, 1);
        }
        return ans;
    }


    public static class Fenwick {
        int n;
        long[] tree;
        int[] a,diff;

        public Fenwick(int[] a) {
            this.n = a.length;
            this.tree = new long[n + 1];
            this.diff = new int[n + 1];
            this.a = a;
        }
        public Fenwick(int n) {
            this.n = n;
            this.tree = new long[n + 1];
        }

        public void init() {
            for (int i = 0; i < n; i++) {
                this.add(i, a[i]);
            }
        }

        public int lowf(int i) {
            return i & -i;
        }

        public void add(int i, int val) {
            int v = val;
            if (diff != null) {
                v = val - this.diff[i];
                this.diff[i] = v;
            }
            i++;
            while (i <= n) {
                tree[i] += v;
                i += lowf(i);
            }
        }

        private long sums(int i) {
            int s = 0;
            i++;
            while (i > 0) {
                s += tree[i];
                i -= lowf(i);
            }
            return s;
        }

        public long sums(int l, int r) {
            if (l < 0 || r > n || l > r) {
                return 0;
            }
            return sums(r) - sums(l - 1);
        }
    }


}