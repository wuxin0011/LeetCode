package code_generation.parse.string;

import code_generation.parse.ParseOneList;
import code_generation.utils.ReflectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description:
 */
@SuppressWarnings("all")
public class ParseStringOneList implements ParseOneList<List<String>> {

    @Override
    public List<String> parse(String input) {
        return convert(ReflectUtils.toString(input));
    }

    @Override
    public List<String> convert(String input) {
        return convert(input, '[', ']', ',');
    }

    @Override
    public List<String> convert(String input, char start, char end, char flag) {
        List<String> ls = new ArrayList<>();
        char[] a = new char[]{start, end};
        String b = new String(a);
        if (b.equals(input)) return ls;
        StringBuilder sb = null;
        char[] cs = input.toCharArray();
        for (char c : cs) {
            if (c == start) {
                sb = new StringBuilder();
                continue;
            }
            if (sb == null) break;
            if (c == end) {
                ls.add(sb.toString());
                break;
            } else if (c == flag) {
                ls.add(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(c);
            }
        }
        return ls;
    }

}
