package leetcode._0x3f_.data_struct.segment.segment_0004;

import code_generation.contest.ParseCodeInfo;
import code_generation.utils.IoUtil;

/**
 *
 * 2286. 以组为单位订音乐会的门票
 *
 * 一个音乐会总共有n排座位，编号从0到n - 1，每一排有m个座椅，编号为0到m - 1。
 * 你需要设计一个买票系统，针对以下情况进行座位安排：
 * 	同一组的 k位观众坐在 同一排座位，且座位连续 。
 * 	k位观众中 每一位都有座位坐，但他们 不一定坐在一起。
 * 由于观众非常挑剔，所以：
 * 	只有当一个组里所有成员座位的排数都 小于等于maxRow，这个组才能订座位。每一组的maxRow可能 不同。
 * 	如果有多排座位可以选择，优先选择 最小的排数。如果同一排中有多个座位可以坐，优先选择号码 最小的。
 * 请你实现BookMyShow类：
 * 	BookMyShow(int n, int m)，初始化对象，n是排数，m是每一排的座位数。
 * 	int[] gather(int k, int maxRow)返回长度为 2的数组，表示 k个成员中 第一个座位的排数和座位编号，这 k位成员必须坐在 同一排座位，且座位连续 。换言之，返回最小可能的r 和c满足第r排中[c, c + k - 1]的座位都是空的，且r <= maxRow。如果无法安排座位，返回[]。
 * 	boolean scatter(int k, int maxRow)如果组里所有k个成员不一定要坐在一起的前提下，都能在第0 排到第maxRow排之间找到座位，那么请返回true。这种情况下，每个成员都优先找排数最小，然后是座位编号最小的座位。如果不能安排所有k个成员的座位，请返回false。
 *
 * 示例 1：
 * 输入：
 * ["BookMyShow", "gather", "gather", "scatter", "scatter"]
 * [[2, 5], [4, 0], [2, 0], [5, 1], [5, 1]]
 * 输出：
 * [null, [0, 0], [], true, false]
 * 解释：
 * BookMyShow bms = new BookMyShow(2, 5); // 总共有 2 排，每排 5 个座位。
 * bms.gather(4, 0); // 返回 [0, 0]
 *                   // 这一组安排第 0 排 [0, 3] 的座位。
 * bms.gather(2, 0); // 返回 []
 *                   // 第 0 排只剩下 1 个座位。
 *                   // 所以无法安排 2 个连续座位。
 * bms.scatter(5, 1); // 返回 True
 *                    // 这一组安排第 0 排第 4 个座位和第 1 排 [0, 3] 的座位。
 * bms.scatter(5, 1); // 返回 False
 *                    // 总共只剩下 2 个座位。
 *
 * 提示：
 * 	1 <= n <= 5 * 10^4
 * 	1 <= m, k <= 10^9
 * 	0 <= maxRow <= n - 1
 * 	gather 和scatter总 调用次数不超过5 * 10^4 次。
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/booking-concert-tickets-in-groups
 * @title: 以组为单位订音乐会的门票
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(BookMyShow.class, ParseCodeInfo.ConstructorClass, "in.txt");
    }


    public static class BookMyShow {

        int n,m;
        public BookMyShow(int n, int m) {
            this.n = n;
            this.m = m;

        }

        public int[] gather(int k, int maxRow) {

            return null;
        }

        public boolean scatter(int k, int maxRow) {

            return false;
        }


    }

}