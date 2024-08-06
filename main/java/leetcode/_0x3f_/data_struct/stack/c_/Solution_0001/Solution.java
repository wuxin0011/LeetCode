package leetcode._0x3f_.data_struct.stack.c_.Solution_0001;

import code_generation.utils.IoUtil;

/**
 * 735. 小行星碰撞
 * <p>
 * 给定一个整数数组 asteroids，表示在同一行的小行星。
 * 对于数组中的每一个元素，其绝对值表示小行星的大小，正负表示小行星的移动方向（正表示向右移动，负表示向左移动）。每一颗小行星以相同的速度移动。
 * 找出碰撞后剩下的所有小行星。碰撞规则：两个小行星相互碰撞，较小的小行星会爆炸。如果两颗小行星大小相同，则两颗小行星都会爆炸。两颗移动方向相同的小行星，永远不会发生碰撞。
 * <p>
 * 示例 1：
 * 输入：asteroids = [5,10,-5]
 * 输出：[5,10]
 * 解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。
 * <p>
 * 示例 2：
 * 输入：asteroids = [8,-8]
 * 输出：[]
 * 解释：8 和 -8 碰撞后，两者都发生爆炸。
 * <p>
 * 示例 3：
 * 输入：asteroids = [10,2,-5]
 * 输出：[10]
 * 解释：2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。
 * <p>
 * 提示：
 * 2 <= asteroids.length<= 10^4
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/asteroid-collision
 * @title: 小行星碰撞
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "asteroidCollision", "in.txt");
    }


    public int[] asteroidCollision(int[] a) {
        int n = a.length;
        int[] sk = new int[n];
        int size = 0;
        for (int v : a) {
            if (v < 0 && size > 0 && sk[size - 1] > 0) {
                if (sk[size - 1] + v >= 0) {
                    if (sk[size - 1] + v == 0) {
                        size--;
                    }
                    continue;
                }
                while (size > 0 && sk[size - 1] > 0 && v + sk[size - 1] <= 0) {
                    if (v + sk[size - 1] >= 0) {
                        if (v + sk[size - 1] == 0) {
                            size--;
                        }
                        v = 0;
                        break;
                    }
                    size--;
                }
                // 如果V 还存在
                if (v < 0) {
                    if (size > 0 && sk[size - 1] + v > 0) {

                    } else {
                        sk[size++] = v;
                    }
                }
            } else {
                sk[size++] = v;
            }
        }
        int[] ans = new int[size];
        System.arraycopy(sk, 0, ans, 0, size);
        return ans;
    }


}