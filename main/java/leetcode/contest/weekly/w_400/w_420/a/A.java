package leetcode.contest.weekly.w_400.w_420.a;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   https://leetcode.cn/contest/weekly-contest-420/problems/find-the-sequence-of-strings-appeared-on-the-screen
 * @title: 出现在屏幕上的字符串序列
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"stringSequence","A.txt");
    }
     

    public List<String> stringSequence(String target) {
        List<String> ans = new ArrayList<>();
        int n = target.length();
        char[] a = target.toCharArray();
        char[] sk = new char[n];
        Arrays.fill(sk,'a');
        int size = 1;
        for(int i = 0;i < n;) {
            ans.add(new String(sk,0,size));
            while(a[i] != sk[size-1]) {
                sk[size-1]  = (char)((sk[size-1] - 'a' + 1) % 26 + 'a');
                ans.add(new String(sk,0,size));
            }
            size++;
            i++;
        }
        return ans;
	}

  

}