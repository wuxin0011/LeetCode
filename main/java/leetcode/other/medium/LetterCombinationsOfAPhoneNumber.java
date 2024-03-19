package leetcode.other.medium;

import code_generation.annotation.Description;
import code_generation.enums.Difficulty;
import code_generation.utils.InvocationHandlerMethodTime;
import code_generation.LogarithmicDevice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description:
 */
@SuppressWarnings("all")
@Description(value = "电话号码的字母组合", url = "https://leetcode.cn/problems/letter-combinations-of-a-phone-number/", diff = Difficulty.MEDIUM)
public class LetterCombinationsOfAPhoneNumber implements LogarithmicDevice {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(LetterCombinationsOfAPhoneNumber.class);
    }


    private static final String[] Mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<String>();
        if(digits == null || digits.length() == 0) return list;
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


    @Override
    public void logarithmicDevice() {

        String s = "23";
        List<String> strings = this.letterCombinations(s);
        System.out.println(strings);
    }
}
