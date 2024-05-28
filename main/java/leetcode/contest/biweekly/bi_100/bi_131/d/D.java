package leetcode.contest.biweekly.bi_100.bi_131.d;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.List;

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
 * @Description:
 * @url
 * @title
 */
public class D {
    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "getResults1", "D.txt");
    }


    public List<Boolean> getResults(int[][] queries) {
        List<Boolean> ans = new ArrayList<>();
        return ans;
    }

    public List<Boolean> getResults1(int[][] queries) {
        List<Boolean> ans = new ArrayList<>();
        int inf = 1000001;
        boolean[] vis = new boolean[inf];
        for (int[] query : queries) {
            int op = query[0];
            int x = query[1];
            if (op == 1) {
                vis[x] = false;
            } else {
                int w = query[2];
                int l = 1;
                boolean ok = false;
                for (int k = 0; k <= x; k++) {
                    if (!vis[k]) {
                        l = 1;
                        continue;
                    }
                    l++;
                    if (l >= w) {
                        ok = true;
                        break;
                    }
                }
                ans.add(ok);
            }
        }

        return ans;
    }
}
