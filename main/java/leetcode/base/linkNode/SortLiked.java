package leetcode.base.linkNode;

import leetcode.annotation.Description;
import leetcode.utils.Bean.Difficulty;
import leetcode.utils.Bean.Tag;
import leetcode.utils.InvocationHandlerMethodTime;
import leetcode.utils.ListNode;
import leetcode.utils.LogarithmicDevice;

/**
 * @author: wuxin0011
 * @Description:
 */

@Description(value = "排序链表", url = "https://leetcode.cn/problems/sort-list/", tag = Tag.LINKLIST, diff = Difficulty.MEDIUM)
public class SortLiked implements LogarithmicDevice {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(SortLiked.class);
    }

    public ListNode sortList(ListNode head) {
        // 1、递归结束条件
        if (head == null || head.next == null) {
            return head;
        }

        // 2、找到链表中间节点并断开链表 & 递归下探
        ListNode midNode = middleNode(head);
        // 右边节点
        ListNode rightHead = midNode.next;
        midNode.next = null; // important !!!

        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);

        // 3、当前层业务操作（合并有序链表）
        return mergeTwoLists(left, right);
    }

    //  找到链表中间节点（876. 链表的中间结点）
    private ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // 合并两个有序链表（21. 合并两个有序链表）
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode sentry = new ListNode(-1);
        ListNode curr = sentry;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }

            curr = curr.next;
        }

        curr.next = l1 != null ? l1 : l2;
        return sentry.next;
    }


    @Override
    public void logarithmicDevice() {

        ListNode listNode = ListNode.createListNode(10, -1, 2, 4, 6);
        ListNode.print(listNode);
        ListNode listNode1 = this.sortList(listNode);
        ListNode.print(listNode1);
    }
}
