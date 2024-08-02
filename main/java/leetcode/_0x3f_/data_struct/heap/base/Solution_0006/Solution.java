package leetcode._0x3f_.data_struct.heap.base.Solution_0006;

import code_generation.contest.ParseCodeInfo;
import code_generation.utils.IoUtil;

/**
 * 1845. 座位预约管理系统
 * <p>
 * 请你设计一个管理 n个座位预约的系统，座位编号从1到n。
 * 请你实现SeatManager类：
 * SeatManager(int n)初始化一个SeatManager对象，它管理从 1到 n编号的n个座位。所有座位初始都是可预约的。
 * int reserve()返回可以预约座位的最小编号，此座位变为不可预约。
 * void unreserve(int seatNumber)将给定编号seatNumber对应的座位变成可以预约。
 * <p>
 * 示例 1：
 * 输入：
 * ["SeatManager", "reserve", "reserve", "unreserve", "reserve", "reserve", "reserve", "reserve", "unreserve"]
 * [[5], [], [], [2], [], [], [], [], [5]]
 * 输出：
 * [null, 1, 2, null, 2, 3, 4, 5, null]
 * 解释：
 * SeatManager seatManager = new SeatManager(5); // 初始化 SeatManager ，有 5 个座位。
 * seatManager.reserve();    // 所有座位都可以预约，所以返回最小编号的座位，也就是 1 。
 * seatManager.reserve();    // 可以预约的座位为 [2,3,4,5] ，返回最小编号的座位，也就是 2 。
 * seatManager.unreserve(2); // 将座位 2 变为可以预约，现在可预约的座位为 [2,3,4,5] 。
 * seatManager.reserve();    // 可以预约的座位为 [2,3,4,5] ，返回最小编号的座位，也就是 2 。
 * seatManager.reserve();    // 可以预约的座位为 [3,4,5] ，返回最小编号的座位，也就是 3 。
 * seatManager.reserve();    // 可以预约的座位为 [4,5] ，返回最小编号的座位，也就是 4 。
 * seatManager.reserve();    // 唯一可以预约的是座位 5 ，所以返回 5 。
 * seatManager.unreserve(5); // 将座位 5 变为可以预约，现在可预约的座位为 [5] 。
 * <p>
 * 提示：
 * 1 <= n <= 10^5
 * 1 <= seatNumber <= n
 * 每一次对reserve的调用，题目保证至少存在一个可以预约的座位。
 * 每一次对unreserve的调用，题目保证seatNumber在调用函数前都是被预约状态。
 * 对reserve 和unreserve的调用总共不超过10^5次。
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/seat-reservation-manager
 * @title: 座位预约管理系统
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(SeatManager.class, ParseCodeInfo.ConstructorClass, "in.txt");
    }


    public static class SeatManager {
        private static final int max = (int) 1e5 + 2;

        public static int[] vis = new int[max];
        int n;
        int mi;

        public SeatManager(int n) {
            this.n = n;
            for (int i = 1; i <= n; i++) vis[i] = -1;
            this.mi = 1;
        }

        public int reserve() {
            int ans = 0;
            for (int i = mi; i <= n; i++) {
                if (i == 0) continue;
                if (vis[i] == -1) {
                    vis[i] = 0;
                    ans = i;
                    break;
                }
            }
            this.mi = ans;
            return ans;
        }

        public void unreserve(int seatNumber) {
            if (mi >= seatNumber) {
                mi = seatNumber;
            }
            vis[seatNumber] = -1;
        }


    }

}