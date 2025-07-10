package leetcode.contest.weekly.w_400.w_457;

import code_generation.utils.IoUtil;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-457/problems/power-grid-maintenance">电网维护</a>
 * @title: 电网维护
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class,"processQueries","B.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        int[] fa = new int[c + 1];
        int[] sz = new int[c+ 1];
        Arrays.fill(sz,1);
        for (int i = 0; i <= c; i++) {
            fa[i] = i;
        }

        Map<Integer, TreeSet<Integer>> d = new HashMap<>();
        Set<Integer> s = new HashSet<>();
        for (int v = 1; v <= c; v++) {
            TreeSet<Integer> set = new TreeSet<>();
            set.add(v);
            d.put(v, set);
            s.add(v);
        }

        Function<Integer, Integer> find = x -> {
            while (fa[x] != x) {
                fa[x] = fa[fa[x]];
                x = fa[x];
            }
            return x;
        };

        BiFunction<Integer, Integer, Integer> merge = (x, y) -> {
            x = find.apply(x);
            y = find.apply(y);
            if (Objects.equals(x, y)) return 0;
            if(sz[x] > sz[y]) {
                int t = x;
                x = y;
                y = t;
            }
            fa[y] = x;
            sz[x] += sz[y];
            return 1;
        };

        for (int[] con : connections) {
            int u = con[0];
            int v = con[1];
            merge.apply(u, v);
        }

        for (int[] con : connections) {
            int u = con[0];
            int v = con[1];
            TreeSet<Integer> st = d.get(find.apply(u));
            st.add(u);
            st.add(v);
        }

        List<Integer> ans = new ArrayList<>();
        for (int[] q : queries) {
            int op = q[0];
            int id = q[1];
            TreeSet<Integer> st = d.get(find.apply(id));

            if (op == 1) {
                if (s.contains(id)) {
                    ans.add(id);
                    continue;
                }
                if (st.isEmpty()) {
                    ans.add(-1);
                    continue;
                }
                ans.add(st.first());
            } else {
                s.remove(id);
                st.remove(id);
            }
        }
        int[] a = new int[ans.size()];
        for(int i = 0;i<ans.size();i++) {
            a[i] = ans.get(i);
        }
        return a;
    }



}