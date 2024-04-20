package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/combination-sum
 * @title: 组合总和
 */
public class Code_0049_39 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0049_39.class, "combinationSum", "txt_file\\Code_0049_39.txt");
    }

    List<List<Integer>> ans = new ArrayList<>();
    int[] candidates;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.candidates = candidates;
        // Arrays.sort(this.candidates);
        f(0, target, new ArrayList<>());
        return ans;
    }


    public void f(int idx, int sum, List<Integer> path) {
        if (sum == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (idx >= candidates.length || sum < 0) {
            return;
        }
        path.add(candidates[idx]);
        f(idx, sum - candidates[idx], path);
        path.remove(path.size() - 1);
        f(idx + 1, sum, path);
    }


}