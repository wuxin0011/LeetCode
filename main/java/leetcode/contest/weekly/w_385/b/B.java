package leetcode.contest.weekly.w_385.b;

import leetcode.utils.IoUtil;

import java.util.HashSet;

/**
 * @author: wuxin0011
 * @Description:
 */
public class B {

    // 使用前缀树或者hashmap 构建
    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "longestCommonPrefix1");
        IoUtil.testUtil(B.class, "longestCommonPrefix");
    }

    static class Trie {
        Trie[] nexts;
        boolean isEnd;

        public Trie() {
            this.nexts = new Trie[10];
            this.isEnd = false;
        }
    }

    public static void buildTrie(Trie root, int[] a) {
        for (int num : a) {
            Trie cur = root;
            String s = String.valueOf(num);
            for (int i = 0; i < s.length(); i++) {
                int index = s.charAt(i) - '0';
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new Trie();
                }
                cur = cur.nexts[index];
            }
            cur.isEnd = true;
        }
    }

    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        int max = 0;
        Trie root = new Trie();
        buildTrie(root, arr2);
        for (int n : arr1) {
            Trie cur = root;
            String s = String.valueOf(n);
            int i = 0;
            while (i < s.length()) {
                int idx = s.charAt(i) - '0';
                if(cur.nexts[idx] == null){
                    break;
                }
                cur = cur.nexts[idx];
                i++;
            }
            max = Math.max(i, max);

        }
        return max;
    }


    public int longestCommonPrefix1(int[] arr1, int[] arr2) {
        int max = 0;
        HashSet<String> s1 = toString(arr1);
        HashSet<String> s2 = toString(arr2);
        for (String s : s1) {
            if (s2.contains(s)) {
                max = Math.max(max, s.length());
            }
        }
        return max;
    }

    public static HashSet<String> toString(int[] arr) {


        HashSet<String> st = new HashSet<>();
        for (int integer : arr) {
            String s = String.valueOf(integer);
            char[] ss = s.toCharArray();
            for (int i = ss.length; i >= 0; i--) {
                String t = new String(ss, 0, i);
                if (st.contains(t)) break;
                st.add(t);
            }
        }

        return st;
    }


}
