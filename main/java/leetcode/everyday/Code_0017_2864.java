package leetcode.everyday;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description: 按照题目直接模拟，将第一个1放到最右边，然后剩下填充到左边，然后补0
 * @title
 * @url https://leetcode.cn/problems/bulls-and-cows/?envType=daily-question&envId=2024-03-10
 */
@SuppressWarnings("all")
public class Code_0017_2864 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0017_2864.class, "maximumOddBinaryNumber", "./txt_file/Code_0017_2864.txt");
    }

    public String maximumOddBinaryNumber(String s) {
        int[] h = new int[2];
        for(int i = 0;i<s.length();i++){
            if(s.charAt(i) == '0'){
                h[0]++;
            }else{
                h[1]++;
            }
        }
        if(h[1] == 0) return s;
        char[] ss = new char[s.length()];
        h[1]--;
        ss[ss.length-1] = '1';
        int id = 0;
        while(h[1]!=0){
            ss[id++] = '1';
            h[1]--;
        }
        while(h[0]!=0){
            ss[id++] = '0';
            h[0]--;
        }


        return new String(ss);
    }

}
