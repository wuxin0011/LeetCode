package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;

import java.util.*;


/**
 * @author: wuxin0011
 * @Description:
 * @title
 * @url https://leetcode.cn/problems/minimum-height-trees/?envType=daily-question&envId=2024-03-17
 */
@SuppressWarnings("all")
public class Code_0020_310 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0020_310.class, "findMinHeightTrees", "./txt_file/Code_0020_310.txt");
    }


    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<>();
        if (n == 1) {
            ans.add(0);
            return ans;
        }
        ArrayList[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        int[] in = new int[n]; // 统计入读
        for (int[] e : edges) {
            int x = e[0], y = e[1];
            g[x].add(y);
            g[y].add(x);
            in[x]++;
            in[y]++;
        }

        // 由于是无向图，因此入读最小为1 以入读为1点
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (in[i] == 1) {
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            ans.clear();
            for (int i = q.size(); i > 0; i--) {
                int a = q.poll();
                ans.add(a);
                ArrayList<Integer> ls = g[a];
                for (int b : ls) {
                    in[b]--;
                    // a-b-d-e-f-g-h-i-j-l-k
                    // 假设 g 为最合适点
                    // 从a或者k出发
                    // 访问到g入读为1 然后从g在扫描一定能找到深度最小
                    if (in[b] == 1) {
                        q.add(b);
                    }
                }
            }
        }


        return ans;
    }


    /**
     * 超时写法
     * 超时原因是深度遍历是重复计算
     * 当追回最坏情况下会退化成双向链表 时间复杂度退化成 O(n2)
     * a-b-d-e-f-g-h-i-j-l-k
     *
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTreesTimeout(int n, int[][] edges) {
        ArrayList[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int x = e[0], y = e[1];
            g[x].add(y);
            g[y].add(x);
        }

        List<Integer> ans = new ArrayList<>();
        boolean[] vis = new boolean[n];
        int min = n;
        for (int i = 0; i < n; i++) {
            int h = dfs(g, i, vis);
            if (h < min) {
                ans.clear();
                ans.add(i);
                min = h;
            } else if (h == min) {
                ans.add(i);
            }
        }
        return ans;
    }


    /**
     * 图的深度遍历
     *
     * @param g   图
     * @param di  当前
     * @param vis 是否访问
     * @return 当前点能够达到的最大深度
     */
    public int dfs(ArrayList<Integer>[] g, int di, boolean[] vis) {

        Deque<int[]> sk = new ArrayDeque<>();
        sk.push(new int[]{di, 0});
        vis[di] = true;
        int mx = 0;
        while (!sk.isEmpty()) {
            int[] p = sk.pop();
            mx = Math.max(mx, p[1]);
            //  System.out.println("mx = " + mx + ",p=" + Arrays.toString(p));
            ArrayList<Integer> gs = g[p[0]];
            for (int id : gs) {
                if (!vis[id]) {
                    vis[id] = true;
                    sk.push(new int[]{id, p[1] + 1});
                }
            }
        }
        Arrays.fill(vis, false);
        //System.out.println("mx = " + mx);
        return mx;
    }

}
