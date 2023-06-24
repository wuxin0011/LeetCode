package com.wuxin.linkNode;

import com.wuxin.utils.ListNode;

/**
 * @author: wuxin0011
 * @Description: 合并两个升序单链表
 */
public class MergeTwoLists {

    public static void main(String[] args) {

        ListNode l1 = ListNode.createListNode(1, 3, 5, 7, 9);
        ListNode l2 = ListNode.createListNode(1, 2, 4, 8, 10);

        ListNode listNode1 = new MergeTwoLists().mergeTwoLists(l1, l2);
        ListNode.print(listNode1);

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
}
