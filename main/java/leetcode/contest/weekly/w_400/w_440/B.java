package leetcode.contest.weekly.w_400.w_440;

import code_generation.utils.IoUtil;
import code_generation.annotation.TestCaseGroup;
import java.util.*;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-440/problems/choose-k-elements-with-maximum-sum">选出和最大的 K 个元素</a>
 * @title: 选出和最大的 K 个元素
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class,"findMaxSum","B.txt");
    }
     

    public long[] findMaxSum(int[] nums1, int[] nums2, int k) {
        long sums = 0;
        int n = nums1.length;
        long[] ans = new long[n];
        Arrays.fill(ans,-1);
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids,i->i);
        Arrays.sort(ids,(x,y)->nums1[x] - nums1[y]);
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 0; i < n; ) {
            int st = i;
            while(i < n && nums1[ids[st]] == nums1[ids[i]])i++;
            for(int j = st;j < i && j < n;j++) {
                ans[ids[j]] = sums;
            }
            for(int j = st;j < i && j < n;j++) {
                sums += nums2[ids[j]];
                q.add(nums2[ids[j]]);
                while(q.size() > k) sums -= q.poll();
            }
        }
        return ans;
    }




  

}