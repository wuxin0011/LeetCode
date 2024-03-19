package leetcode.contest.weekly.w_300.w_380.a;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description: 最大频率元素计数
 */
public class A {
    public static void main(String[] args) {
        IoUtil.testUtil(A.class);
    }

    public int maxFrequencyElements(int[] nums) {
        int[] helps = new int[101];
        int cnt = 0;
        for(int num : nums){
            helps[num]++;
            if(helps[num]> cnt){
                cnt = helps[num];
            }
        }
        int ans=0;
        for(int i = 0;i<helps.length;i++){
            if(helps[i] == cnt) ans += cnt;
        }
        return ans;
    }
}
