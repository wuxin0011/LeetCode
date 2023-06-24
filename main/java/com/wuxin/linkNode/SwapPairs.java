package com.wuxin.linkNode;

import com.wuxin.utils.ListNode;

/**
 * @author: wuxin0011
 * @Description:
 */
public class SwapPairs {

    public static void main(String[] args) {
        ListNode l1 = ListNode.createListNode(1, 2, 3, 5, 6, 7, 8, 9);
        ListNode listNode = new SwapPairs().swapPairs(l1);
        ListNode.print(listNode);
    }

    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }
}
