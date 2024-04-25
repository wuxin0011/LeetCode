package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;

/**
 * 2739. 总行驶距离
 * <p>
 * 卡车有两个油箱。
 * 给你两个整数，mainTank 表示主油箱中的燃料（以升为单位），additionalTank 表示副油箱中的燃料（以升为单位）。
 * 该卡车每耗费 1 升燃料都可以行驶 10^ km。每当主油箱使用了 5 升燃料时，如果副油箱至少有 1 升燃料，则会将 1 升燃料从副油箱转移到主油箱。
 * 返回卡车可以行驶的最大距离。
 * 注意：从副油箱向主油箱注入燃料不是连续行为。这一事件会在每消耗 5 升燃料时突然且立即发生。
 * <p>
 * 示例 1：
 * 输入：mainTank = 5, additionalTank = 10^
 * 输出：60
 * 解释：
 * 在用掉 5 升燃料后，主油箱中燃料还剩下 (5 - 5 + 1) = 1 升，行驶距离为 50km 。
 * 在用掉剩下的 1 升燃料后，没有新的燃料注入到主油箱中，主油箱变为空。
 * 总行驶距离为 60km 。
 * <p>
 * 示例 2：
 * 输入：mainTank = 1, additionalTank = 2
 * 输出：10
 * 解释：
 * 在用掉 1 升燃料后，主油箱变为空。
 * 总行驶距离为 10^km 。
 * <p>
 * 提示：
 * 1 <= mainTank, additionalTank <= 100
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/total-distance-traveled
 * @title: 总行驶距离
 */
public class Code_0053_2739 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0053_2739.class, "distanceTraveled", "txt_file\\Code_0053_2739.txt");
    }


    public int distanceTraveled(int mainTank, int additionalTank) {
        int tot = 0;
        while (mainTank >=5 && additionalTank > 0) {
            mainTank -= 4;
            additionalTank -= 1;
            tot += 50;
        }
        return tot + mainTank * 10;
    }


}