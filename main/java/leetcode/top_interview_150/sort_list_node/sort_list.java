package leetcode.top_interview_150.sort_list_node;

import code_generation.utils.IoUtil;
import code_generation.bean.ListNode;

/**
 * @author: wuxin0011
 * @Description:
 */
public class sort_list {
    public static void main(String[] args) {
        IoUtil.testUtil(sort_list.class,"sortList");
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode midNode = findMidNode(head);
        ListNode rHead = midNode == null ? null : midNode.next;
        if (midNode != null) {
            midNode.next = null;
        }

        ListNode l1 = sortList(head);
        ListNode l2 = sortList(rHead);
        return megreSort(l1, l2);
    }

    public ListNode findMidNode(ListNode root) {
        if (root == null || root.next == null) return root;
        ListNode slow = root;
        ListNode fast = root.next.next;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // 参考归并排序
    public ListNode megreSort(ListNode l1, ListNode l2) {
        ListNode dump = new ListNode(-1);
        ListNode cur = dump;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        return dump.next;
    }

}
