package leetcode.base.linkNode;

import leetcode.annotation.Description;
import leetcode.utils.Bean.Difficulty;
import leetcode.utils.Bean.Tag;
import leetcode.utils.InvocationHandlerMethodTime;
import leetcode.utils.ListNode;
import leetcode.utils.LogarithmicDevice;

/**
 * @author: wuxin0011
 * @Description:
 */
@Description(value = "前后位置互换", tag = Tag.LINKLIST, diff = Difficulty.MEDIUM)
public class SwapPairs implements LogarithmicDevice {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(SwapPairs.class);
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }

    @Override
    public void logarithmicDevice() {
        ListNode l1 = ListNode.createListNode(1, 2, 3, 5, 6, 7, 8, 9);
        ListNode.print(l1);
        ListNode listNode = this.swapPairs(l1);
        ListNode.print(listNode);
    }
}
