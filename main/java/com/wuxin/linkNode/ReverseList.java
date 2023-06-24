package com.wuxin.linkNode;

import com.wuxin.utils.ListNode;

import java.security.Principal;

/**
 * @author: wuxin0011
 * @Description: 反转链表
 */
public class ReverseList {

    public static void main(String[] args) {
        ListNode listNode = ListNode.createListNode(1, 2, 3, 4, 5);
        ListNode.print(listNode);
        ListNode listNode1 = new ReverseList().reverseList(listNode);
        ListNode.print(listNode1);

    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode temp = head;
        // 虚拟头节点
        ListNode cur = null;
        ListNode next = null;
        while (temp != null) {
            // 下一个的下一个指针指向 上面取出的临时指针
            next = temp.next;
            // 当前指向下一步
            temp.next = cur;
            // 遍历指针移动
            cur = temp;
            // 向后移动
            temp = next;
        }
        return cur;
    }
}
