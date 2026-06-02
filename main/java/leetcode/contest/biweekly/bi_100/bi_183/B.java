package leetcode.contest.biweekly.bi_100.bi_183;

import code_generation.utils.IoUtil;
import code_generation.annotation.TestCaseGroup;
import java.util.*;
/**
 * @author: qitongwei
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/biweekly-contest-183/problems/minimum-operations-to-make-array-modulo-alternating-i">使数组变为模交替数组的最少操作次数 I</a>
 * @title: 使数组变为模交替数组的最少操作次数 I
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class,"minOperations","B.txt");
    }
    private static final int MOD = (int)1e9 + 7;

    public int minOperations(int[] a, int k) {
        int n =a.length;
        int ans = Integer.MAX_VALUE;
        int v = a[0] % k;
        int cnt = 0;
        for(int i = 0;i<n;i++){
            cnt += a[i] % k == v ? 1 : 0;
        }
        if(cnt==n){
            return Math.min(n / 2,n - n / 2);
        }
        for(int x = 0;x<k;x++){
            for(int y = 0;y<k;y++){
                if(x==y)continue;
                cnt = 0;
                for(int i = 0;i<n;i++){
                    v = a[i] % k;
                    if((i%2)==1){
                        // x
                        int v0 = Math.abs(v - x);
                        cnt += Math.min(v0,k - v0);
                    }else{
                        // y
                        int v0 = Math.abs(v - y);
                        cnt += Math.min(v0,k - v0);
                    }
                }
                ans = Math.min(ans,cnt);
            }
        }
        return ans;
    }


}