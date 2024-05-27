package leetcode.contest.biweekly.bi_100.bi_131.c;

import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 100313. 所有球里面不同颜色的数目
 * <p>
 * 给你一个整数limit和一个大小为 n x 2的二维数组queries。
 * 总共有limit + 1个球，每个球的编号为[0, limit]中一个互不相同的数字。一开始，所有球都没有颜色。queries中每次操作的格式为[x, y]，你需要将球x染上颜色y。每次操作之后，你需要求出所有球中不同颜色的数目。
 * 请你返回一个长度为 n的数组result，其中result[i]是第 i次操作以后不同颜色的数目。
 * 注意，没有染色的球不算作一种颜色。
 * <p>
 * 示例 1：
 * 输入：limit = 4, queries = [[1,4],[2,5],[1,3],[3,4]]
 * 输出：[1,2,2,3]
 * 解释：
 * 操作 0后，球 1 颜色为 4 。
 * 操作 1 后，球 1 颜色为4 ，球 2 颜色为 5 。
 * 操作 2 后，球 1 颜色为 3 ，球 2 颜色为 5 。
 * 操作 3 后，球 1 颜色为 3 ，球 2 颜色为 5 ，球 3 颜色为 4 。
 * <p>
 * 示例 2：
 * 输入：limit = 4, queries = [[0,1],[1,2],[2,2],[3,4],[4,5]]
 * 输出：[1,2,2,3,4]
 * 解释：
 * 操作 0后，球 0颜色为 1。
 * 操作 1后，球 0颜色为 1 ，球 1 颜色为 2 。
 * 操作 2后，球 0颜色为 1 ，球 1 和 2颜色为 2 。
 * 操作 3 后，球 0颜色为 1 ，球 1 和 2颜色为 2 ，球 3 颜色为 4 。
 * 操作 4后，球 0颜色为 1 ，球 1 和 2颜色为 2 ，球 3 颜色为 4 ，球 4 颜色为 5 。
 * <p>
 * 提示：
 * 1 <= limit <= 10^9
 * 1 <= n == queries.length <= 10^5
 * queries[i].length == 2
 * 0 <= queries[i][0] <= limit
 * 1 <= queries[i][1] <= 10^9
 *
 * @author: wuxin0011
 * @Description:
 * @url
 * @title
 */
public class C {
    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "queryResults", "C.txt");
    }


    public int[] queryResults(int limit, int[][] queries) {

        int n = queries.length;
        int[] ans = new int[n];
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> colorMap = new HashMap<>();
        int type = 0;
        for (int i = 0; i < queries.length; i++) {
            int NO = queries[i][0];
            int color = queries[i][1];
            Integer colorKey = map.get(NO);
            if (colorKey != null) {
                int t = colorMap.get(colorKey) - 1;
                if (t == 0) {
                    type--;
                }
                colorMap.put(colorKey, t);
            }
            int colorSize = colorMap.getOrDefault(color, 0) + 1;
            if (colorSize == 1) {
                type++;
            }
            map.put(NO, color);
            colorMap.put(color, colorSize);
            ans[i] = type;
        }
        return ans;
    }
}
