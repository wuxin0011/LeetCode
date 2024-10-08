package leetcode.top_interview_150.a_phone_number;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description:
 */
public class a_phone_number {
    public static void main(String[] args) {
        IoUtil.testUtil(a_phone_number.class, "letterCombinations","in.txt");
    }

    private static final String[] Mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<String>();
        if (digits == null || digits.length() == 0) return list;
        char[] path = new char[digits.length()];
        dfs(0, path, list, digits);
        return list;
    }

    public static void dfs(int idx, char[] path, List<String> ans, String digits) {
        // 递归终止条件
        if (idx == path.length) {
            ans.add(new String(path));
            return;
        }

        // 继续遍历
        for (char c : Mapping[digits.charAt(idx) - '0'].toCharArray()) {
            path[idx] = c;
            dfs(idx + 1, path, ans, digits);
        }
    }
}
