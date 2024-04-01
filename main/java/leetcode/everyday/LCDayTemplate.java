package leetcode.everyday;

import code_generation.crwal.leetcode.LCEveryDay;


/**
 * @author: wuxin0011
 * @Description: 自动获取每日一题
 */
public class LCDayTemplate {

    private static final LCEveryDay EVERY_DAY = new LCEveryDay();


    public static void main(String[] args) {
        EVERY_DAY.start(LCDayTemplate.class);
    }


}
