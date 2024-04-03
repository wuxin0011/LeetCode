package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;
import java.util.*;
import code_generation.bean.TreeNode;
/**
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree
 * @title: find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree
 */
public class Code_0033_1379 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0033_1379.class,"getTargetCopy","txt_file\\Code_0033_1379.txt");
    }

    // TODO 这个本地测试没通过 经过排查 仅仅是找出节点的引用而不是所有 先记录着吧
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if(cloned == null || original == null){
            return null;
        }
        if(cloned.val == target.val){
            return cloned;
        }
        TreeNode left = getTargetCopy(original,cloned.left,target);

        if(left != null){
            return left;
        }
        return  getTargetCopy(original, cloned.right, target);
     }

}