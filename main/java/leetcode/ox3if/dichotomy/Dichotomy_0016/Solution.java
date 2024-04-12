package leetcode.ox3if.dichotomy.Dichotomy_0016;

import code_generation.utils.IoUtil;
import java.util.*;
/**
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