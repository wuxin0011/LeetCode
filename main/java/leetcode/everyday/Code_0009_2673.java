package leetcode.everyday;

import code_generation.utils.IoUtil;


/**
 * @author: wuxin0011
 * @Description: 统计树中的合法路径数目
 * @url https://leetcode.cn/problems/make-costs-of-paths-equal-in-a-binary-tree
 */
@SuppressWarnings("all")
public class Code_0009_2673 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0009_2673.class, "minIncrements", "./txt_file/Code_0009_2673.txt");
    }


    public int minIncrements(int n, int[] cost) {
        int ans = 0;
        for(int i = n / 2;i>0;i--){
            ans += Math.abs(cost[i*2-1] - cost[i*2]);
            cost[i-1] += Math.max(cost[i*2-1],cost[i*2]);
        }
        return ans;
    }


}
