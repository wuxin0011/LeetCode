package leetcode.contest.weekly.w_400.w_406.b;

import code_generation.bean.ListNode;
import code_generation.utils.IoUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-406/problems/delete-nodes-from-linked-list-present-in-array
 * @title: 从链表中移除在数组中存在的节点
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "modifiedList", "B.txt");
    }


    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> s = new HashSet<>();
        for (int num : nums) {
            s.add(num);
        }
        ListNode dump = new ListNode(-1, head);
        ListNode cur = dump;
        while (cur.next != null) {
            if (s.contains(cur.next.val)) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dump.next;
    }


}