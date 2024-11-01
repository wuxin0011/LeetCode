package leetcode.contest.biweekly.bi_100.bi_131.d;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

 /**
 * 100314. 物块放置查询
 * <p>
 * 有一条无限长的数轴，原点在 0 处，沿着 x 轴 正方向无限延伸。
 * 给你一个二维数组queries，它包含两种操作：
 * 操作类型 1 ：queries[i] = [1, x]。在距离原点 x处建一个障碍物。数据保证当操作执行的时候，位置 x处 没有任何障碍物。
 * 操作类型 2 ：queries[i] = [2, x, sz]。判断在数轴范围[0, x]内是否可以放置一个长度为sz的物块，这个物块需要完全放置在范围[0, x]内。如果物块与任何障碍物有重合，那么这个物块不能被放置，但物块可以与障碍物刚好接触。注意，你只是进行查询，并不是真的放置这个物块。每个查询都是相互独立的。
 * 请你返回一个 boolean 数组results，如果第i 个操作类型 2 的操作你可以放置物块，那么results[i]为true，否则为 false。
 * <p>
 * 示例 1：
 * 输入：queries = [[1,2],[2,3,3],[2,3,1],[2,2,2]]
 * 输出：[false,true,true]
 * 解释：
 * 查询 0 ，在x = 2处放置一个障碍物。在x = 3之前任何大小不超过 2 的物块都可以被放置。
 * <p>
 * 示例 2：
 * 输入：queries = [[1,7],[2,7,6],[1,2],[2,7,5],[2,7,6]]
 * 输出：[true,true,false]
 * 解释：
 * 查询 0 在x = 7处放置一个障碍物。在x = 7之前任何大小不超过 7 的物块都可以被放置。
 * 查询 2 在x = 2处放置一个障碍物。现在，在x = 7之前任何大小不超过 5 的物块可以被放置，x = 2之前任何大小不超过 2 的物块可以被放置。
 * <p>
 * 提示：
 * 1 <= queries.length <= 15 * 10^4
 * 2 <= queries[i].length <= 3
 * 1 <= queries[i][0] <= 2
 * 1 <= x, sz <= min(5 * 10^4, 3 * queries.length)
 * 输入保证操作 1 中，x处不会有障碍物。
 * 输入保证至少有一个操作类型 2 。
 *
 * @author: wuxin0011
 * @Description: <a href="https://leetcode.cn/problems/block-placement-queries/submissions/577381149/">提交链接</a>
 * @url <a href="https://leetcode.cn/problems/block-placement-queries">物块放置查询</a>
 * @title 物块放置查询
 */
public class D {
    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "getResults", "D.txt");
    }


    public List<Boolean> getResults(int[][] queries) {

        // 查询线段树最大区间
        int mx = 0;
        for (int[] q : queries) {
            if (q[0] == 1) {
                mx = Math.max(mx, q[1]);
            } else if (q[0] == 2) {
                mx = Math.max(mx, q[1]);
                mx = Math.max(mx, q[2]);
            }
        }
        mx++;
//        t = new int[2 << (32 - Integer.numberOfLeadingZeros(mx))];
        t = new int[mx << 2];


        TreeSet<Integer> treeSet = new TreeSet<>(Integer::compareTo);

        // 设置哨兵节点
        treeSet.add(0);
        treeSet.add(mx);

        List<Boolean> ans = new ArrayList<>();
        for (int[] q : queries) {
            int x = q[1];
            Integer pre = treeSet.floor(x - 1);
            assert pre != null;
            if (q[0] == 1) {
                Integer next = treeSet.ceiling(x);
                assert next != null;
                treeSet.add(x);
                update(0, mx, 1, x, x - pre);
                update(0, mx, 1, next, next - x);
            } else if (q[0] == 2) {
                int cur = Math.max(x - pre, query(pre, 0, mx, 1));
                ans.add(cur >= q[2]);
            }
        }
        return ans;
    }

    int[] t;


    void update(int l, int r, int i, int index, int val) {
        if (l == r) {
            // update
            t[i] = val;
        } else {
            int mid = l + ((r - l) >> 1);
            if (index <= mid) {
                update(l, mid, i << 1, index, val);
            } else {
                update(mid + 1, r, i << 1 | 1, index, val);
            }
            t[i] = Math.max(t[i << 1], t[i << 1 | 1]);
        }
    }


    int query(int qr, int l, int r, int i) {
        if (r <= qr) {
            return t[i];
        } else {
            int mid = l + ((r - l) >> 1);
            if (qr <= mid) {
                return query(qr, l, mid, i << 1);
            }
            // 注意如果查询内容位于右侧 应该同时合并左边和右边的最大值！！！
            return Math.max(t[i << 1],query(qr, mid + 1, r, i << 1 | 1));
        }
    }
}
