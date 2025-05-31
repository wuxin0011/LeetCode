package template.graph;

import java.util.PriorityQueue;

/**
 * @author: wuxin0011
 * @Description: 假设网格图中相邻两点距离为1 给出原点和终点求到达目标点需要最小步骤  0 网格图中只有 0 和 1 ，0 认为不可走 1 可以走
 */
public class AStar {


    /**
     * A* 算法
     *
     * @param grid 二维网格图
     * @param st   起点
     * @param ed   终点
     * @return 最小值
     */

    public static int dijkstraGridBFSAStart(int[][] grid, int[] st, int[] ed) {
        int m = grid.length, n = grid[0].length;
        if (st[0] < 0 || st[0] >= m || st[1] < 0 || st[1] >= m || ed[0] < 0 || ed[0] >= m || ed[1] < 0 || ed[1] >= m) {
            return -1;
        }
        int[][] dis = new int[m][n];
        int inf = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dis[i][j] = inf;
            }
        }

        boolean[][] vis = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]); // 根据权重排序

        int x0 = st[0], y0 = st[1]; // 起始点


        boolean isUseMan = true;

        if (isUseMan) {
            // 真实距离和预估距离
            dis[x0][y0] = 1 + ManhattanDistance(st[0], st[1], ed[0], ed[1]);
        } else {
            dis[x0][y0] = 1 + SingleDistance(st[0], st[1], ed[0], ed[1]);
        }


        // 对角线距离

        pq.add(new int[]{x0, y0, dis[x0][y0]});
        int[] dirs = {-1, 0, 1, 0, 1};

        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            x0 = p[0];
            y0 = p[1];
            int wt0 = p[2];
            if (x0 == ed[0] && y0 == ed[1]) {
                return dis[ed[0]][ed[1]]; // 找到目标点
            }
            if (vis[x0][y0]) {
                continue;
            }
            vis[x0][y0] = true;

            // 分层搜索
            for (int k = 0; k < 4; k++) {
                int x1 = dirs[k] + x0, y1 = dirs[k + 1] + x1;
                if (x1 < 0 || x1 >= m || y1 < 0 || y1 >= n || vis[x1][y1]) {
                    continue;
                }

                if (grid[x1][y1] == 0) {
                    continue;
                }
                // 这个步骤为核心 求 两个点之间花费

                int cost = 1;
                if (isUseMan) {
                    cost += ManhattanDistance(x1, y1, ed[0], ed[1]);
                } else {
                    cost += SingleDistance(x1, y1, ed[0], ed[1]);
                }
                if (cost < dis[x1][y1]) {
                    dis[x1][y1] = cost;
                    pq.add(new int[]{x1, y1, cost});
                }
            }

        }
        return inf;
    }

    static class Node implements Comparable<Node> {
        int x, y;
        double cost;

        Node(int x, int y, double cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(this.cost, o.cost);
        }
    }


    public static int dijkstraGridBFSAStartODistance(int[][] grid, int[] st, int[] ed) {
        int m = grid.length, n = grid[0].length;
        if (st[0] < 0 || st[0] >= m || st[1] < 0 || st[1] >= m || ed[0] < 0 || ed[0] >= m || ed[1] < 0 || ed[1] >= m) {
            return -1;
        }
        double[][] dis = new double[m][n];
        int inf = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dis[i][j] = inf;
            }
        }
        boolean[][] vis = new boolean[m][n];
        PriorityQueue<Node> pq = new PriorityQueue<>(); // 根据权重排序
        int x0 = st[0], y0 = st[1]; // 起始点
        dis[x0][y0] = ODistance(st[0], st[1], ed[0], ed[1]);
        pq.add(new Node(x0, y0, dis[x0][y0]));
        int[] dirs = {-1, 0, 1, 0, 1};

        while (!pq.isEmpty()) {
            Node p = pq.poll();
            x0 = p.x;
            y0 = p.y;
            if (x0 == ed[0] && y0 == ed[1]) {
                return (int) dis[ed[0]][ed[1]]; // 找到目标点
            }
            if (vis[x0][y0]) {
                continue;
            }
            vis[x0][y0] = true;
            for (int k = 0; k < 4; k++) {
                int x1 = dirs[k] + x0, y1 = dirs[k + 1] + x1;
                if (x1 < 0 || x1 >= m || y1 < 0 || y1 >= n || vis[x1][y1]) {
                    continue;
                }
                if (grid[x1][y1] == 0) {
                    continue;
                }
                // 这个步骤为核心 求 两个点之间花费
                double cost = ODistance(x1, y1, ed[0], ed[1]);

                if (cost < dis[x1][y1]) {
                    dis[x1][y1] = cost;
                    pq.add(new Node(x1, y1, cost));
                }
            }

        }
        return inf;
    }


    // 求两个点的曼哈顿距离
    public static int ManhattanDistance(int x, int y, int targetX, int targetY) {
        return Math.abs(x - targetX) + Math.abs(y - targetY);
    }

    // 对角线距离
    public static int SingleDistance(int x, int y, int targetX, int targetY) {
        return Math.max(Math.abs(x - targetX), Math.abs(y - targetY));
    }

    // 直线距离
    // 用 勾股定理
    public static double ODistance(int x, int y, int targetX, int targetY) {
        return Math.sqrt(Math.abs(x - targetX) * 1L * Math.abs(x - targetX) + Math.abs(y - targetY) * 1L * Math.abs(y - targetY));
    }


    /**
     * dijkstra 求
     *
     * @param grid
     * @param st
     * @param ed
     * @return
     */

    public static int dijkstraGrid(int[][] grid, int[] st, int[] ed) {
        int m = grid.length, n = grid[0].length;
        if (st[0] < 0 || st[0] >= m || st[1] < 0 || st[1] >= m || ed[0] < 0 || ed[0] >= m || ed[1] < 0 || ed[1] >= m) {
            return -1;
        }
        int[][] dis = new int[m][n];
        int inf = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dis[i][j] = inf;
            }
        }

        boolean[][] vis = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]); // 根据权重排序

        int x0 = st[0], y0 = st[1]; // 起始点
        dis[x0][y0] = 1;
        pq.add(new int[]{x0, y0, 1});


        int[] dirs = {-1, 0, 1, 0, 1};

        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            x0 = p[0];
            y0 = p[1];
            int wt0 = p[2];
            if (x0 == ed[0] && y0 == ed[1]) {
                return dis[ed[0]][ed[1]]; // 找到目标点
            }
            if (vis[x0][y0]) {
                continue;
            }
            vis[x0][y0] = true;

            // 分层搜索
            for (int k = 0; k <= 3; k++) {
                int x1 = dirs[k] + x0, y1 = dirs[k + 1] + x1;
                if (x1 < 0 || x1 >= m || y1 < 0 || y1 >= n || vis[x1][y1]) {
                    continue;
                }
                if (grid[x1][y1] == 0) {
                    continue;
                }
                // 这个步骤为核心 求 两个点之间花费
                int cost = dis[x0][y0] + grid[x1][y1];

                if (cost < dis[x1][y1]) {
                    dis[x1][y1] = cost;
                    pq.add(new int[]{x1, y1, cost});
                }
            }

        }
        return inf;
    }


    public static int bfs(int[][] grid, int[] st, int[] ed) {

        return -1;
    }


    public static void main(String[] args) {


        long l1 = 0;
        long l2 = 0;

        boolean isOK = true;

        // 对数器验证
        for (int i = 0; i < 1000; i++) {
            int row = 1 + (int) (Math.random() * 1000);
            int col = 1 + (int) (Math.random() * 1000);
            int[][] grid = new int[row][col];

            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    grid[r][c] = Math.random() >= 0.5 ? 1 : 0;
                }
            }


            int st0 = (int) (Math.random() * row);
            int st1 = (int) (Math.random() * col);

            int ed0 = (int) (Math.random() * row);
            int ed1 = (int) (Math.random() * col);

            int[] st = new int[]{st0, st1};
            int[] ed = new int[]{ed0, ed1};


            long s1 = System.currentTimeMillis();
            int dis1 = dijkstraGrid(grid, st, ed);
            long s2 = System.currentTimeMillis();
            l1 += (s2 - s1);

            s1 = System.currentTimeMillis();
            int dis2 = dijkstraGridBFSAStart(grid, st, ed);
            // int dis2 = dijkstraGridBFSAStartODistance(grid, st, ed);
            s2 = System.currentTimeMillis();
            l2 += (s2 - s1);

            if (dis1 != dis2) {
                isOK = false;
                break;
            }

        }

        if (isOK) {
            System.out.println("dijkstra cost time : " + l1 + "ms");
            System.out.println("A  start cost time : " + l2 + "ms");
        } else {
            System.out.println("Error");
        }


    }

}
