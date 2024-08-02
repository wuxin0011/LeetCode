package leetcode._0x3f_.slide_window.slide_0001;

import code_generation.utils.IoUtil;
/**
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/find-the-k-beauty-of-a-number
 * @title: find-the-k-beauty-of-a-number
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"divisorSubstrings","in.txt");
    }

    private static final int MOD = (int)1e9 + 7;

    public int divisorSubstrings(int num, int k) {
        char[] charArray = String.valueOf(num).toCharArray();
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for(int i = 0;i<charArray.length;i++){

            if(i>=k){
                sb.deleteCharAt(0);
            }
            sb.append(charArray[i]);

            if(i>=k-1){
                int v = Integer.parseInt(sb.toString());
                if(v != 0 && num % v == 0){
                    cnt++;
                }
            }
        }

        return cnt;
    }

  

}