package template.graph;


import java.util.Arrays;

/**
 * @see leetcode.ox3if.graph.topological_sorting.Solution_0001.Solution
 * @see leetcode.ox3if.graph.topological_sorting.Solution_0002.Solution
 * @see leetcode.ox3if.graph.topological_sorting.Solution_0003.Solution
 * @author: wuxin0011
 * @Description: 链式向前星建图
 */
public class LinkStartBuildGraph {

    @FunctionalInterface
    public interface Apply {
        void run();
    }

    static final boolean isDirect = false; // 是否是无向图
    static final boolean isWeigth = false; // 是否有权重
    static int NO = 26;
    static int MAX = 1001;
    static int cnt = 0;
    static final int[] head, next, to, w;
    static final int N = 26;
    static final int[] queue = new int[N];
    static final int[] in = new int[N];
    static int r, l, t;

    static {
        MAX = isDirect ? MAX * 2 + 1 : MAX;
        head = new int[NO];
        next = new int[MAX];
        to = new int[MAX];
        w = isWeigth ? null : new int[MAX];
    }

    static void clear(int n) {
        clear(n, () -> {
        });
    }

    static void clear(int n, Apply a) {
        cnt = 0;
        for (int i = 0; i < n; i++) {
            if (i < NO)
                head[i] = -1;
            next[i] = 0;
            to[i] = 0;
            if (w != null) {
                w[i] = 0;
            }
        }
        a.run();
    }

    static void addEdge(int fa, int nxt, int we) {
        next[cnt] = head[fa];
        to[cnt] = nxt;
        if (w != null) {
            w[cnt] = we;
        }
        head[fa] = cnt;
        cnt++;
        if (isDirect) {
            next[cnt] = head[nxt];
            to[cnt] = fa;
            if (w != null) {
                w[cnt] = we;
            }
            head[nxt] = cnt;
            cnt++;
        }
    }

    public static void topSort() {
        clear(MAX, () -> {
            Arrays.fill(in, -1);
            r = l = t = 0;
        });

        while (l < r) {
            int i = queue[l++];
            for (int ei = head[i]; ei != -1; ei = next[ei]) {
                in[to[ei]]--;
                if (in[to[ei]] == 0) {
                    queue[r++] = to[ei];
                }
            }
        }
    }
}
