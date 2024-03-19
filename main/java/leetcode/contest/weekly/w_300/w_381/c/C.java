package leetcode.contest.weekly.w_300.w_381.c;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description: 输入单词需要的最少按键次数 II
 */
public class C {
    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "minimumPushes");
    }

    public int minimumPushes(String word) {
        int[] help = new int[26];
        for (int i = 0; i < word.length(); i++) {
            help[word.charAt(i) - 'a']++;
        }
        List<Integer> ls = new ArrayList<>();
        for (int v : help) {
            ls.add(v);
        }
        ls.sort((a, b) -> b - a);
        int cnt = 0;
        int idx = 1;
        for (int i = 0; i < ls.size(); ) {
            int t = 8;
            while (i < ls.size() && t > 0) {
                cnt += ls.get(i) * idx;
                i++;
                t--;
            }
            idx++;
        }

        return cnt;
    }
}
