package leetcode.contest.biweekly.bi_100.bi_126.c;

import leetcode.utils.IoUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author: wuxin0011
 * @Description: 这道题目设计很巧妙 ， 可以直接吧带问好索引全部拿出来保存住，然后使用小顶堆 计算当前字符，按照问号多少计算 最后题目要求
 * 最后题目要求字典序最小，因此可以对 问号的结果排序，然后将结果填入数组中
 * @url https://leetcode.cn/contest/biweekly-contest-126/problems/replace-question-marks-in-string-to-minimize-its-value/
 * @title
 */
public class C {
    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "minimizeStringValue", "in.txt");
    }


    public String minimizeStringValue(String s) {
        int[] h = new int[26];
        char[] cs = s.toCharArray();
        int n = cs.length;
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            if (c == '?') {
                ids.add(i); // 保存问号的索引
            } else {
                h[c - 'a']++;
            }

        }
        // System.out.println(qs);

        List<Character> res = new ArrayList<>();
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        for (int i = 0; i < h.length; i++) {
            q.add(new int[]{h[i], i}); // 初始化 q
        }
        for (int i = 0; i < ids.size(); i++) {
            int[] p = q.poll();
            if (p == null) break;
            p[0]++;
            char c = (char) (p[1] + 'a');
            res.add(c);
            q.add(p);
        }

        // 字典序最小
        Collections.sort(res);
        for (int i = 0; i < res.size(); i++) {
            cs[ids.get(i)] = res.get(i); // 将问号修改后的字符填充到cs中
        }
        return new String(cs);
    }
}
