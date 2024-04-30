package leetcode.contest.biweekly.bi_100.bi_129.c;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   https://leetcode.cn/contest/biweekly-contest-129/problems/find-all-possible-stable-binary-arrays-i
 * @title: 找出所有稳定的二进制数组I
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class,"numberOfStableArrays","C.txt");
    }
     

    public int numberOfStableArrays(int zero, int one, int limit) {    

        return 0; 
	}

    char[] nums;
    int[][] dp;
    public int digitOneInNumber(int num) {
        this.nums = String.valueOf(num).toCharArray();
        int n = nums.length;
        this.dp = new int[n][n];
        for(int i = 0;i<n;i++) Arrays.fill(dp[i],-1);
        return dfs(0,0,true);
    }
    public int dfs(int idx,int cnt,boolean isLimit){
        if( idx == nums.length ) return cnt;

        if( !isLimit && dp[idx][cnt] != -1) return dp[idx][cnt];

        int ans = 0;

        int up = isLimit ? nums[idx] - '0' : 9;
        for(int di = 0;di<=up;di++){
            ans += dfs(idx+1,cnt + (di == 1 ? 1 : 0),isLimit && di == up);
        }
        if( !isLimit ){
            dp[idx][cnt] = ans;
        }
        return ans;
    }



}