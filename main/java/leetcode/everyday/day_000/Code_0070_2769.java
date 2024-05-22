package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/find-the-maximum-achievable-number
 * @title: 找出最大的可达成数字
 */
public class Code_0070_2769 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0070_2769.class, "theMaximumAchievableX", "txt_file\\Code_0070_2769.txt");
    }


    public int theMaximumAchievableX(int num, int t) {

        return num + (t << 1);
    }


}