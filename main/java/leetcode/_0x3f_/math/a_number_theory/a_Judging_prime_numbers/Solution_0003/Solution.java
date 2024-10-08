package leetcode._0x3f_.math.a_number_theory.a_Judging_prime_numbers.Solution_0003;

import code_generation.utils.IoUtil;

import java.util.Map;
import java.util.TreeMap;

/**
 * 3044. 出现频率最高的质数
 * <p>
 * 给你一个大小为 m x n 、下标从 0 开始的二维矩阵 mat 。
 * 在每个单元格，你可以按以下方式生成数字：
 * 最多有 8 条路径可以选择：东，东南，南，西南，西，西北，北，东北。
 * 选择其中一条路径，沿着这个方向移动，并且将路径上的数字添加到正在形成的数字后面。
 * 注意，每一步都会生成数字，例如，如果路径上的数字是 1, 9, 1，那么在这个方向上会生成三个数字：1, 19, 191 。
 * 返回在遍历矩阵所创建的所有数字中，出现频率最高的、大于 10的质数；如果不存在这样的质数，则返回 -1 。如果存在多个出现频率最高的质数，那么返回其中最大的那个。
 * 注意：移动过程中不允许改变方向。
 * <p>
 * 示例 1：
 * <p>
 * 输入：mat = [[1,1],[9,9],[1,1]]
 * 输出：19
 * 解释：
 * 从单元格 (0,0) 出发，有 3 个可能的方向，这些方向上可以生成的大于 10 的数字有：
 * 东方向: [11], 东南方向: [19], 南方向: [19,191] 。
 * 从单元格 (0,1) 出发，所有可能方向上生成的大于 10 的数字有：[19,191,19,11] 。
 * 从单元格 (1,0) 出发，所有可能方向上生成的大于 10 的数字有：[99,91,91,91,91] 。
 * 从单元格 (1,1) 出发，所有可能方向上生成的大于 10 的数字有：[91,91,99,91,91] 。
 * 从单元格 (2,0) 出发，所有可能方向上生成的大于 10 的数字有：[11,19,191,19] 。
 * 从单元格 (2,1) 出发，所有可能方向上生成的大于 10 的数字有：[11,19,19,191] 。
 * 在所有生成的数字中，出现频率最高的质数是 19 。
 * <p>
 * 示例 2：
 * 输入：mat = [[7]]
 * 输出：-1
 * 解释：唯一可以生成的数字是 7 。它是一个质数，但不大于 10 ，所以返回 -1 。
 * <p>
 * 示例 3：
 * 输入：mat = [[9,7,8],[4,6,5],[2,8,6]]
 * 输出：97
 * 解释：
 * 从单元格 (0,0) 出发，所有可能方向上生成的大于 10 的数字有: [97,978,96,966,94,942] 。
 * 从单元格 (0,1) 出发，所有可能方向上生成的大于 10 的数字有: [78,75,76,768,74,79] 。
 * 从单元格 (0,2) 出发，所有可能方向上生成的大于 10 的数字有: [85,856,86,862,87,879] 。
 * 从单元格 (1,0) 出发，所有可能方向上生成的大于 10 的数字有: [46,465,48,42,49,47] 。
 * 从单元格 (1,1) 出发，所有可能方向上生成的大于 10 的数字有: [65,66,68,62,64,69,67,68] 。
 * 从单元格 (1,2) 出发，所有可能方向上生成的大于 10 的数字有: [56,58,56,564,57,58] 。
 * 从单元格 (2,0) 出发，所有可能方向上生成的大于 10 的数字有: [28,286,24,249,26,268] 。
 * 从单元格 (2,1) 出发，所有可能方向上生成的大于 10 的数字有: [86,82,84,86,867,85] 。
 * 从单元格 (2,2) 出发，所有可能方向上生成的大于 10 的数字有: [68,682,66,669,65,658] 。
 * 在所有生成的数字中，出现频率最高的质数是 97 。
 * <p>
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 6
 * 1 <= mat[i][j] <= 9
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/most-frequent-prime
 * @title: 出现频率最高的质数
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "mostFrequentPrime", "in.txt");
    }

    static int[][] dirs = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1},
    };

    public int mostFrequentPrime(int[][] mat) {

        int m = mat.length, n = mat[0].length;

        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                for (int[] dir : dirs) {
                    int base = mat[i][j];
                    int x = i, y = j;
                    while (dir[0] + x >= 0 && dir[0] + x < m && dir[1] + y >= 0 && dir[1] + y < n) {
                        x += dir[0];
                        y += dir[1];
                        base = base * 10 + mat[x][y];
                        map.put(base, map.getOrDefault(base, 0) + 1);
                    }
                }


            }
        }

        int cnt = 0;
        int ans = 0;
        for (Map.Entry<Integer, Integer> item : map.entrySet()) {
            int k = item.getKey();
            int v = item.getValue();

            // 下面这样优化 是多余的减枝
            // 减枝 1
            if (k < 10 || v < cnt) {
                continue;
            }
            // 减枝 2
            if (v == cnt && k < ans) {
                continue;
            }
            // 减枝 3
            if (!isPrime(k)) {
                continue;
            }
            if (v > cnt) {
                ans = k;
                cnt = v;
            } else if (k > ans) {
                ans = k;
            }

        }
        return cnt == 0 ? -1 : ans;
    }


    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }


}