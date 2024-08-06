package leetcode._0x3f_.data_struct.union.b_adv.Solution_0001;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/lexicographically-smallest-equivalent-string
 * @title: 按字典序排列最小的等效字符串
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "smallestEquivalentString", "in.txt");
    }


    // 分析
    // 使用并查集将权重相同字符合并排序 通过集合查询该集合中最小字符

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        Union<Character> u = new Union<>(26);
        int n = s1.length();
        for (int i = 0; i < n; i++) {
            char c0 = s1.charAt(i);
            char c1 = s2.charAt(i);
            u.union(c0 - 'a', c0, c1 - 'a', c1);
        }
        for (int i = 0; i < 26; i++) {
            if (u.ids[i] == null || u.ids[i].size() == 0) {
                continue;
            }
            List<Node<Character>> nodes = u.ids[i];
            nodes.sort(Comparator.comparing(a -> a.c));
        }
        char[] a = baseStr.toCharArray();
        n = a.length;
        for (int i = 0; i < n; i++) {
            int id = u.find(a[i] - 'a');
            if (u.ids[id] == null || u.ids[id].size() == 0) {
                continue;
            }
            List<Node<Character>> nodes = u.ids[id];
            a[i] = nodes.get(0).c;
        }
        return new String(a);
    }

    public static class Union<T> {
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

        public boolean union(int x, T t0, int y, T t1) {
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
                    s[y] = 0;
                    // 由于大的吸收了小的 如果小的下标集合没有被吸收 会造成集合大小不够
                    if (ids[y] != null) {
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
                    s[x] = 0;
                    // 由于大的吸收了小的 如果小的下标集合没有被吸收 会造成集合大小不够
                    if (ids[x] != null) {
                        nodes.addAll(ids[x]);
                        ids[x].clear();
                    }
                }
                // 由于并查集中下标集合是唯一 加一个vis数组目的是为了保证不重复添加
                if (!vis[idx0]) {
                    nodes.add(new Node(idx0, t0));
                    vis[idx0] = true;
                }
                if (!vis[idx1]) {
                    nodes.add(new Node(idx1, t1));
                    vis[idx1] = true;
                }
                return true;
            }
        }
    }


    static class Node<T> {
        int index;
        T c;

        // 补充更多于ID绑定的内容
        Node(int index, T t) {
            this.c = t;
            this.index = index;
        }
    }

}