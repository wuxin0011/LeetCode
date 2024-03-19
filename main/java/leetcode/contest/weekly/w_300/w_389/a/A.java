package leetcode.contest.weekly.w_300.w_389.a;

import code_generation.utils.IoUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: wuxin0011
 * @Description:
 * @url https://leetcode.cn/problems/existence-of-a-substring-in-a-string-and-its-reverse/description/
 * @title
 */
public class A {
    public static void main(String[] args) {
        IoUtil.testUtil(A.class, IoUtil.DEFAULT_METHOD_NAME, "in.txt");
    }


    public boolean isSubstringPresent(String s) {
        int n = s.length();
        if (n == 1) return false;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < n - 1; i++) {
            String t = s.substring(i, Math.min(i + 2, n));
            set.add(t);
        }
        s = new StringBuilder().append(s).reverse().toString();
        for (int i = 0; i < n - 1; i++) {
            String t = s.substring(i, Math.min(i + 2, n));
            if (set.contains(t)) return true;
        }
        return false;
    }
}
