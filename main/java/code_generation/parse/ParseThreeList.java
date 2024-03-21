package code_generation.parse;

import code_generation.utils.ReflectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: wuxin0011
 * @Description:
 */
@SuppressWarnings("all")
public interface ParseThreeList<T> extends ParseList<List<List<List<T>>>> {



    default List<List<List<String>>> a(String input, char start, char end, char flag) {
        List<List<List<String>>> ans = new ArrayList<>();
        char[] charArray = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean f1 = false, f2 = false, f3 = false;
        List<List<String>> d = new ArrayList<>();
        List<String> t = new ArrayList<>();
        Stack<Character> sk = new Stack<>();
        for (char c : charArray) {
            if (ReflectUtils.isIgnore(c))
            if (c == start) {
                sk.push(c);
                if (sk.size() == 2) {
                    d = new ArrayList<>();
                } else if (sk.size() == 3) {
                    t = new ArrayList<>();
                }
            } else if (c == end) {
                if (!sk.isEmpty()) {
                    sk.pop();
                }
                if (sk.size() == 1) {
                    ans.add(d);
                } else if (sk.size() == 2) {
                    if (sb != null) {
                        t.add(sb.toString());
                        sb = null;
                    }
                    d.add(t);
                }
            } else if (c == flag) {
                if (sb != null) {
                    t.add(sb.toString());
                }
                sb = null;
            } else {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(c);
            }
        }
        return ans;
    }
}
