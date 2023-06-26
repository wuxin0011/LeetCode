package com.wuxin.linkNode;

import com.wuxin.utils.InvocationHandlerMethodTime;
import com.wuxin.utils.ListNode;
import com.wuxin.utils.LogarithmicDevice;

import java.util.HashSet;

/**
 * @author: wuxin0011
 * @Description: 判断单链表是否存在环
 */
public class CheckNodeHasRing implements LogarithmicDevice {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(CheckNodeHasRing.class);
    }

    public static void printHasRing(ListNode node, int strategy) {
        ListNode result = null;
        if (strategy == 1) {
            result = hasRingNode(node);
        } else if (strategy == 2) {
            result = hasRingNode2(node);
        }
        if (result != null) {
            System.out.println("有环" + result.val);
        } else {
            System.out.println("无环");
        }

    }


    /**
     * 判断一个链表是否存在环 如果存在环就返回
     * 方法一使用快慢指针
     *
     * @return SingleNode
     */
    public static ListNode hasRingNode(ListNode node) {
        // 两个节点的链表不能成环
        if (node == null || node.next == null || node.next.next == null) {
            return null;
        }
        // 创建一个款
        ListNode slow = node.next;
        ListNode fast = node.next.next;
        // 如果存在环形  快慢节点指针一定会相遇
        while (slow.next != null && fast.next.next != null) {
            // 快慢指针相遇
            if (slow == fast) {
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        // ！！！
        fast = node;
        while (slow != null && fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 方法二 使用 HashSet
     * 判断单链表是否存在环
     *
     * @param node SingleNode
     * @return SingleNode
     */
    public static ListNode hasRingNode2(ListNode node) {
        // 两个节点的链表不能成环
        if (node == null || node.next == null) {
            return null;
        }
        HashSet<ListNode> set = new HashSet<>();

        ListNode temp = node;
        while (temp != null) {
            if (set.contains(temp)) {
                return temp;
            }
            set.add(temp);
            temp = temp.next;
        }

        return null;
    }

    @Override
    public void logarithmicDevice() {
        //  测试一个环形链表
        ListNode l1 = ListNode.createListNode(1, 2, 3, 5, 6, 7, 8, 9);

        // 获取 l1 一个节点 这里是 6
        ListNode temp = l1.next.next.next.next;

        // 获取末尾 节点
        ListNode cur = l1;
        while (cur.next != null) {
            cur = cur.next;
        }
        // 得到最后一个节点 让他指向 temp 节点就成了一个有环链表
        cur.next = temp;


        //  测试一个普通单链表 输出结果应该是
        ListNode l2 = ListNode.createListNode(1, 2, 3, 5);

        // 测试结果
        System.out.println("===============方案一==============");
        printHasRing(l1, 1);
        printHasRing(l2, 1);


        System.out.println("===============方案二==============");
        printHasRing(l1, 2);
        printHasRing(l2, 2);
    }
}
