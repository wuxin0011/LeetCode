package leetcode.contest;

import code_generation.crwal.leetcode.WeekContest;

/**
 * @author: wuxin0011
 * @Description: 通过输入序号自定义获取周赛 经过测试小于83 无法获取 国服是从83开始的！
 */
public class WeekGen {

    public static void main(String[] args) {
        WeekContest.WEEK_CONTEST.createNo(WeekGen.class);
    }
}
