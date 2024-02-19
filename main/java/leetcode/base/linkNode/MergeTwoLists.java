package leetcode.base.linkNode;

import leetcode.annotation.Description;
import leetcode.utils.Bean.Difficulty;
import leetcode.utils.Bean.Tag;
import leetcode.utils.InvocationHandlerMethodTime;
import leetcode.utils.ListNode;
import leetcode.utils.LogarithmicDevice;

/**
 * @author: wuxin0011
 * @Description: 合并两个升序单链表
 */

@Description(value = "合并两个升序单链表", tag = Tag.LINKLIST, diff = Difficulty.MEDIUM)
public class MergeTwoLists implements LogarithmicDevice {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(MergeTwoLists.class);
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode l1 = list1;
        ListNode l2 = list2;
        ListNode cur = new ListNode(-1);
        ListNode pre = cur;
        while (l1 != null && l2 != null) {
            int v1 = l1.val;
            int v2 = l2.val;
            if (v1 <= v2) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }

        return pre.next;
    }

    @Override
    public void logarithmicDevice() {
        ListNode l1 = ListNode.createListNode(1, 3, 5, 7, 9);
        ListNode.print(l1);

        ListNode l2 = ListNode.createListNode(1, 2, 4, 8, 10);
        ListNode.print(l2);

        ListNode listNode1 = new MergeTwoLists().mergeTwoLists(l1, l2);
        ListNode.print(listNode1);

    }
}
