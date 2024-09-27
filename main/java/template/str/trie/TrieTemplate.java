package template.str.trie;

/**
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

    static void insert(Trie root, char[] s) {
        Trie cur = root;
        for (char c : s) {
            int idx = c - 'a';
            if (cur.next[idx] == null) {
                cur.next[idx] = new Trie();
            }
            cur.path++;
            cur = cur.next[idx];
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
