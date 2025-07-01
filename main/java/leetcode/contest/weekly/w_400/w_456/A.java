package leetcode.contest.weekly.w_400.w_456;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-456/problems/partition-string">分割字符串</a>
 * @title: 分割字符串
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"partitionString","A.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    public List<String> partitionString(String s) {
        List<String> ans = new ArrayList<>();
        Set<String> set = new HashSet<>();
        String c = "";
        for(int i = 0;i<s.length();i++) {
            c += s.charAt(i);
            if(!set.contains(c)) {
                set.add(c);
                ans.add(c);
                c = "";
            }
        }
        return ans;
	}

  

}