package leetcode.contest.weekly.w_400.w_454;

import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-454/problems/count-special-triplets">统计特殊三元组</a>
 * @title: 统计特殊三元组
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class,"specialTriplets","B.txt");
    }
    private static final int MOD = (int)1e9 + 7;

    public int specialTriplets(int[] a) {
        int n = a.length;
        Map<Long,Integer> pre = new HashMap<>();
        Map<Long,Integer> suf = new HashMap<>();
        for(int i = 0;i < n;i++) {
            suf.merge(a[i]*1L,1,Integer::sum);
        }
        long ans = 0;
        for(int i = 0;i < n;i++) {
            suf.merge(a[i]*1L,-1,Integer::sum);
            if(i>=1 && i <= n - 2) {
                long x = 1L * a[i] * 2;
                ans += 1L * pre.getOrDefault(x,0) * suf.getOrDefault(x,0);
                ans %= MOD;
            }
            pre.merge(a[i]*1L,1,Integer::sum);
        }
        return (int)ans;

    }

  

}