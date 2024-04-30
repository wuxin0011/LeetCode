package leetcode.contest.biweekly.bi_100.bi_129.a;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   https://leetcode.cn/contest/biweekly-contest-129/problems/make-a-square-with-the-same-color
 * @title: 构造相同颜色的正方形
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"canMakeSquare","A.txt");
    }
     

    public boolean canMakeSquare(char[][] grid) {
        // B W
        // 检查4个格子大小
        for (int i = 0 ;i<=1;i++) {
            for(int j = 0;j<=1;j++){
                if(check(grid,i,j)){
                    return true;
                }
            }
        }

        return false; 
	}

    public static boolean check(char[][] grid,int i,int j) {
        int w = 0;
        int b = 0;
        if(grid[i][j] == 'W'){
            w++;
        }else{
            b++;
        }
        if(grid[i][j+1] == 'W'){
            w++;
        }else{
            b++;
        }
        if(grid[i+1][j]  == 'W'){
            w++;
        }else{
            b++;
        }
        if(grid[i+1][j+1]  == 'W'){
            w++;
        }else{
            b++;
        }
        return b >= 3 || w >= 3;
    }

  

}