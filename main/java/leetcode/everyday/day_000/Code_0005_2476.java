package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;
import code_generation.bean.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description: 二叉搜索树最近节点查询
 * @url https://leetcode.cn/problems/closest-nodes-queries-in-a-binary-search-tree/description/
 */
@SuppressWarnings("all")
public class Code_0005_2476 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0005_2476.class, "closestNodes", "./txt_file/Code_0005_2476.txt");
    }




    public void inOrder(TreeNode node,List<Integer> vals) {
        if (node == null)
            return;
        inOrder(node.left,vals);
        vals.add(node.val);
        inOrder(node.right,vals);
    }


    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> vals = new ArrayList<>();
        List<Integer> temp = null;
        inOrder(root,vals);
        int n = vals.size();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = vals.get(i);
        }
        for (int q : queries) {
            temp = new ArrayList<>();
            int idx = lowBound(arr, q);

            // idx == n not find
            int max = idx == n ? -1 : arr[idx];
            // 不是最右侧vals由于是二叉搜索树因此只需要移动一次
            if( idx == n || arr[idx] != q){
                idx--;
            }
            int min = idx < 0 ? -1 : arr[idx];
            temp.add(min);
            temp.add(max);
            ans.add(temp);
        }
        return ans;
    }

    public static int lowBound(int[] arr, int target) {
        int left = -1, right = arr.length;
        while (left + 1 < right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }


}
