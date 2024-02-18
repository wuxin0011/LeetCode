package leetcode.contest.weekly.w_385.d;


import leetcode.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wuxin0011
 * @Description:
 */
public class D {
    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "z_function_ans");
        IoUtil.testUtil(D.class, "countPrefixSuffixPairs");
    }

    static class Trie {
        Trie[] nexts;
        int cnt = 0;

        public Trie() {
            this.nexts = new Trie[26];
            this.cnt = 0;
        }
    }
    public long z_function_ans(String[] words) {
        long ans = 0;
        Trie root = new Trie();
        for (String T : words) {
            char[] t = T.toCharArray();
            int n = t.length;
            int[] z = new int[n];
            int l = 0, r = 0;
            for (int i = 1; i < n; i++) {
                if (i <= r) {
                    z[i] = Math.min(z[i - l], r - i + 1);
                }
                while (i + z[i] < n && t[z[i]] == t[i + z[i]]) {
                    l = i;
                    r = i + z[i];
                    z[i]++;
                }
            }
            z[0] = n;

            Trie cur = root;
            for (int i = 0; i < n; i++) {
                int c = t[i] - 'a';
                if (cur.nexts[c] == null) {
                    cur.nexts[c] = new Trie();
                }
                cur = cur.nexts[c];
                if (z[n - 1 - i] == i + 1) { // t 的长为 i+1 的前后缀相同
                    ans += cur.cnt;
                }
            }
            cur.cnt++;
        }
        return ans;
    }


    public long ans(String[] words) {
        return countPrefixSuffixPairs1(words);
    }


    static class Node {
        Map<Integer, Node> son = new HashMap<>();
        int cnt = 0;
    }


    public long countPrefixSuffixPairs(String[] words) {
        long ans = 0;
        Node root = new Node();
        for (String S : words) {
            char[] s = S.toCharArray();
            int n = s.length;
            Node cur = root;
            for (int i = 0; i < n; i++) {
                int p = (s[i] - 'a') << 5 | (s[n - 1 - i] - 'a');
                cur = cur.son.computeIfAbsent(p, k -> new Node());
                ans += cur.cnt;
            }
            cur.cnt++;
        }
        return ans;
    }



    public static void buildTrie(Trie root, Trie end, String[] words) {
        for (String word : words) {
            Trie cur = root;
            Trie endRoot = end;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new Trie();
                }
                cur.nexts[index].cnt++;
                cur = cur.nexts[index];
            }
            cur.cnt++;
            for (int i = word.length() - 1; i >= 0; i--) {
                int index = word.charAt(i) - 'a';
                if (endRoot.nexts[index] == null) {
                    endRoot.nexts[index] = new Trie();
                }
                endRoot.nexts[index].cnt++;
                endRoot = endRoot.nexts[index];
            }
            endRoot.cnt++;
        }
    }

    public static boolean check(Trie pre, Trie suf, String word) {
        boolean a = true, b = true;
        Trie p1 = pre;
        Trie p2 = suf;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (p1.nexts[idx] == null) {
                a = false;
                break;
            }
            p1 = p1.nexts[idx];
            if (p2.nexts[idx] == null) {
                b = false;
                break;
            }
            p2 = p2.nexts[idx];
        }

        return a && b && p1.cnt > 1 && p2.cnt > 1;
    }


    public int countPrefixSuffixPairs1(String[] words) {
        int cnt = 0;
        Trie preRoot = new Trie();
        Trie endRoot = new Trie();
        buildTrie(preRoot, endRoot, words);
        for (String word : words) {
            if (check(preRoot, endRoot, word)) cnt++;
        }
        return cnt;
    }


}
