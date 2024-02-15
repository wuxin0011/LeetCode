package leetcode.linkNode;

import leetcode.annotation.Description;
import leetcode.utils.Bean.Difficulty;
import leetcode.utils.Bean.Tag;
import leetcode.utils.ListNode;

/**
 * @author: wuxin0011
 * @Description:
 */
@Description(value = "删除链表倒数第n个节点", tag = Tag.LINKLIST, diff = Difficulty.MEDIUM)
public class RemoveNthFromEnd {
    public static void main(String[] args) {
        ListNode listNode = ListNode.createListNode(1, 2, 3, 4, 5);
        // listNode = ListNode.createListNode(1);
        int n = 1;
        ListNode listNode1 = new RemoveNthFromEnd().removeNthFromEnd(listNode, n);
        ListNode.print(listNode1);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode p1 = head;
        ListNode p2 = head;

        while (n != 0) {
            p1 = p1.next;
            n--;
        }

        if (p1 == null) {
            return p2.next;
        }

        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        p2.next = p2.next.next;

        return head;
    }
}
