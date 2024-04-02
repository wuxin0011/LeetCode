package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;
import java.util.*;
import code_generation.bean.TreeNode;
/**
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/all-possible-full-binary-trees
 * @title: all-possible-full-binary-trees
 */
public class Code_0032_894 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0032_894.class,"allPossibleFBT","txt_file\\Code_0032_894.txt");
    }


    private List<TreeNode>[] f;

    public List<TreeNode> allPossibleFBT(int n) {
        f = new List[n + 1];
        return dfs(n);
    }

    private List<TreeNode> dfs(int n) {
        if (f[n] != null) {
            return f[n];
        }
        if (n == 1) {
            List<TreeNode> treeNodes = new ArrayList<>();
            treeNodes.add(new TreeNode(0));
            return treeNodes;
        }
        List<TreeNode> ans = new ArrayList<>();
        for (int i = 0; i < n - 1; ++i) {
            int j = n - 1 - i;
            for (TreeNode left : dfs(i)) {
                for (TreeNode right : dfs(j)) {
                    ans.add(new TreeNode(0, left, right));
                }
            }
        }
        return f[n] = ans;
    }

}