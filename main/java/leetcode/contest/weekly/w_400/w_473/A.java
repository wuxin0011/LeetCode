package leetcode.contest.weekly.w_400.w_473;

import code_generation.utils.IoUtil;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-473/problems/remove-zeros-in-decimal-representation">移除十进制表示中的所有零</a>
 * @title: 移除十进制表示中的所有零
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"removeZeros","A.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    public long removeZeros(long n) {
        return Long.valueOf(String.valueOf(n).replace("0",""));
	}

  

}