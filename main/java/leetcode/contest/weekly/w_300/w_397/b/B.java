package leetcode.contest.weekly.w_300.w_397.b;

import code_generation.utils.IoUtil;

/**
 * 100274. 从魔法师身上吸取的最大能量
 * <p>
 * <p>
 * 在神秘的地牢中，n 个魔法师站成一排。
 * 每个魔法师都拥有一个属性，这个属性可以给你提供能量。有些魔法师可能会给你负能量，即从你身上吸取能量。
 * 你被施加了一种诅咒，当你从魔法师 i 处吸收能量后，你将被立即传送到魔法师 (i + k) 处。这一过程将重复进行，直到你到达一个不存在 (i + k) 的魔法师为止。
 * 换句话说，你将选择一个起点，然后以 k 为间隔跳跃，直到到达魔法师序列的末端，在过程中吸收所有的能量。
 * 给定一个数组 energy 和一个整数k，返回你能获得的 最大 能量。
 * <p>
 * 示例 1：
 * 输入： energy = [5,2,-10,-5,1], k = 3
 * 输出： 3
 * 解释：可以从魔法师 1 开始，吸收能量 2 + 1 = 3。
 * <p>
 * 示例 2：
 * 输入： energy = [-2,-3,-1], k = 2
 * 输出： -1
 * 解释：可以从魔法师 2 开始，吸收能量 -1。
 * <p>
 * 提示：
 * 1 <= energy.length <= 10^5
 * -1000 <= energy[i] <= 1000
 * 1 <= k <= energy.length - 1
 *
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-397/problems/taking-maximum-energy-from-the-mystic-dungeon
 * @title: 从魔法师身上吸取的最大能量
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "maximumEnergy", "B.txt");
    }


    public int maximumEnergy(int[] energy, int k) {
        int mx = Integer.MIN_VALUE;
        int n = energy.length;
        for (int i = n - 1; i > n - k - 1 ; i--) {
            int ans = 0;
            for (int j = i; j >= 0; j -= k) {
                ans += energy[j];
                mx = Math.max(ans, mx);
            }
        }
        return mx;
    }


}