package com.wuxin.linkNode;

import com.wuxin.utils.ListNode;

/**
 * @author: wuxin001
 * @Description:
 */
public class NodeAddSum {


    public static void main(String[] args) {
        ListNode listp1 = ListNode.createListNode(9, 9, 9, 9, 9, 9, 9);
        ListNode listp2 = ListNode.createListNode(9, 9, 9, 9);
        ListNode.print(listp1);
        ListNode.print(listp2);

        ListNode listNode = twoNodeAddSum(listp1, listp2);
        ListNode.print(listNode);
    }

    public static ListNode twoNodeAddSum(ListNode p1, ListNode p2) {
        if (p1 == null && p2 == null) {
            return null;
        }
        ListNode listNode = new ListNode(-1);
        ListNode cur = listNode;

        int c = 0;
        while (p1 != null || p2 != null) {
            // 记录上次进位数
            int val = c;

            if (p1 != null) {
                val += p1.val;
                p1 = p1.next;
            }
            if (p2 != null) {
                val += p2.val;
                p2 = p2.next;
            }
            // 进位数
            c = (int) Math.floor((double) val / 10);
            val = val % 10;

            // 当前指针位置后移动一位
            cur.next = new ListNode(val);
            cur = cur.next;
        }


        return listNode.next;
    }




}
