package leetcode.contest.weekly.w_400.w_401.b;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   https://leetcode.cn/contest/weekly-contest-401/problems/find-the-n-th-value-after-k-seconds
 * @title: K 秒后第 N 个元素的值
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class,"valueAfterKSeconds","B.txt");
    }
     


    public static int MOD = (int)1e9 + 7;
    public int valueAfterKSeconds(int n, int k) {
        long[] arr = new long[n];
        Arrays.fill(arr,1);
        while(k > 0) {
            for(int i = 1; i < arr.length;i++ ){
                arr[i] = (arr[i] + arr[i - 1]) % MOD;
            }
            k--;
        }
        return (int) arr[n-1];
	}

  

}