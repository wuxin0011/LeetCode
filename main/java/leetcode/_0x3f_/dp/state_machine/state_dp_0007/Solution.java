package leetcode._0x3f_.dp.state_machine.state_dp_0007;

import code_generation.utils.IoUtil;

/**
 *
 * 1395. 统计作战单位数
 *
 * n 名士兵站成一排。
 * 每个士兵都有一个 独一无二 的评分 rating 。
 * 从中选出 3 个士兵组成一个作战单位，规则如下：
 * 	从队伍中选出下标分别为 i、j、k 的 3 名士兵，他们的评分分别为 rating[i]、rating[j]、rating[k]
 * 	作战单位需满足： rating[i] < rating[j] < rating[k] 或者 rating[i] > rating[j] > rating[k] ，其中 0<= i <j <k <n
 * 请你返回按上述条件组建的作战单位的方案数。
 *
 * 示例 1：
 * 输入：rating = [2,5,3,4,1]
 * 输出：3
 * 解释：我们可以组建三个作战单位 (2,3,4)、(5,4,1)、(5,3,1) 。
 *
 * 示例 2：
 * 输入：rating = [2,1,3]
 * 输出：0
 * 解释：根据题目条件，我们无法组建作战单位。
 *
 * 示例 3：
 * 输入：rating = [1,2,3,4]
 * 输出：4
 *
 * 提示：
 * 	n == rating.length
 * 	3 <= n <= 1000
 * 	1 <= rating[i] <= 10^5
 * 	rating中的元素都是唯一的
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/count-number-of-teams
 * @title: 统计作战单位数
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"numTeams","in.txt");
    }
     

    public int numTeams(int[] rating) {    

        return 0; 
	}

  

}