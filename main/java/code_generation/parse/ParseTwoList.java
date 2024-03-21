package code_generation.parse;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: wuxin0011
 * @Description:
 */

@SuppressWarnings("all")
public interface ParseTwoList<T> extends ParseList<List<List<T>>> {
    default List<List<String>> b(String input, char start, char end, char flag) {
        StringBuilder sb = null;
        List<List<String>> ls = new ArrayList<>();
        List<String> temp = null;
        Stack<Character> sk = new Stack<>();
        char[] cs = input.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            if (c == start) {
                sk.push(c);
                if (sk.size() == 2) temp = new ArrayList<>();
            } else if (c == end) {
                if (!sk.isEmpty()) {
                    sk.pop();
                }
                if (sb != null && temp != null) {
                    temp.add(sb.toString());
                    ls.add(temp);
                }
                sb = null;
                temp = null;
            } else if (c == flag) {
                if (temp != null && sb != null) {
                    temp.add(sb.toString());
                    sb = new StringBuilder();
                }
            } else {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(c);
            }
        }

        return ls;
    }
}
