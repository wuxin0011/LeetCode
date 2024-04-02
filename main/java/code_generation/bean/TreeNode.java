package code_generation.bean;

import code_generation.utils.StringUtils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringJoiner;

/**
 * @author: wuxin0011
 * @Description: 二叉树
 */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this(val, null, null);
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TreeNode.class.getSimpleName() + "[", "]")
                .add("val=" + val)
                .toString();
    }


    public static TreeNode fullTreeNode() {
        // 第一层节点
        TreeNode treeNode = new TreeNode(0);

        // 第二层节点
        treeNode.left = new TreeNode(1);
        treeNode.right = new TreeNode(2);

        // 第三层节点
        treeNode.left.left = new TreeNode(3);
        treeNode.left.right = new TreeNode(4);

        treeNode.right.left = new TreeNode(5);
        treeNode.right.right = new TreeNode(6);

        // 第四层次节点
        treeNode.left.left.left = new TreeNode(7);
        treeNode.left.left.right = new TreeNode(8);

        treeNode.left.right.left = new TreeNode(9);
        treeNode.left.right.right = new TreeNode(10);

        treeNode.right.left.left = new TreeNode(11);
        treeNode.right.left.right = new TreeNode(12);

        treeNode.right.right.left = new TreeNode(13);
        treeNode.right.right.right = new TreeNode(14);

        return treeNode;
    }


    /**
     * 通过宽度遍历方式构建二叉树
     *
     * @param ls 序列化后的二叉树
     * @return 返回二叉树的根节点
     */
    public static TreeNode widthBuildTreeNode(String[] ls) {

        if (ls == null || ls.length == 0 || ls[0] == null || isNullNode(ls[0])) {
            return null;
        }
        Deque<TreeNode> q = new ArrayDeque<>();
        TreeNode root = new TreeNode(Integer.parseInt(ls[0]));
        q.add(root);
        int i = 1;
        while (i < ls.length && !q.isEmpty()) {
            int s = q.size();
            while (s > 0 && i < ls.length) {
                s--;
                TreeNode node = q.poll();
                if (node == null) continue;
                if (!isNullNode(ls[i])) {
                    TreeNode left = new TreeNode(Integer.parseInt(ls[i]));
                    node.left = left;
                    q.add(left);
                }
                i++;
                if (i >= ls.length) break;
                if (!isNullNode(ls[i])) {
                    TreeNode right = new TreeNode(Integer.parseInt(ls[i]));
                    node.right = right;
                    q.add(right);
                }
                i++;
            }
        }
        return root;
    }

    // "null" support lc
    // "#" supprot niuke
    private static boolean isNullNode(String s) {
        return s == null || s.length() == 0 || "null".equals(s) || "#".equals(s);
    }


    public static TreeNode widthBuildTreeNode(Integer[] ls) {
        if (ls == null || ls.length == 0 || ls[0] == null || "null".equals(String.valueOf(ls[0]))) {
            return null;
        }
        String[] s = new String[ls.length];
        for (int i = 0; i < ls.length; i++) {
            s[i] = String.valueOf(ls[i]);
        }
        return widthBuildTreeNode(s);
    }


}
