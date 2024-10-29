package leetcode.contest.biweekly.bi_100.bi_141.c;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/biweekly-contest-141/problems/find-maximum-removals-from-source-string">从原字符串里进行删除操作的最多次数</a>
 * @title: 从原字符串里进行删除操作的最多次数
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class,"maxRemovals","C.txt");
    }
     

    int[] cnt = new int[26];
    public int maxRemovals(String source, String pattern, int[] targetIndices) {
        for (int i = 0; i < source.length(); i++) {
            cnt[source.charAt(i) - 'a']++;
        }
        for (int i = 0; i < pattern.length(); i++) {
            cnt[pattern.charAt(i) - 'a']--;
        }
        this.source = source;
        this.pattern = pattern;
        return 0;
    }

    int[] targetIndices;
    String source,pattern;
    int dfs(int i) {
        if(i >= targetIndices.length) {
            return 0;
        }
        int ans = 0;

        // 一定不能删除
        if(cnt[source.charAt(targetIndices[i]) - 'a'] - 1 < 0) {
            ans += dfs(i + 1);
        }

        return 0;
    }


  

}