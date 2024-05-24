package leetcode.contest.weekly.w_300.w_380.d;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 3006. 找出数组中的美丽下标 I
 *
 * 给你一个下标从 0 开始的字符串 s 、字符串 a 、字符串 b 和一个整数 k 。
 * 如果下标 i 满足以下条件，则认为它是一个 美丽下标：
 * 	0 <= i <= s.length - a.length
 * 	s[i..(i + a.length - 1)] == a
 * 	存在下标 j 使得：
 *
 * 		0 <= j <= s.length - b.length
 * 		s[j..(j + b.length - 1)] == b
 * 		|j - i| <= k
 *
 *
 * 以数组形式按 从小到大排序 返回美丽下标。
 *
 * 示例 1：
 * 输入：s = "isawsquirrelnearmysquirrelhouseohmy", a = "my", b = "squirrel", k = 15
 * 输出：[16,33]
 * 解释：存在 2 个美丽下标：[16,33]。
 * - 下标 16 是美丽下标，因为 s[16..17] == "my" ，且存在下标 4 ，满足 s[4..11] == "squirrel" 且 |16 - 4| <= 15 。
 * - 下标 33 是美丽下标，因为 s[33..34] == "my" ，且存在下标 18 ，满足 s[18..25] == "squirrel" 且 |33 - 18| <= 15 。
 * 因此返回 [16,33] 作为结果。
 *
 * 示例 2：
 * 输入：s = "abcd", a = "a", b = "a", k = 4
 * 输出：[0]
 * 解释：存在 1 个美丽下标：[0]。
 * - 下标 0 是美丽下标，因为 s[0..0] == "a" ，且存在下标 0 ，满足 s[0..0] == "a" 且 |0 - 0| <= 4 。
 * 因此返回 [0] 作为结果。
 *
 * 提示：
 * 	1 <= k <= s.length <= 10^5
 * 	1 <= a.length, b.length <= 10
 * 	s、a、和 b 只包含小写英文字母。
 *
 * @author: wuxin0011
 * @Description: 找出数组中的美丽下标 I
 * @url https://leetcode.cn/problems/find-beautiful-indices-in-the-given-array-i/description/
 */
@SuppressWarnings("all")
public class D {
    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "beautifulIndices", "in.txt");
    }

    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        List<Integer> ans = new ArrayList<>();
        char[] ss = s.toCharArray();
        char[] as = a.toCharArray();
        char[] bs = b.toCharArray();
        List<Integer> a_pos = kmpSearch(ss, as);
        List<Integer> b_pos = kmpSearch(ss, bs);
        int pre = 0;
        for (int i = 0; i < a_pos.size(); i++) {
            for (int j = pre; j < b_pos.size(); j++) {
                if (Math.abs(a_pos.get(i) - b_pos.get(j)) <= k) {
                    ans.add(a_pos.get(i));
                    pre = j;
                    break;
                }
            }
        }
        return ans;
    }

    public static List<Integer> kmpSearch(char[] text, char[] pattern) {
        int[] nexts = new int[pattern.length];
        List<Integer> ans = new ArrayList<>();
        int m = text.length, n = pattern.length;
        for (int i = 1, cnt = 0; i < n; i++) {
            while (cnt > 0 && pattern[i] != pattern[cnt]) {
                cnt = nexts[cnt - 1];
            }
            if (pattern[i] == pattern[cnt]) cnt++;
            nexts[i] = cnt;
        }

        for (int i = 0, cnt = 0; i < m; i++) {
            while (cnt > 0 && text[i] != pattern[cnt]) {
                cnt = nexts[cnt - 1];
            }
            if (text[i] == pattern[cnt]) cnt++;
            if (cnt == n) {
                ans.add(i - n + 1);
                cnt = nexts[cnt - 1];
            }
        }
        return ans;
    }


}
