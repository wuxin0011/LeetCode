package com.wuxin.linkNode;

import com.wuxin.utils.ListNode;

import java.util.HashSet;

/**
 * @author: wuxin0011
 * @Description: 判断两个链表是否相交
 */
public class IntersectNode {


    public static void main(String[] args) {

        ListNode l1 = ListNode.createListNode(1, 2, 3, 4, 5, 6, 7, 8);
        ListNode l2 = ListNode.createListNode(1, 2, 3, 4, 5);

        // 创建一个新链表 假设从该部分开始为相交部分
        ListNode intersectNode = ListNode.createListNode(10, 11, 12);
        ListNode temp1 = l1;
        while (temp1 != null) {
            temp1 = temp1.next;
            if (temp1.next == null) {
                temp1.next = intersectNode;
                break;
            }
        }

        // SingleNode.print(l1);

        System.out.println("第二个节点================");
        // SingleNode.print(l2);
        ListNode temp2 = l2;
        while (temp2 != null) {
            temp2 = temp2.next;
            if (temp2.next == null) {
                temp2.next = intersectNode;
                break;
            }
        }
        // SingleNode.print(l2);

        ListNode n = getIntersectNode(l1, l2);
        if (n != null) {
            System.out.println("相交" + n.val);
        } else {
            System.out.println("不相交");

        }

        System.out.println("=============方案二===========");
        n = getIntersectNode1(l1, l2);
        if (n != null) {
            System.out.println("相交" + n.val);
        } else {
            System.out.println("不相交");

        }
    }

    public static ListNode getIntersectNode(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        // 如果头节点是公共的
        if (l1 == l2) {
            return l1;
        }
        int n = 0;
        ListNode c1 = l1;
        ListNode c2 = l2;
        while (c1.next != null) {
            n++;
            c1 = c1.next;
        }
        while (c2.next != null) {
            n--;
            c2 = c2.next;
        }
        // 最后一个节点如果不相等 一定不会相交
        if (c1 != c2) {
            return null;
        }
        // 获取较长一个链表
        c1 = n > 0 ? l1 : l2;
        // 获取较短一个链表
        c2 = c1 == l1 ? l2 : l1;
        // 获取长度的结绝对值
        n = Math.abs(n);
        // 长的链表先走 n 步
        while (n != 0) {
            c1 = c1.next;
            n--;
        }
        // 短链表 开始遍历
        while (c1 != null && c2 != null && c1 != c2) {
            c1 = c1.next;
            c2 = c2.next;
        }
        return c1;
    }


    public static ListNode getIntersectNode1(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        ListNode h1 = l1;
        HashSet<ListNode> set1 = new HashSet<>();
        while (h1 != null) {
            set1.add(h1);
            h1 = h1.next;
        }
        h1 = l2;
        while (h1 != null) {
            h1 = h1.next;
            if (set1.contains(h1)) {
                return h1;
            }
        }
        return null;
    }


}
