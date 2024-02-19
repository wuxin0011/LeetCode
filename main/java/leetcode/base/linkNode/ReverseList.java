package leetcode.base.linkNode;

import leetcode.annotation.Description;
import leetcode.utils.Bean.Difficulty;
import leetcode.utils.Bean.Tag;
import leetcode.utils.InvocationHandlerMethodTime;
import leetcode.utils.ListNode;
import leetcode.utils.LogarithmicDevice;

/**
 * @author: wuxin0011
 * @Description: 反转链表
 */

@Description(value = "反转链表", diff = Difficulty.MEDIUM, tag = Tag.LINKLIST)
public class ReverseList implements LogarithmicDevice {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(ReverseList.class);

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

    @Override
    public void logarithmicDevice() {
        ListNode listNode = ListNode.createListNode(1, 2, 3, 4, 5);
        ListNode.print(listNode);
        ListNode listNode1 = new ReverseList().reverseList(listNode);
        ListNode.print(listNode1);
    }
}
