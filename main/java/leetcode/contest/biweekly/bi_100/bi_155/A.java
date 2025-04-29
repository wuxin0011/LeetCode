package leetcode.contest.biweekly.bi_100.bi_155;

import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/biweekly-contest-155/problems/find-the-most-common-response">找到最常见的回答</a>
 * @title: 找到最常见的回答
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "findCommonResponse", "A.txt");
    }


    public String findCommonResponse(List<List<String>> responses) {
        Map<String, Integer> cnt = new HashMap<>();
        int mx = 0;
        for (List<String> ss : responses) {
            HashSet<String> set = new HashSet<>();
            for (String s : ss) {
                if (set.contains(s)) {
                    continue;
                }
                cnt.merge(s, 1, Integer::sum);
                mx = Math.max(mx, cnt.get(s));
                set.add(s);
            }
        }
        String ans = null;
        for (Map.Entry<String, Integer> item : cnt.entrySet()) {
            if (item.getValue() == mx) {
                if (ans == null || item.getKey().compareTo(ans) < 0) {
                    ans = item.getKey();
                }
            }
        }
        return ans;
    }


}