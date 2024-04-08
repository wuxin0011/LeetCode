package leetcode.ox3if.slide_window.slide_0010;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * 1423. 可获得的最大点数
 *
 * 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。你的点数就是你拿到手中的所有卡牌的点数之和。给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
 *
 * 示例 1：输入：cardPoints = [1,2,3,4,5,6,1], k = 3输出：12解释：第一次行动，不管拿哪张牌，你的点数总是 1 。但是，先拿最右边的卡牌将会最大化你的可获得点数。最优策略是拿右边的三张牌，最终点数为 1 + 6 + 5 = 12 。
 *
 * 示例 2：输入：cardPoints = [2,2,2], k = 2输出：4解释：无论你拿起哪两张卡牌，可获得的点数总是 4 。
 *
 * 示例 3：输入：cardPoints = [9,7,7,9,7,7,9], k = 7输出：55解释：你必须拿起所有卡牌，可以获得的点数为所有卡牌的点数之和。
 *
 * 示例 4：输入：cardPoints = [1,1000,1], k = 1输出：1解释：你无法拿到中间那张卡牌，所以可以获得的最大点数为 1 。
 *
 * 示例 5：输入：cardPoints = [1,79,80,1,1,1,200,1], k = 3输出：202
 *
 * 提示：
 *
 *
 * 	1 <= cardPoints.length <= 10^5
 * 	1 <= cardPoints[i] <= 10^4
 * 	1 <= k <= cardPoints.length
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/maximum-points-you-can-obtain-from-cards
 * @title: maximum-points-you-can-obtain-from-cards
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"maxScore","in.txt");
    }
     

    public int maxScore(int[] cardPoints, int k) {
        long tot = 0;
        int n = cardPoints.length;
        for(int num : cardPoints ) tot += num;
        if(k>=n) return (int) tot;
        k = n - k;
        long mi = tot,val = 0;
        for(int i = 0;i<n;i++){
            if (i>=k) {
                val -= cardPoints[i-k];
            }

            val += cardPoints[i];

            if(i>=k-1 && val < mi ){
                mi = val;
            }
        }

        return (int)(tot - mi);
	}

  

}