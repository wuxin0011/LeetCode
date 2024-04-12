package nowcoder.Solution.Solution_0000;

import code_generation.utils.IoUtil;
import code_generation.bean.ListNode;
/**
 * @author: wuxin0011
 * @Description:
 * @url:   https://www.nowcoder.com/practice/75e878df47f24fdc9dc3e400ec6058ca?tpId=295&tqId=23286&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj
 * @title: 
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"ReverseList","in.txt");
    }


    public ListNode ReverseList (ListNode head) {
        ListNode pre = null;
        while(head != null){
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

  

}