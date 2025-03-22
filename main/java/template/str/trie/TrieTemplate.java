package template.str.trie;

/**
 * <a href="https://leetcode.cn/problems/longest-common-prefix-of-k-strings-after-removal/">删除元素后 K 个字符串的最长公共前缀</a>
 * @author: wuxin0011
 * @Description: 字典树
 */
public class TrieTemplate {


    public static class Trie {
        Trie[] next;
        boolean isEnd;

        int path;
        int queryCnt;

        public Trie() {
            this.next = new Trie[26];
            this.isEnd = false;
            this.queryCnt = 0;
            this.path = 0;
        }
    }

    static void insert(Trie root, int cnt,char[] s) {
        Trie cur = root;
        for (char c : s) {
            int idx = c - 'a';
            if (cur.next[idx] == null) {
                cur.next[idx] = new Trie();
            }
            cur = cur.next[idx];
            cur.path += cnt;
        }
        cur.isEnd = true;
    }

    static boolean find(Trie root, String s) {
        return search(root, s.toCharArray()) > 0;
    }

    static int search(Trie root, char[] s) {
        Trie cur = root;
        for (char c : s) {
            int idx = c - 'a';
            if (cur.next[idx] == null) {
                cur.next[idx] = new Trie();
            }
            cur = cur.next[idx];
        }
        if (!cur.isEnd) {
            return -1;
        }
        cur.queryCnt++;
        return cur.queryCnt;
    }
}
