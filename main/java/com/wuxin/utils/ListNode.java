package com.wuxin.utils;

import java.util.Objects;

/**
 * @author: wuxin0011
 * @Description: 单链表生成类
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this(val, null);
    }


    public ListNode(int val, ListNode listNode) {
        this.val = val;
        this.next = listNode;
    }


    /**
     * 构造节点
     *
     * @param args 节点数据
     * @return 返回一个 Node节点
     */
    public static ListNode createListNode(int... args) {
        ListNode temp = new ListNode(-1);
        ListNode cur = temp;
        for (int val : args) {
            cur.next = new ListNode(val);
            cur = cur.next;
        }
        return temp.next;
    }

    /**
     * 打印一个单链表数据结构
     *
     * @param node
     */
    public static void print(ListNode node) {
        if (Objects.isNull(node)) {
            System.out.println("node is null");
            return;
        }
        while (node != null) {
            System.out.print(node.val);
            if (node.next != null) {
                System.out.print(",");
            }
            node = node.next;
        }
        System.out.println();
    }
}
