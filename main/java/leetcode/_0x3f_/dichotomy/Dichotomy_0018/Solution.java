package leetcode._0x3f_.dichotomy.Dichotomy_0018;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 * 1642. 可以到达的最远建筑
 *
 * 给你一个整数数组 heights ，表示建筑物的高度。另有一些砖块 bricks 和梯子 ladders 。你从建筑物 0 开始旅程，不断向后面的建筑物移动，期间可能会用到砖块或梯子。当从建筑物 i 移动到建筑物 i+1（下标 从 0 开始 ）时：如果当前建筑物的高度 大于或等于 下一建筑物的高度，则不需要梯子或砖块如果当前建筑的高度 小于 下一个建筑的高度，您可以使用 一架梯子 或 (h[i+1] - h[i]) 个砖块如果以最佳方式使用给定的梯子和砖块，返回你可以到达的最远建筑物的下标（下标 从 0 开始 ）。
 *
 * 示例 1：输入：heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1输出：4解释：从建筑物 0 出发，你可以按此方案完成旅程：- 不使用砖块或梯子到达建筑物 1 ，因为 4 >= 2- 使用 5 个砖块到达建筑物 2 。你必须使用砖块或梯子，因为 2 < 7- 不使用砖块或梯子到达建筑物 3 ，因为 7 >= 6- 使用唯一的梯子到达建筑物 4 。你必须使用砖块或梯子，因为 6 < 9无法越过建筑物 4 ，因为没有更多砖块或梯子。
 *
 * 示例 2：输入：heights = [4,12,2,7,3,18,20,3,19], bricks = 10, ladders = 2输出：7
 *
 * 示例 3：输入：heights = [14,3,19,3], bricks = 17, ladders = 0输出：3
 *
 * 提示：
 *
 *
 * 	1 <= heights.length <= 105
 * 	1 <= heights[i] <= 106
 * 	0 <= bricks <= 109
 * 	0 <= ladders <= heights.length
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/furthest-building-you-can-reach
 * @title: 可以到达的最远建筑
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"furthestBuilding","in.txt");
        System.out.println("==========================================");
        IoUtil.testUtil(Solution.class,"furthestBuilding1","in.txt");
    }
    private static final int MOD = (int)1e9 + 7;

    // 反悔贪心做法
    // 开始使用梯子 当梯子不够才开始使用砖块
    // 梯子能够达到无限高度但是一个梯子只能只用一次
    // 砖块可以按照高度合适时候使用
    // [1,2,3,4,100] bricks = 4 ladders =1
    // 先梯子 后面梯子不够，才开始使用砖块 砖块在堆中位最大值(这个最大值应该是消耗梯子)
    // 相似题目 https://leetcode.cn/problems/p0NxJO/description/
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        int n = heights.length;
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 1; i < n ; i++) {
            int need = heights[i]-heights[i-1];
            if(need<=0){
                continue;
            }
            q.add(need);
            if(q.size()>ladders && !q.isEmpty()){
                bricks -= q.poll();
            }
            if(bricks<0){
                return i-1;
            }
        }
        return n - 1;
    }



    // 二分做法
    public int furthestBuilding1(int[] heights, int bricks, int ladders) {
        int n = heights.length;
        int l = 1,r = n - 1;
        while(l<=r){
            int mid = l + ((r - l)>>1);
            if(check(heights,mid,bricks,ladders)) {
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        return r;
    }

    public static boolean check(int[] h,int k,int bricks,int ladders){
        long tot = 0;
        int[] temp = new int[k+1];
        for (int i = 1; i <= k; i++) {
            int diff = h[i] - h[i-1];
            if(diff>0){
                temp[i] = diff;
                tot += diff;
            }
        }
        // 消耗砖块数量 不使用梯子就够了
        if(tot<=bricks){
            return true;
        }
        Arrays.sort(temp);
        for(int i = temp.length - 1;i>=0 && ladders>0;i--){
            tot -= temp[i];
            ladders--;
        }
        return tot <= bricks;
    }

  

}