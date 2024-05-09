package leetcode.ox3if.data_struct.heap.base.Solution_0018;

import code_generation.bean.TreeNode;
import code_generation.utils.IoUtil;

import java.util.TreeSet;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/QO5KpG
 * @title: 二叉搜索树染色
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "getNumber", "in.txt");
        IoUtil.testUtil(Solution.class, "getNumber2", "in.txt");
    }


    // 分析
    // 二叉搜索树，使用中序遍历 转换为有序数组
    // 可以从后面遍历 因为最终是返回最后一次填充
    // 可以将最后一次的数据从数组中删除
    // 返回范围内数据 可以使用 红黑树 TreeSet
    TreeSet<Integer> set = new TreeSet<>();

    public void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        set.add(node.val);
        dfs(node.right);
    }


    public int getNumber(TreeNode root, int[][] ops) {
        int cnt = 0, n = ops.length;
        dfs(root);
        for (int i = n - 1; i >= 0; i--) {
            int[] op = ops[i];
            TreeSet<Integer> o = new TreeSet<>(set.subSet(op[1] - 1, false, op[2] + 1, false));
            if (o.size() == 0) continue;
            if (op[0] == 1) {
                cnt += o.size();
            }
            for (Integer idx : o) {
                set.remove(idx);
            }
        }
        return cnt;
    }


    public static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }

    }


    // public String toString() {
    // 	StringBuilder sb = new StringBuilder();
    // 	Node node = root;
    // 	sb.append("[");
    // 	while (node != null) {
    // 		sb.append(node.val);
    // 		if (node.next != null) {
    // 			sb.append(",");
    // 		}
    // 		node = node.next;
    // 	}
    // 	sb.append("]");
    // 	return sb.toString();
    // }

    void build(int val) {
        if (root == null) {
            root = new Node(val);
            cur = root;
        } else {
            cur.next = new Node(val);
            cur = cur.next;
        }
        this.size++;
    }

    Node root;
    Node cur;
    int size = 0;

    public int find(int l, int r) {
        if (this.size == 0 || root == null || root.val > r) {
            return 0;
        }
        int n = 0;
        // System.out.println("query = {" + l + "," + r + "}" + ",dataList = " + this);
        Node dummy = new Node(-1); // 创建一个虚拟节点作为头结点的前一个节点
        dummy.next = root;
        Node prev = dummy;
        Node current = root;

        while (current != null) {
            Node next = current.next;
            if (current.val >= l && current.val <= r) {
                prev.next = next; // 删除当前节点
                n++;
            } else {
                prev = current;
            }

            if (current.val > r) {
                break;
            }

            current = next;
        }

        this.size -= n;
        root = dummy.next; // 更新头结点
        // System.out.println("dataList = " + this + "\n");
        return n;
    }

    public void dfs2(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs2(node.left);
        build(node.val);
        dfs2(node.right);

    }

    public int getNumber2(TreeNode root, int[][] ops) {
        dfs2(root);
        int n = ops.length;
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            int[] op = ops[i];
            if (this.size == 0)
                break;
            int cnt = find(op[1], op[2]);
            if (op[0] == 1) {
                ans += cnt;
            }
        }
        return ans;
    }
}