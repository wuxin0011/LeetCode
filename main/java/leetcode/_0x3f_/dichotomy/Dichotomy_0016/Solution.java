package leetcode._0x3f_.dichotomy.Dichotomy_0016;

import code_generation.utils.IoUtil;

/**
 *
 *
 * 1898. 可移除字符的最大数目
 *
 * 给你两个字符串 s 和 p ，其中 p 是 s 的一个 子序列 。同时，给你一个元素 互不相同 且下标 从 0 开始 计数的整数数组removable ，该数组是 s 中下标的一个子集（s 的下标也 从 0 开始 计数）。请你找出一个整数 k（0 <= k <= removable.length），选出removable 中的 前 k 个下标，然后从 s 中移除这些下标对应的 k 个字符。整数 k 需满足：在执行完上述步骤后， p 仍然是 s 的一个 子序列 。更正式的解释是，对于每个 0 <= i < k ，先标记出位于 s[removable[i]] 的字符，接着移除所有标记过的字符，然后检查 p 是否仍然是 s 的一个子序列。返回你可以找出的 最大 k ，满足在移除字符后 p 仍然是 s 的一个子序列。字符串的一个 子序列 是一个由原字符串生成的新字符串，生成过程中可能会移除原字符串中的一些字符（也可能不移除）但不改变剩余字符之间的相对顺序。
 *
 * 示例 1：输入：s = "abcacb", p = "ab", removable = [3,1,0]输出：2解释：在移除下标 3 和 1 对应的字符后，"abcacb" 变成 "accb" 。"ab" 是 "accb" 的一个子序列。如果移除下标 3、1 和 0 对应的字符后，"abcacb" 变成 "ccb" ，那么 "ab" 就不再是 s 的一个子序列。因此，最大的 k 是 2 。
 *
 * 示例 2：输入：s = "abcbddddd", p = "abcd", removable = [3,2,1,4,5,6]输出：1解释：在移除下标 3 对应的字符后，"abcbddddd" 变成 "abcddddd" 。"abcd" 是 "abcddddd" 的一个子序列。
 *
 * 示例 3：输入：s = "abcab", p = "abc", removable = [0,1,2,3,4]输出：0解释：如果移除数组 removable 的第一个下标，"abc" 就不再是 s 的一个子序列。
 *
 * 提示：
 *
 *
 * 	1 <= p.length <= s.length <= 105
 * 	0 <= removable.length < s.length
 * 	0 <= removable[i] < s.length
 * 	p 是 s 的一个 子字符串
 * 	s 和 p 都由小写英文字母组成
 * 	removable 中的元素 互不相同
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/maximum-number-of-removable-characters
 * @title: 可移除字符的最大数目
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"maximumRemovals","in.txt");
    }




    public int maximumRemovals(String s, String p, int[] removable) {

        int r = removable.length - 1,l = 0;
        char[] cs = s.toCharArray();
        char[] ps = p.toCharArray();
        boolean[] vis = new boolean[cs.length];
        while(l<=r){
            int mid = l + ((r - l)>>1);
            if(check(cs,ps,removable,vis,mid)){
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        return l;
    }


    public static boolean check(char[] chars,char[] ps,int[] removable,boolean[] vis,int k){
        for (int i = 0; i <= k; i++) {
            vis[removable[i]] = true;
        }
        int i = 0,j = 0;
        for(;i<chars.length && j < ps.length;i++){
            if(vis[i]){
                continue;
            }
            if(chars[i] == ps[j]){
                j++;
            }
        }
        for (i = 0; i <= k; i++) {
            vis[removable[i]] = false;
        }
        return j == ps.length;
    }
}