package leetcode.contest.weekly.w_400.w_473;

import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 后缀数组解法 https://leetcode.cn/problems/count-distinct-subarrays-divisible-by-k-in-sorted-array/solutions/3815825/hou-zhui-shu-zu-bu-bao-zheng-fei-jiang-x-wohz/
 * @author: sagitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-473/problems/count-distinct-subarrays-divisible-by-k-in-sorted-array">统计有序数组中可被 K 整除的子数组数量</a>
 * @title: 统计有序数组中可被 K 整除的子数组数量
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class,"numGoodSubarrays","D.txt");
    }
    private static final int MOD = (int)1e9 + 7;



    public long numGoodSubarrays(int[] a, int k) {
        long ans = calc(a,k);
        int n = a.length;
        for(int i = 0;i<n;){
            // 分组循环
            int st = i;
            while(i < n&&a[i] == a[st]) i++;
            if(i - st == 1) continue;
            int L = i - st;
            for(int j = 1;j <= L;j++) {
                // 长度不同子数组本质是不同的
                // 因此此处-的是相同的子数组
                // 在相同长度为L的情况下，如果长度为j的子数组能被k整除，总共有 L - j + 1 个子数组 因此重复了 L - j 个
                // 需要减去 L - j
                if(1L * a[st] * j % k == 0) {
                    ans -= L - j;
                }
            }
        }
        return ans;
    }

    // https://leetcode.cn/problems/subarray-sums-divisible-by-k/description/
    public static long calc(int[] a,int k) {
        Map<Integer,Integer> cnt = new HashMap<>();
        long ans = 0;
        cnt.merge(0,1,Integer::sum);
        long pre = 0;
        for(int x : a) {
            pre = ((pre + x)%k + k)%k;
            ans += cnt.getOrDefault((int)pre,0);
            cnt.merge((int)pre,1,Integer::sum);
        }
        return ans;
    }


}