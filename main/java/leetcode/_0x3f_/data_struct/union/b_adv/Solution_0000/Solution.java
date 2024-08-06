package leetcode._0x3f_.data_struct.union.b_adv.Solution_0000;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/smallest-string-with-swaps
 * @title: 交换字符串中的元素
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "smallestStringWithSwaps", "in.txt");
    }


    // @TestCaseGroup(start = 2)
    public String smallestStringWithSwaps(String ss, List<List<Integer>> pairs) {
        char[] s = ss.toCharArray();
        int n = s.length;
        Union u = new Union(n);
        for (List<Integer> p : pairs) {
            int a = p.get(0), b = p.get(1);
            u.union(a, s[a], b, s[b]);
        }
        for (int i = 0; i < n; i++) {
            List<Node> nodes = u.ids[i];
            if (nodes == null || nodes.size() == 0) {
                continue;
            }
            nodes.sort(Comparator.comparingInt(a -> a.c));
            int[] ids = new int[nodes.size()];
            int size = 0;
            for (Node node : nodes) {
                ids[size++] = node.index;
            }
            Arrays.sort(ids);
            for (int k = 0; k < size; k++) {
                int index = ids[k];
                char c = nodes.get(k).c;
                // System.out.printf("index = %d,c = %c\n", index, c);
                s[index] = c;
            }
        }
        return new String(s);
    }

    public static class Union {
        int[] fa;
        int[] s;
        int size;
        List[] ids;
        boolean[] vis;

        public Union(int n) {
            fa = new int[n];
            s = new int[n];
            ids = new ArrayList[n];
            vis = new boolean[n];
            for (int i = 0; i < n; i++) {
                fa[i] = i;
                s[i] = 1;
            }
            size = n;
        }

        public int find(int i) {
            while (i != fa[i]) {
                fa[i] = fa[fa[i]];
                i = fa[i];
            }
            return i;
        }


        public boolean isSame(int x, int y) {
            return find(x) == find(y);
        }

        public boolean union(int x, char c0, int y, char c1) {
            int idx0 = x, idx1 = y;
            x = find(x);
            y = find(y);
            if (x == y) {
                return false;
            } else {
                size--;
                int sx = s[x];
                int sy = s[y];
                List<Node> nodes = null;
                if (sx >= sy) {
                    nodes = ids[x];
                    if (nodes == null) {
                        ids[x] = new ArrayList<Node>();
                        nodes = ids[x];
                    }
                    fa[y] = x;
                    s[x] += s[y];
                    if (ids[y] != null && ids[y].size() > 0) {
                        nodes.addAll(ids[y]);
                        ids[y].clear();
                    }
                } else {
                    nodes = ids[y];
                    if (nodes == null) {
                        ids[y] = new ArrayList<Node>();
                        nodes = ids[y];
                    }
                    fa[x] = y;
                    s[y] += s[x];
                    if (ids[x] != null && ids[x].size() > 0) {
                        nodes.addAll(ids[x]);
                        ids[x].clear();
                    }
                }
                if (!vis[idx0]) {
                    nodes.add(new Node(idx0, c0));
                    vis[idx0] = true;
                }
                if (!vis[idx1]) {
                    nodes.add(new Node(idx1, c1));
                    vis[idx1] = true;
                }
                return true;
            }
        }
    }


    static class Node {
        int index;
        char c;

        Node(int index, char c) {
            this.index = index;
            this.c = c;
        }
    }

}