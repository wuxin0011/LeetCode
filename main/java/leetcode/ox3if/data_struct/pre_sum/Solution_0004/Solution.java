package leetcode.ox3if.data_struct.pre_sum.Solution_0004;

import code_generation.utils.IoUtil;

import java.util.TreeSet;

/**
 * 2055. 蜡烛之间的盘子
 * <p>
 * 给你一个长桌子，桌子上盘子和蜡烛排成一列。
 * 给你一个下标从 0开始的字符串s，它只包含字符'*' 和'|'，其中'*'表示一个 盘子，'|'表示一支蜡烛。
 * 同时给你一个下标从 0开始的二维整数数组queries，其中queries[i] = [lefti, righti]表示 子字符串s[lefti...righti]（包含左右端点的字符）。对于每个查询，你需要找到 子字符串中在 两支蜡烛之间的盘子的 数目。如果一个盘子在 子字符串中左边和右边 都至少有一支蜡烛，那么这个盘子满足在 两支蜡烛之间。
 * 比方说，s = "||**||**|*"，查询[3, 8]，表示的是子字符串"*||**|"。子字符串中在两支蜡烛之间的盘子数目为2，子字符串中右边两个盘子在它们左边和右边 都 至少有一支蜡烛。
 * 请你返回一个整数数组answer，其中answer[i]是第i个查询的答案。
 * <p>
 * 示例 1:
 * 输入：s = "**|**|***|", queries = [[2,5],[5,9]]
 * 输出：[2,3]
 * 解释：
 * - queries[0] 有两个盘子在蜡烛之间。
 * - queries[1] 有三个盘子在蜡烛之间。
 * <p>
 * 示例 2:
 * 输入：s = "***|**|*****|**||**|*", queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
 * 输出：[9,0,0,0,0]
 * 解释：
 * - queries[0] 有 9 个盘子在蜡烛之间。
 * - 另一个查询没有盘子在蜡烛之间。
 * <p>
 * 提示：
 * 3 <= s.length <= 10^5
 * s只包含字符'*' 和'|'。
 * 1 <= queries.length <= 10^5
 * queries[i].length == 2
 * 0 <= lefti <= righti < s.length
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/plates-between-candles
 * @title: 蜡烛之间的盘子
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "platesBetweenCandles", "in.txt");
        IoUtil.testUtil(Solution.class, "platesBetweenCandles2", "in.txt");
    }


    public int[] platesBetweenCandles(String s, int[][] queries) {
        char[] cs = s.toCharArray();
        int m = queries.length;
        int n = cs.length;
        int[] pre = new int[n];
        TreeSet<Integer> set = new TreeSet<>();  // 统计蜡烛位置信息
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == '|') {
                set.add(i);
                pre[i] = i - 1 >= 0 ? pre[i - 1] : 0;
            } else {
                pre[i] = i - 1 >= 0 ? (pre[i - 1] + 1) : 1;
            }
        }
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            // 最小的最大
            Integer l = set.ceiling(queries[i][0]);
            if (l == null) {
                continue;
            }

            // 最近的最小
            Integer r = set.floor(queries[i][1]);
            if (r == null || l >= r) {
                continue;
            }
            ans[i] = pre[r] - pre[l];
        }

        return ans;
    }


    public int[] platesBetweenCandles2(String s, int[][] queries) {
        char[] cs = s.toCharArray();
        int m = queries.length;
        int n = cs.length;
        int cnt = 0;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == '|') {
                cnt++;
            }
        }
        int[] pre = new int[n];
        int[] l_pos = new int[cnt];
        int j = 0;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == '|') {
                l_pos[j++] = i;
                pre[i] = i - 1 >= 0 ? pre[i - 1] : 0;
            } else {
                pre[i] = i - 1 >= 0 ? (pre[i - 1] + 1) : 1;
            }
        }
        j = cnt - 1;

        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {

            int l = lowe_bound(l_pos, queries[i][0]);

            int r = lowe_bound(l_pos, queries[i][1] + 1) - 1;
            if (l >= cnt || r <= 0 || l >= r) {
                continue;
            }
            if (l_pos[l] < queries[i][0]) {
                l++;
            }
            if (l_pos[r] > queries[i][1]) {
                r--;
            }
            if (l >= r || l >= cnt) {
                continue;
            }
            ans[i] = pre[l_pos[r]] - pre[l_pos[l]];
        }

        return ans;
    }

    public static int lowe_bound(int[] arr, int t) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] == t) return mid;
            if (arr[mid] > t) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }


}