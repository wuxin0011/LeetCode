package leetcode.contest.weekly.w_400.w_439;

import code_generation.utils.IoUtil;
import code_generation.annotation.TestCaseGroup;
import java.util.*;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-439/problems/find-the-largest-almost-missing-integer">找出最大的几近缺失整数</a>
 * @title: 找出最大的几近缺失整数
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"largestInteger","A.txt");
    }
     

    public int largestInteger(int[] a, int k) {
        int n = a.length;
        int[] d = new int[100];
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if(i >= k) {
                q.pollFirst();
            }
            q.add(a[i]);
            if(i >= k - 1) {
                for(int x : q){
                    d[x]++;
                }
            }
        }
        q = new ArrayDeque<>();
        int ans = -1;
        for (int i = 0; i < n; i++) {
            if(i >= k) {
                q.pollFirst();
            }
            q.add(a[i]);
            if(i >= k - 1) {
                int[] cnt = new int[100];
                for(int x : q){
                    cnt[x]++;
                    if(cnt[x] == d[x]) {
                        ans = Math.max(ans,x);
                    }
                }
            }
        }
        return ans;
    }

  

}