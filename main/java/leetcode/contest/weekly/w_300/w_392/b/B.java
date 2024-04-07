package leetcode.contest.weekly.w_300.w_392.b;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-392/problems/lexicographically-smallest-string-after-operations-with-constraint
 * @title: 满足距离约束且字典序最小的字符串
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "getSmallestString", "B.txt");
    }


    public String getSmallestString(String s, int k) {
        StringBuilder res = new StringBuilder();

        for(char c : s.toCharArray()){
            if(k == 0){
                res.append(c);
                continue;
            }

            int d = Math.min(c - 'a', 'a' + 26 - c);
            if(d <= k){
                res.append('a');
                k -= d;
                continue;
            }

            int mc = c;
            if(c - k >= 'a'){
                mc = Math.min(mc, c - k);
            }

            if(c + k - 26 >= 'a'){
                mc = Math.min(mc, c + k - 26);
            }

            res.append((char) mc);
            k = 0;
        }


        return res.toString();
    }

}