package leetcode.contest;

import code_generation.crwal.leetcode.LCContest;

/**
 * @author: wuxin0011
 * @Description: 通过输入序号自定义获取周赛 经过测试小于83 无法获取 国服是从83开始的！
 */
public class CustomWeekContest {

    public static void main(String[] args) {
        LCContest.WEEK_CONTEST.createNo(CustomWeekContest.class);
    }
}
