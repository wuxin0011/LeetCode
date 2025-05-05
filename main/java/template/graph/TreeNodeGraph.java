package template.graph;

import code_generation.bean.TreeNode;

import java.util.*;

/**
 * * 二叉树还原成为无向图
 * @see templateBuildGraph1
 * @see templateBuildGraph2
 * 二叉树上的 LCA
 * 1> https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
 * 任意节点的LCA
 * @see TreeNodeLca
 * 2> https://leetcode.cn/problems/number-of-good-leaf-nodes-pairs/
 * @author: wuxin0011
 * @Description:

 */
@SuppressWarnings("all")
public class TreeNodeGraph {

    // 参考题目 https://leetcode.cn/problems/amount-of-time-for-binary-tree-to-be-infected/

    // 用 hashMap 保存图 优点方便
    static class templateBuildGraph1 {
        // BFS 建图
        // 根据题目输入的二叉树建立图 方便处理
        // 二叉树建图
        // 这种方式一般是题目每个节点 val 不同
        public static void buildEdgeListTreeNodeDFSGraph(TreeNode root) {

            Map<Integer, List<Integer>> g = new HashMap<>();

            class Temp {

                public void dfs(TreeNode node, int u) {
                    if (node == null) return;
                    int v = node.val;
                    if (u != -1) {
                        List<Integer> father = g.getOrDefault(u, new ArrayList<>());
                        List<Integer> son = g.getOrDefault(v, new ArrayList<>());
                        father.add(v);
                        son.add(u);
                        g.put(u, father);
                        g.put(v, son);
                    }
                    dfs(node.left, v);
                    dfs(node.right, v);
                }

            }
            new Temp().dfs(root, -1);
        }

        public static int treeNodeDFS(int u, int fa, Map<Integer, List<Integer>> g) {
            int ans = 0;
            if (g.get(u) != null) {
                for (int v : g.get(u)) {
                    if (v != fa && v != u) {
                        ans = Math.max(ans, treeNodeDFS(v, u, g) + 1);
                    }
                }
            }
            return ans;
        }


        // BFS

        public static void buildEdgeListTreeNodeBFSGraph(TreeNode root) {

            Map<Integer, List<Integer>> g = new HashMap<>();
            Deque<TreeNode> treeQueue = new ArrayDeque<>();
            treeQueue.add(root);
            List<Integer> father = null, son = null;
            while (!treeQueue.isEmpty()) {
                int size = treeQueue.size();
                while (size > 0) {
                    size--;
                    TreeNode node = treeQueue.poll();
                    father = g.getOrDefault(node.val, new ArrayList<>());
                    if (node.left != null) {
                        son = g.getOrDefault(node.left.val, new ArrayList<>());
                        father.add(node.left.val);
                        son.add(node.val);
                        g.put(node.left.val, son);
                        treeQueue.add(node.left);
                    }
                    if (node.right != null) {
                        son = g.getOrDefault(node.right.val, new ArrayList<>());
                        father.add(node.right.val);
                        son.add(node.val);
                        g.put(node.right.val, son);
                        treeQueue.add(node.right);
                    }
                    g.put(node.val, father);
                }
            }

        }


        public static void TreeNodeBFS(Map<Integer, List<Integer>> g, int root) {

            Deque<Integer> q = new ArrayDeque<>();
            q.add(root);
            int nodeMaxCnt = 0;
            Set<Integer> vis = new HashSet<>();
            vis.add(root);
            while (!q.isEmpty()) {
                int size = q.size();
                nodeMaxCnt++;
                while (size > 0) {
                    size--;
                    int node = q.poll();
                    for (int v : g.get(node)) {
                        if (vis.contains(v)) continue;
                        vis.add(v);
                        q.add(v);
                    }
                }
            }
//            System.out.println(nodeMaxCnt);
        }

    }


    // 修改root.val 方式建图同时统计个数
    static class templateBuildGraph2 {

