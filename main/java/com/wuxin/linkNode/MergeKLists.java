package com.wuxin.linkNode;

import com.wuxin.utils.ListNode;

import java.util.ArrayList;

/**
 * @author: wuxin0011
 * @Description: 合并 K 个升序链表
 */
public class MergeKLists {


    public static void main(String[] args) {
        ListNode l1 = ListNode.createListNode(1, 3, 5, 7, 9);
        ListNode l2 = ListNode.createListNode(1, 2, 4, 8, 10);
        ListNode l3 = ListNode.createListNode(999, 12, 11, 123, 3123);
        ListNode l4 = ListNode.createListNode(1, 2, 2, 3, 4);
        ListNode[] listNodes = new ListNode[]{l1, l2, l3, l4};

        ListNode listNode = new MergeKLists().mergeKLists(listNodes);
        ListNode.print(listNode);
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        ListNode cur = new ListNode(-1);
        ListNode temp = cur;
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (ListNode list : lists) {
            // cur.next = mergeTwoLists(list, cur.next);
            while (list != null) {
                arrayList.add(list.val);
                list = list.next;
            }

        }
        arrayList.sort(Integer::compare);

        for (Integer val : arrayList) {
            temp.next = new ListNode(val);
            temp = temp.next;
        }

        return cur.next;
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {

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
