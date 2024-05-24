package leetcode.ox3if.bitwise_operations.base.bitwise_0013;

import code_generation.utils.IoUtil;

/**
 *
 *
 * 868. 二进制间距
 *
 * 给定一个正整数 n，找到并返回 n 的二进制表示中两个 相邻 1 之间的 最长距离 。如果不存在两个相邻的 1，返回 0 。如果只有 0 将两个 1 分隔开（可能不存在 0 ），则认为这两个 1 彼此 相邻 。两个 1 之间的距离是它们的二进制表示中位置的绝对差。例如，"1001" 中的两个 1 的距离为 3 。
 *
 * 示例 1：输入：n = 22输出：2解释：22 的二进制是 "10110" 。在 22 的二进制表示中，有三个 1，组成两对相邻的 1 。第一对相邻的 1 中，两个 1 之间的距离为 2 。第二对相邻的 1 中，两个 1 之间的距离为 1 。答案取两个距离之中最大的，也就是 2 。
 *
 * 示例 2：输入：n = 8输出：0解释：8 的二进制是 "1000" 。在 8 的二进制表示中没有相邻的两个 1，所以返回 0 。
 *
 * 示例 3：输入：n = 5输出：2解释：5 的二进制是 "101" 。
 *
 * 提示：
 *
 *
 * 	1 <= n <= 109
 *
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/binary-gap
 * @title: 二进制间距
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"binaryGap","in.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    public int binaryGap(int n) {
        int ans = 0;
        for(int r = 0,l = -1;r<32;r++){
            if((n>>r&1)==0){
                continue;
            }
            if(l==-1){
                l = r;
            }else{
                ans = Math.max(ans,r - l );
                l = r;
            }
        }
        return ans;
    }

    public int binaryGap1(int n) {
        int[] h = new int[32];
        for (int i = 0; i < h.length; i++) {
            h[i] = n >> i & 1;
        }
        int ans = 0;
        for(int r = 0,l = -1;r<h.length;r++){
            if(h[r]==0){
                continue;
            }

            if(l==-1){
                l = r;
            }else{
                ans = Math.max(ans,r - l );
                l = r;
            }
        }
        return ans;
    }



}