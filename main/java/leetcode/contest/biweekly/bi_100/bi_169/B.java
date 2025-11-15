package leetcode.contest.biweekly.bi_100.bi_169;

import code_generation.utils.IoUtil;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/biweekly-contest-169/problems/count-subarrays-with-majority-element-i">统计主要元素子数组数目 I</a>
 * @title: 统计主要元素子数组数目 I
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class,"countMajoritySubarrays","B.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    public int countMajoritySubarrays(int[] a, int t) {
        int n = a.length;
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i+1]=pre[i]+(a[i]==t?1:0);
        }
        if(pre[n]==0)return 0;
        int ans=0;
        for (int i = 0; i < n; i++) {
            for(int j = i;j<n;j++){
                if((pre[j+1]-pre[i]) * 2 > (j-i+1))ans++;
            }
        }
        return ans;
    }

  

}