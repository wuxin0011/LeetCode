package leetcode.contest.weekly.w_300.w_382.c;

import leetcode.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description: Alice 和 Bob 玩鲜花游戏
 */
public class C {
    public static void main(String[] args) {
        IoUtil.testUtil(C.class);
    }

    public long flowerGame(int n, int m) {
        return (m*1L*n*1L)>>1;
    }
}
