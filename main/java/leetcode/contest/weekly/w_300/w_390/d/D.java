package leetcode.contest.weekly.w_300.w_390.d;

import code_generation.utils.IoUtil;

import java.util.PriorityQueue;

/**
 * @author: wuxin0011
 * @Description:
 * @url
 * @title
 */
public class D {
    public static void main(String[] args) {

        IoUtil.testUtil(D.class, IoUtil.DEFAULT_METHOD_NAME, "D.txt");
    }


    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        int n = wordsQuery.length;
        int[] ans = new int[n];
//        PriorityQueue<int[]> q = new PriorityQueue<>((a,b)->a[0]==b[0] ? a[1]-b[1]:);
//        for (int i = 0; i < wordsContainer.length; i++) {
//            q.add(new int[]{wordsQuery.length, i});
//        }

        for (int i = 0; i < wordsQuery.length; i++) {
            ans[i] = 0;
        }
        return null;
    }


    public static class Trie {
        Trie[] next;
        boolean isEnd;
        int path;

        public Trie() {
            this.next = new Trie[26];
            this.isEnd = false;
            this.path = 0;
        }
    }


    public static void build(String[] ss, Trie root) {
        for (String s : ss) {
            Trie cur = root;
            for (int i = s.length() - 1; i >= 0; i--) {
                int id = s.charAt(i) - 'a';
                if (cur.next[id] == null) {
                    cur.next[id] = new Trie();
                }
                cur.next[id].path++;
                cur = cur.next[id];
            }
            cur.isEnd = true;
        }
    }


    public static int search(String[] ss, Trie root, String s) {

        int i = 0;

        return i;
    }


}
