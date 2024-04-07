package leetcode.contest.weekly.w_300.w_379.a;

import code_generation.utils.IoUtil;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   https://leetcode.cn/contest/weekly-contest-379/problems/maximum-area-of-longest-diagonal-rectangle
 * @title: 对角线最长的矩形的面积
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"areaOfMaxDiagonal","A.txt");
    }


    public int areaOfMaxDiagonal(int[][] dimensions) {

        int area = 0;
        long v = 0;
        for (int[] dimension : dimensions) {
            long v1 = (long) dimension[0] *dimension[0] + (long) dimension[1] *dimension[1];
            int a = dimension[0]*dimension[1];
            if(v1>v){
                v = v1;
                area = a;
            }else if (v1==v){
                area = Math.max(area,a);
            }
        }
        return area;
    }

  

}