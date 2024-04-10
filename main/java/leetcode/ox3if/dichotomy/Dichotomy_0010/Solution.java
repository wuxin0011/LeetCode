package leetcode.ox3if.dichotomy.Dichotomy_0010;

import code_generation.crwal.request.Config;
import code_generation.utils.IoUtil;

/**
 * 275. H 指数 II
 * <p>
 * 给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数，citations 已经按照升序排列。计算并返回该研究者的 h指数。h 指数的定义：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （n 篇论文中）至少有 h 篇论文分别被引用了至少 h 次。请你设计并实现对数时间复杂度的算法解决此问题。
 * <p>
 * 示例 1：输入：citations = [0,1,3,5,6]输出：3解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 0, 1, 3, 5, 6 次。    由于研究者有3篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3 。
 * <p>
 * 示例 2：输入：citations = [1,2,100]输出：2
 * <p>
 * 提示：
 * <p>
 * <p>
 * n == citations.length
 * 1 <= n <= 105
 * 0 <= citations[i] <= 1000
 * citations 按 升序排列
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/h-index-ii
 * @title: h-index-ii
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "hIndex", "in.txt");
    }


    public int hIndex(int[] citations) {
        int n = citations.length;
        int r = n - 1,l = 0;
        while (l<=r){
            int mid = l + ((r - l)>>1);
            if(citations[mid] == n - mid ){
                return n - mid;
            }
            if(citations[mid]>n - mid ){
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return n - l;
    }



}