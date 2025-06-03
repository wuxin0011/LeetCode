package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 * @url:   <a href="https://leetcode.cn/problems/candy">分发糖果</a>
 * @title: 分发糖果
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class Code_0075_135 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0075_135.class,"candy","txt_file\\Code_0075_135.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] a = new int[n];
        Arrays.fill(a,1);
        for(int i = 1;i < n;i++){
            if(ratings[i] > ratings[i-1] && a[i] <= a[i-1]){
                a[i] = a[i - 1] + 1;
            }
        }
        for(int i = n - 2;i >= 0;i--){
            if(ratings[i] > ratings[i+1] && a[i] <= a[i+1]){
                a[i] = a[i+1] + 1;
            }
        }
        return Arrays.stream(a).sum();
	}

  

}