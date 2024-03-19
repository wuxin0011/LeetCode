package leetcode.everyday;

import code_generation.utils.IoUtil;
import code_generation.utils.TreeNode;

import java.util.*;

/**
 * @author: wuxin0011
 * @Description: 二叉树中的第 K 大层和
 * @url https://leetcode.cn/problems/kth-largest-sum-in-a-binary-tree/
 */
@SuppressWarnings("all")
public class Code_0004_2583 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0004_2583.class, "kthLargestLevelSum", "./txt_file/Code_0004_2583.txt");
    }


    public long kthLargestLevelSum(TreeNode root, int k) {
        long ans = 0;
        if( root == null){
            return -1;
        }

        Deque<TreeNode> q = new ArrayDeque<>();
        List<Long> ls = new ArrayList<>();
        q.add(root);
        int level = 0;
        while(!q.isEmpty()){
            int size = q.size();
            level++;
            long tot = 0;
            while(size>0){
                size--;
                TreeNode node  = q.poll();
                tot += node.val*1L;
                if(node.left != null){
                    q.add(node.left);
                }
                if(node.right != null){
                    q.add(node.right);
                }
            }
            ls.add(tot);
        }
        if(k>level) return  -1;
        Collections.sort(ls);
        for(int i = ls.size()-1;i>=0;i--){
            k--;
            if( k == 0) return ls.get(i);
        }
        return -1;
    }

}
