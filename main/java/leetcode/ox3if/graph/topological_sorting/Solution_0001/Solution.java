package leetcode.ox3if.graph.topological_sorting.Solution_0001;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 * LCR 114. 火星词典
 *
 * 现有一种使用英语字母的外星文语言，这门语言的字母顺序与英语顺序不同。
 * 给定一个字符串列表 words ，作为这门语言的词典，words 中的字符串已经 按这门新语言的字母顺序进行了排序 。
 * 请你根据该词典还原出此语言中已知的字母顺序，并 按字母递增顺序 排列。若不存在合法字母顺序，返回 "" 。若存在多种可能的合法字母顺序，返回其中 任意一种 顺序即可。
 * 字符串 s 字典顺序小于 字符串 t 有两种情况：
 * 	在第一个不同字母处，如果 s 中的字母在这门外星语言的字母顺序中位于 t 中字母之前，那么s 的字典顺序小于 t 。
 * 	如果前面 min(s.length, t.length) 字母都相同，那么 s.length < t.length 时，s 的字典顺序也小于 t 。
 *
 * 示例 1：
 * 输入：words = ["wrt","wrf","er","ett","rftt"]
 * 输出："wertf"
 *
 * 示例 2：
 * 输入：words = ["z","x"]
 * 输出："zx"
 *
 * 示例 3：
 * 输入：words = ["z","x","z"]
 * 输出：""
 * 解释：不存在合法字母顺序，因此返回 "" 。
 *
 * 提示：
 * 	1 <= words.length <= 100
 * 	1 <= words[i].length <= 100
 * 	words[i] 仅由小写英文字母组成
 * 注意：本题与主站 269题相同：
 *
 * @author: wuxin0011
 * @Description: 链式前向星建图 + 拓扑排序
 * @url:   https://leetcode.cn/problems/Jf1JuT
 * @title: 火星词典
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"alienOrder","in.txt");
    }


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

    public String alienOrder(String[] words) {

        clear(MAX, () -> {
            Arrays.fill(in, -1);
            r = l = t = 0;
        });
        int mx = 0;
        for (String w : words) {
            if (mx < w.length())
                mx = w.length();
            for (int i = 0; i < w.length(); i++) {
                in[w.charAt(i) - 'a'] = 0;
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String c = words[i], ne = words[i + 1];
            int j = 0;
            int len = Math.min(c.length(), ne.length());
            while (j < len) {
                if (c.charAt(j) != ne.charAt(j)) {
                    int fa = c.charAt(j) - 'a';
                    int to = ne.charAt(j) - 'a';
                    addEdge(fa, to, 0);
                    in[to]++;
                    break;
                }

                j++;
            }
            // 后面长度 > this
            if (j < c.length() && j == ne.length())
                return "";
        }

        for (int i = 0; i < 26; i++) {
            if (in[i] != -1)
                t++;
            if (in[i] == 0)
                queue[r++] = i;
        }

        StringBuilder sb = new StringBuilder();
        while (l < r) {
            int i = queue[l++];
            sb.append((char) (i + 'a'));
            for (int ei = head[i]; ei != -1; ei = next[ei]) {
                in[to[ei]]--;
                if (in[to[ei]] == 0) {
                    queue[r++] = to[ei];
                }
            }
        }
        return sb.length() == t ? sb.toString() : "";
    }
  

}