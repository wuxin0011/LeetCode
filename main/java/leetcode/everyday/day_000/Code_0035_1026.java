package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;
import java.util.*;
import code_generation.bean.TreeNode;
/**
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/maximum-difference-between-node-and-ancestor
 * @title: maximum-difference-between-node-and-ancestor
 */
public class Code_0035_1026 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0035_1026.class,"maxAncestorDiff","txt_file\\Code_0035_1026.txt");
    }
	  

    // 简单的递归
    private int ans;
	 public int maxAncestorDiff(TreeNode root) {
         ans = 0;
         if(root == null ){
             return ans;
         }
        dfs(root,root.val,root.val);
        return ans;
     }

     public  void dfs(TreeNode node,int  min,int max){
        if(node == null){
            ans = Math.max(ans,max - min);
            return;
        }
        min = Math.min(node.val,min);
        max = Math.max(node.val,max);
        dfs(node.left,min,max);
        dfs(node.right,min,max);
     }

}