package leetcode.top_interview_150.graph;

import leetcode.utils.IoUtil;

import java.util.*;

/**
 * @author: wuxin0011
 * @Description:
 * @title: 单词接龙 II
 * @url https://leetcode.cn/problems/word-ladder-ii/description/
 */
public class word_ladder_II {
    public static void main(String[] args) {
        IoUtil.testUtil(word_ladder_II.class, "findLadders", "./txt_file/word_ladder_II.txt");
    }


    List<List<String>> ans = new ArrayList<>();
    Set<String> set = new HashSet<>(); // 缓存提前处理的结果
    String beginWord;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0) {
            return ans;
        }
        this.beginWord = beginWord;

        // 检查 end
        int idx = -1;
        boolean has = false;

        for (int i = 0; i < wordList.size(); i++) {
            if (wordList.get(i).equals(endWord)) {
                idx = i;
            }
            if (isConnection(beginWord, wordList.get(i))) {
                has = true;
            }
        }

        // 检查begin and end
        if (idx == -1 || !has) {
            return ans;
        }

        for (int i = 0; i < wordList.size(); i++) {
            for (int j = 0; j < wordList.size(); j++) {
                if (i == j) continue;
                isConnection(wordList.get(i), wordList.get(j));
            }
        }

        Set<String> vis = new HashSet<>();
        vis.add(beginWord);

        Deque<String> q = new ArrayDeque<>();
        List<List<String>> nodes = new ArrayList<>();
        boolean find = false;
        q.add(beginWord);
        while (!q.isEmpty() && !find) {
            nodes.add(new ArrayList<>(q));
            int size = q.size();
            while (size > 0) {
                size--;
                String word = q.poll();
                for (String item : wordList) {
                    if (vis.contains(item)) continue;
                    if (!has(item, word)) continue;
                    if (item.equals(endWord)) {
                        find = true;
                        break;
                    }
                    vis.add(item);
                    q.add(item); //
                }
                if (find) break;
            }
        }
        List<String> step = new ArrayList<>();
        step.add(endWord);
        backtracking(endWord, nodes, nodes.size() - 1, step);
        return ans;
    }

    public void backtracking(String word, List<List<String>> nodes, int level, List<String> step) {
        if (word.equals(beginWord)) {
            List<String> res = new ArrayList<>();
            for (int i = step.size() - 1; i >= 0; i--) {
                res.add(step.get(i));
            }
            ans.add(res);
            return;
        }
        if (level < 0) return; // 最多为N
        for (String item : nodes.get(level)) {
            if (has(item, word)) {
                step.add(item);
                backtracking(item, nodes, level - 1, step);
                step.remove(step.size() - 1);
            }
        }

    }

    public boolean isConnection(String a, String b) {
        // 这个条件可以不要 题目不符合这个 不过也不影响
//        if (a == null || a.length() == 0 || b == null || b.length() == 0 || a.length() != b.length()) {
//            return false;
//        }
        int cnt = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                cnt++;
                if (cnt > 1) {
                    return false;
                }
            }
        }
        String k1 = a + b;
        String k2 = b + a;
        set.add(k1);
        set.add(k2);
        return true;
    }

    public boolean has(String a, String b) {
        return set.contains(a + b);
    }
}
