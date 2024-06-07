package leetcode.top_interview_150.generate_parentheses;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 22. 括号生成
 *
 * 数字 n代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 *
 * 提示：
 * 	1 <= n <= 8
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/generate-parentheses
 * @title: 括号生成
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "generateParenthesis", "in.txt", false, false);
    }


    List<String> ans = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        char[] path = new char[n * 2];
        dfs(0, 0, path);
        return ans;
    }

    /**
     * @param i 当前索引
     * @param l 左括号数量 右括号可以通过 i - l 得到
     */
    public void dfs(int i, int l, char[] path) {
        if (i == path.length) {
            ans.add(new String(path));
            return;
        }
        if (l < path.length / 2) {
            path[i] = '(';
            dfs(i + 1, l + 1, path);
        }

        if (i - l < l) {
            path[i] = ')';
            dfs(i + 1, l, path);
        }
    }


}