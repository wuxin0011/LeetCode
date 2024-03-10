package leetcode.contest.weekly.w_300.w_388.b;

import leetcode.utils.IoUtil;
import java.util.*;

/**
 * @author: wuxin0011
 * @Description:
 * @url https://leetcode.cn/problems/maximize-happiness-of-selected-children/
 */
public class B {
    public static void main(String[] args) {
        IoUtil.testUtil(B.class,"maximumHappinessSum","in.txt");
    }

    public long maximumHappinessSum(int[] happiness, int k) {
        Arrays.sort(happiness);
        long tot  = 0;
        int di = 0;
        for(int i = happiness.length-1; i>=0&& k>0;i--,k--,di++){
            tot += Math.max(happiness[i]-di,0);
        }
        return tot;
    }
}
