package leetcode.ox3if.dichotomy.Dichotomy_0012;

import code_generation.utils.IoUtil;

/**
 * 2187. 完成旅途的最少时间
 * <p>
 * 给你一个数组time，其中time[i]表示第 i辆公交车完成 一趟旅途所需要花费的时间。每辆公交车可以 连续 完成多趟旅途，也就是说，一辆公交车当前旅途完成后，可以 立马开始下一趟旅途。每辆公交车 独立运行，也就是说可以同时有多辆公交车在运行且互不影响。给你一个整数totalTrips，表示所有公交车总共需要完成的旅途数目。请你返回完成 至少totalTrips趟旅途需要花费的 最少时间。
 * <p>
 * 示例 1：输入：time = [1,2,3], totalTrips = 5输出：3解释：- 时刻 t = 1 ，每辆公交车完成的旅途数分别为 [1,0,0] 。  已完成的总旅途数为 1 + 0 + 0 = 1 。- 时刻 t = 2 ，每辆公交车完成的旅途数分别为 [2,1,0] 。  已完成的总旅途数为 2 + 1 + 0 = 3 。- 时刻 t = 3 ，每辆公交车完成的旅途数分别为 [3,1,1] 。  已完成的总旅途数为 3 + 1 + 1 = 5 。所以总共完成至少 5 趟旅途的最少时间为 3 。
 * <p>
 * 示例 2：输入：time = [2], totalTrips = 1输出：2解释：只有一辆公交车，它将在时刻 t = 2 完成第一趟旅途。所以完成 1 趟旅途的最少时间为 2 。
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= time.length <= 105
 * 1 <= time[i], totalTrips <= 107
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/minimum-time-to-complete-trips
 * @title: minimum-time-to-complete-trips
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "minimumTime", "in.txt");
    }


    public long minimumTime(int[] time, int totalTrips) {

        long mx = 0;
        for(int i : time){
            if(mx<i) mx = i;
        }

        long r = totalTrips*mx, l = 1;
        while (l <= r) {
            long mid = l + ((r - l) >> 1);
            if (check(time, mid, totalTrips)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public static boolean check(int[] arr, long time, int totalTrips) {
        long times = 0;
        for (int a : arr) {
            times += time / a;
            if (times >= totalTrips) {
                return true;
            }
        }
        return false;
    }


}