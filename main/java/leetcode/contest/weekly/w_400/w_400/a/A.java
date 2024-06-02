package leetcode.contest.weekly.w_400.w_400.a;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   https://leetcode.cn/contest/weekly-contest-400/problems/minimum-number-of-chairs-in-a-waiting-room
 * @title: 候诊室中的最少椅子数
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"minimumChairs","A.txt");
    }
     

    public int minimumChairs(String s) {
        // ELELEEL
        char[] chs = s.toCharArray();
        int tot = 0;
        int ans = 0;
        for (char ch : chs) {
            if(ch == 'E') tot++;
            else tot--;
            ans = Math.max(ans,tot);
        }

        return ans;
    }

  

}