package leetcode.contest.weekly.w_400.w_405.b;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-405/problems/generate-binary-strings-without-adjacent-zeros
 * @title: 生成不含相邻零的二进制字符串
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "validStrings", "B.txt", false, false);
    }


    public List<String> validStrings(int n) {
        this.n = n;
        path = new char[n];
        dfs(0);
        dfs(1);
        ans.addAll(set);
        return ans;
    }

    char[] map = {'0', '1'};

    char[] path;
    int n;
    int l = 0;
    Set<String> set = new HashSet<>();
    List<String> ans = new ArrayList<>();

    public void dfs(int pre) {
        if (l == n) {
            set.add(new String(path));
            return;
        }
        if (pre == 0) {
            path[l] = map[1];
            l++;
            dfs(1);
            l--;
        } else {
            path[l] = map[0];
            l++;
            dfs(0);
            l--;
            path[l] = map[1];
            l++;
            dfs(1);
            l--;
        }
    }


}