        // 保存原来图节点值
        Map<Integer, Integer> originMap = new HashMap<>();

        public void buildTreeGraphLinkStart(TreeNode root) {
            pre(root);
            clear(nodeCount);
            build(root, -1);
        }

        // 遍历图
        public int dfs1(int u, int fa) {
            int ans = 0;
            for (int e = head[u]; e != -1; e = nxt[e]) {
                if (to[e] != fa && to[e] != u) {
                    ans = Math.max(ans, dfs1(to[e], u) + 1);
                }
            }
            return ans;
        }


        public void build(TreeNode node, int u) {
            if (node == null) return;
            int v = node.val;
            if (u >= 0) {
                addEdge(u, v, 0);
                addEdge(v, u, 0);
            }
            build(node.left, v);
            build(node.right, v);
        }


        int nodeCount = 0;

        public void pre(TreeNode node) {
            if (node == null) return;
            int v = node.val;
            originMap.put(node.val, nodeCount);
            node.val = nodeCount++;
            pre(node.left);
            pre(node.right);
        }


        // =========================================链式向前星建图模板开始=================================
        private static int N = (int) 1e5 + 100, NOT_EXIST_FLAG = -1;
        private static int M = N << 1; // 无向图
        private static int head[] = new int[N], nxt[] = new int[M], to[] = new int[M], weight[] = new int[M], cnt;


        public static void clear(int n) {
            for (int i = 0; i <= n + 10; i++) {
                head[i] = NOT_EXIST_FLAG;
            }
            cnt = 0;
        }

        public static void addEdge(int u, int v, int w) {
            nxt[cnt] = head[u];
            to[cnt] = v;
            weight[cnt] = w;
            head[u] = cnt++;
        }

        // =========================================模板结束=================================
    }


    // https://leetcode.cn/problems/number-of-good-leaf-nodes-pairs/
    public static class TreeNodeLca {

        int count = 0;
        List<Integer> ids = new ArrayList<>();
        // 如果题目需要原来值需要关闭注释
//        Map<Integer, Integer> originMap = new HashMap<>();
//        Map<Integer, Integer> cntValOriginMap = new HashMap<>();

        public void update(TreeNode root) {
            if (root == null) return;
//            originMap.put(root.val, count);
//            cntValOriginMap.put(count,root.val);
            root.val = count++;
            update(root.left);
            update(root.right);
            if (root.left == null && root.right == null) {
                ids.add(root.val);
            }
        }

        int st[][], power, h[];

        public void dfs(TreeNode root, int f, int d) {
            if (root == null) return;
            int u = root.val;
            st[u][0] = f;
            h[u] = d;
            for (int i = 1; i <= power; i++) {
                st[u][i] = st[st[u][i - 1]][i - 1];
            }
            dfs(root.left, u, d + 1);
            dfs(root.right, u, d + 1);
        }

        public int lca(int a, int b) {
            if (a == b) return a;
            if (h[b] > h[a]) {
                int t = a;
                a = b;
                b = t;
            }
            for (int i = power; i >= 0; i--) {
                if (h[st[a][i]] >= h[b]) a = st[a][i];
            }
            if (a == b) return a;
            for (int i = power; i >= 0; i--) {
                if (st[a][i] != st[b][i]) {
                    a = st[a][i];
                    b = st[b][i];
                }
            }
            return st[a][0];
        }

        public int countPairs(TreeNode root, int distance) {
            update(root);
            power = (int) (Math.log(count) / Math.log(2) + 1);
            h = new int[count + 2];
            st = new int[count + 2][power + 1];
            dfs(root, 0, 1);
            int res = 0;
            for (int i = 0; i < ids.size(); i++) {
                for (int j = i + 1; j < ids.size(); j++) {
                    int u = ids.get(i);
                    int v = ids.get(j);
                    int uvlca = lca(u, v);
                    if (h[u] + h[v] - 2 * h[uvlca] <= distance) {
                        res++;
                    }
                }
            }
            return res;
        }

    }

}
