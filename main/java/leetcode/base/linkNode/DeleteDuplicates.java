package leetcode.base.linkNode;


import code_generation.annotation.Description;
import code_generation.enums.Difficulty;
import code_generation.enums.Tag;
import code_generation.proxy.InvocationHandlerMethodTime;
import code_generation.bean.ListNode;
import code_generation.LogarithmicDevice;

/**
 * @author: wuxin0011
 * @Description: 删除排序链表中的重复元素
 */

@Description(value = "删除排序链表中的重复元素", diff = Difficulty.MEDIUM, tag = Tag.LINKLIST)
public class DeleteDuplicates implements LogarithmicDevice {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(DeleteDuplicates.class);
    }

    public ListNode deleteDuplicates(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;

        if (head.val == next.val) {
            // 由于是排序的 因此只需要遇到相等的 val 直接跳过
            while (next != null && next.val == head.val) {
                next = next.next;
            }
            // 如果值出现相等，之前节点需要被下一个不相等的节点覆盖
            head = deleteDuplicates(next);
        } else {
            // 递归 下一步做该步骤同样事情
            head.next = deleteDuplicates(next);
        }

        return head;
    }

    public ListNode deleteDuplicates2(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode result = new ListNode(-1);
        result.next = head;
        ListNode cur = result;

        while (cur.next != null && cur.next.next != null) {

            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                // 指针后移动
                cur = cur.next;
            }

        }
        return result.next;
    }

    @Override
    public void logarithmicDevice() {
        ListNode l1 = ListNode.createListNode(1, 2, 2, 3, 3, 4, 5, 6, 7, 8);
        ListNode.print(l1);
        ListNode listNode = new DeleteDuplicates().deleteDuplicates2(l1);
        ListNode.print(listNode);
    }
}
