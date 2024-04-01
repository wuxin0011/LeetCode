package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/faulty-keyboard
 * @title: faulty-keyboard
 */
public class Code_0031_2810 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0031_2810.class, "finalString", "txt_file\\Code_0031_2810.txt");
    }


    public String finalString(String s) {
        int n = s.length();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'i') {
                stringBuilder.reverse();
            }else{
                stringBuilder.append(s.charAt(i));
            }
        }
        return stringBuilder.toString();

    }

}