package leetcode.contest.weekly.w_300.w_390.d;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url
 * @title
 */
public class D {
    public static void main(String[] args) {

        IoUtil.testUtil(D.class, "stringIndices", "D.txt");
    }


    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        int n = wordsQuery.length;
        Trie root = new Trie();
        build(wordsContainer, root);
        int[] ans = new int[n];
        for (int i = 0; i < wordsQuery.length; i++) {
            ans[i] = query(wordsQuery[i], root);
        }
        return ans;
    }


    public static class Trie {
        Trie[] next;
        int l = Integer.MAX_VALUE;
        int id = 0;

        public Trie() {
            this.next = new Trie[26];
        }
    }


    public static void build(String[] ss, Trie root) {
        for (int curId = 0; curId < ss.length; curId++) {
            Trie cur = root;
            String s = ss[curId];
            int n = s.length();
            if (n < cur.l) {
                cur.l = n;
                cur.id = curId;
            }
            // 倒叙创建前缀树路径
            for (int i = s.length() - 1; i >= 0; i--) {
                int id = s.charAt(i) - 'a';
                if (cur.next[id] == null) {
                    cur.next[id] = new Trie();
                }
                cur = cur.next[id];
                if (n < cur.l) {
                    cur.l = n;
                    cur.id = curId;
                }
            }
        }

    }


    public static int query(String str, Trie root) {
        Trie cur = root;
        for (int i = str.length() - 1; i >= 0 && cur.next[str.charAt(i) - 'a'] != null; i--) {
            cur = cur.next[str.charAt(i) - 'a'];
        }
        return cur.id;
    }

}
