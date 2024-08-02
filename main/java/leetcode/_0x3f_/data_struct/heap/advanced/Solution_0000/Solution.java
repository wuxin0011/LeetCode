package leetcode._0x3f_.data_struct.heap.advanced.Solution_0000;

import code_generation.bean.ListNode;
import code_generation.utils.IoUtil;

import java.util.PriorityQueue;

/**
 *
 *
 * 23. 合并 K 个升序链表
 *
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 示例 1：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 *
 * 示例 2：
 * 输入：lists = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：lists = [[]]
 * 输出：[]
 *
 * 提示：
 * 	k == lists.length
 * 	0 <= k <= 10^4
 * 	0 <= lists[i].length <= 500
 * 	-10^4 <= lists[i][j] <= 10^4
 * 	lists[i] 按 升序 排列
 * 	lists[i].length 的总和不超过 10^4
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/merge-k-sorted-lists
 * @title: merge-k-sorted-lists
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "mergeKLists", "in.txt");
    }


    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0 ) {
            return null;
        }
        PriorityQueue<ListNode> q = new PriorityQueue<>((a, b) -> a.val - b.val);
        ListNode tmep = new ListNode(-1);
        ListNode cur = tmep;
        for (ListNode node : lists) {
            if(node==null)continue;
            q.add(node);
        }
        while (!q.isEmpty()) {
            ListNode t = q.poll();
            cur.next = t;
            cur = cur.next;
            t = t.next;
            if (t != null) {
                q.add(t);
            }
        }
        return tmep.next;
    }


}