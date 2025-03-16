package leetcode.contest.biweekly.bi_100.bi_152;

import code_generation.utils.IoUtil;
import code_generation.annotation.TestCaseGroup;

import java.util.*;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/biweekly-contest-152/problems/longest-common-prefix-of-k-strings-after-removal">删除元素后 K 个字符串的最长公共前缀</a>
 * @title: 删除元素后 K 个字符串的最长公共前缀
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class,"longestCommonPrefix","C.txt");
    }


    public int[] longestCommonPrefix(String[] words, int k) {
        int n = words.length;
        int[] ans = new int[n];
        Trie trie = new Trie();
        for (String word : words) {
            insert(trie,word.toCharArray());
        }
        for(int i = 0;i < n;i++) {
            ans[i] = get(trie,words[i]);
        }
        return ans;
    }


    Map<String,Integer> cache = new HashMap<>();

    int get(Trie trie,String s){
        if(cache.containsKey(s)) return cache.get(s);
        del(trie,s.toCharArray());
        int ans = dfs(trie);
        if(ans>0)ans++;
        insert(trie,s.toCharArray());
        cache.put(s,ans);
        return ans;
    }


    int dfs(Trie trie) {
        if(trie == null) return 0;
        int ans = 0;
        for (int k = 0; k < 26; k++) {
            if(trie.next[k] != null && trie.next[k].path > 1) {
                ans = Math.max(ans,dfs(trie.next[k]) + 1);
            }
        }
        return ans;
    }







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
    static void del(Trie root, char[] s) {
        Trie cur = root;
        for (char c : s) {
            int idx = c - 'a';
            cur.path--;
            cur = cur.next[idx];
        }
    }




}