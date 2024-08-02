package leetcode._0x3f_.data_struct.differential.one_dimensional.one_diff_0003;

import code_generation.utils.IoUtil;

/**
 *
 *
 * 10^94. 拼车车上最初有capacity个空座位。
 * 车只能向一个方向行驶（也就是说，不允许掉头或改变方向）
 * 给定整数capacity和一个数组 trips , trip[i] = [numPassengersi, fromi, toi]表示第 i 次旅行有numPassengersi乘客，接他们和放他们的位置分别是fromi和toi。这些位置是从汽车的初始位置向东的公里数。
 * 当且仅当你可以在所有给定的行程中接送所有乘客时，返回true，否则请返回 false。
 *
 * 示例 1：
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 4
 * 输出：false
 *
 * 示例 2：
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 5
 * 输出：true
 *
 * 提示：
 * 	1 <= trips.length <= 1000
 * 	trips[i].length == 3
 * 	1 <= numPassengersi<= 100
 * 	0 <= fromi< toi<= 1000
 * 	1 <= capacity <= 10^5
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/car-pooling
 * @title: 拼车
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"carPooling","in.txt");
    }
     

    public boolean carPooling(int[][] trips, int capacity) {

        int N = 1001;
        int[] diff = new int[N];

        for (int[] trip : trips) {
            int user = trip[0];
            int from = trip[1];
            int to = trip[2];
            diff[from] += user;
            diff[to] -= user;
        }

        int c = 0;
        for (int i = 0; i < N; i++) {
            c += diff[i];
            if(c > capacity) {
                return false;
            }
        }
        return true;
    }

  

}