package leetcode.contest.biweekly.bi_100.bi_152;

import code_generation.utils.IoUtil;

import java.util.TreeMap;

/**
 * 3485. 删除元素后 K 个字符串的最长公共前缀
 * <p>
 * 给你一个字符串数组 words 和一个整数 k。
 * 对于范围 [0, words.length - 1] 中的每个下标i，在移除第i个元素后的剩余数组中，找到任意k 个字符串（k个下标 互不相同）的 最长公共前缀 的 长度。
 * 返回一个数组 answer，其中 answer[i] 是 i个元素的答案。如果移除第i个元素后，数组中的字符串少于 k 个，answer[i] 为 0。
 * 一个字符串的 前缀 是一个从字符串的开头开始并延伸到字符串内任何位置的子字符串。
 * 一个 子字符串 是字符串中一段连续的字符序列。
 * <p>
 * 示例 1：
 * 输入： words = ["jump","run","run","jump","run"], k = 2
 * 输出： [3,4,4,3,4]
 * 解释：
 * 移除下标0 处的元素"jump"：
 * words 变为： ["run", "run", "jump", "run"]。 "run" 出现了 3 次。选择任意两个得到的最长公共前缀是 "run" （长度为 3）。
 * 移除下标1 处的元素"run"：
 * words 变为： ["jump", "run", "jump", "run"]。 "jump" 出现了 2 次。选择这两个得到的最长公共前缀是 "jump" （长度为 4）。
 * 移除下标2 处的元素"run"：
 * words 变为： ["jump", "run", "jump", "run"]。 "jump" 出现了 2 次。选择这两个得到的最长公共前缀是 "jump" （长度为 4）。
 * 移除下标3 处的元素"jump"：
 * words 变为： ["jump", "run", "run", "run"]。 "run" 出现了 3 次。选择任意两个得到的最长公共前缀是 "run" （长度为 3）。
 * 移除下标4 处的元素"run"：
 * words 变为： ["jump", "run", "run", "jump"]。 "jump" 出现了 2 次。选择这两个得到的最长公共前缀是 "jump" （长度为 4）。
 * 示例 2：
 * 输入： words = ["dog","racer","car"], k = 2
 * 输出： [0,0,0]
 * 解释：
 * 移除任何元素的结果都是 0。
 * <p>
 * 提示：
 * 1 <= k <= words.length <= 10^5
 * 1 <= words[i].length <= 10^4
 * words[i] 由小写英文字母组成。
 * words[i].length 的总和小于等于 10^5。
 *
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/biweekly-contest-152/problems/longest-common-prefix-of-k-strings-after-removal">删除元素后 K 个字符串的最长公共前缀</a>
 * @title: 删除元素后 K 个字符串的最长公共前缀
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class C {

    static class S1 {
        public static void main(String[] args) {
            IoUtil.testUtil(S1.class, "longestCommonPrefix", "C.txt");
        }


        public int[] longestCommonPrefix(String[] words, int k) {
            int n = words.length;
            int[] ans = new int[n];
            Trie trie = new Trie();
            TreeMap<Integer, Integer> map = new TreeMap<>();
            for (int i = 0; i < n; i++) {
                insert(trie,words[i],k,map,1);
            }
            for (int i = 0; i < n; i++) {
                insert(trie,words[i],k - 1,map,-1);
                ans[i] = map.isEmpty() ? 0 :  map.lastKey();
                insert(trie,words[i],k,map,1);
            }
            return ans;
        }

        static void insert(Trie root, String s,int count,TreeMap<Integer,Integer> map,int cnt) {
            Trie cur = root;
            for (int i = 0; i < s.length(); i++) {
                int idx = s.charAt(i) - 'a';
                if (cur.next[idx] == null) {
                    cur.next[idx] = new Trie();
                }
                cur = cur.next[idx];
                cur.cnt += cnt;
                if(cur.cnt == count) {
                    map.put(i + 1,map.getOrDefault(i + 1,0) + cnt);
                    if(map.get(i + 1) <= 0) {
                        map.remove(i + 1);
                    }
                }
            }
        }

        public static class Trie {
            Trie[] next = new Trie[26];
            int cnt = 0;
        }

    }

}