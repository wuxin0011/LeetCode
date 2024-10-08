package leetcode.everyday.day_000;

import code_generation.contest.ParseCodeInfo;
import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 *
 * 2349. 设计数字容器系统
 *
 * 设计一个数字容器系统，可以实现以下功能：
 * 	在系统中给定下标处插入或者 替换一个数字。
 * 	返回系统中给定数字的最小下标。
 * 请你实现一个NumberContainers类：
 * 	NumberContainers()初始化数字容器系统。
 * 	void change(int index, int number) 在下标index处填入number。如果该下标index处已经有数字了，那么用 number替换该数字。
 * 	int find(int number)返回给定数字number在系统中的最小下标。如果系统中没有number，那么返回-1。
 *
 * 示例：
 * 输入：
 * ["NumberContainers", "find", "change", "change", "change", "change", "find", "change", "find"]
 * [[], [10^], [2, 10^], [1, 10^], [3, 10^], [5, 10^], [10^], [1, 20], [10^]]
 * 输出：
 * [null, -1, null, null, null, null, 1, null, 2]
 * 解释：
 * NumberContainers nc = new NumberContainers();
 * nc.find(10^); // 没有数字 10^ ，所以返回 -1 。
 * nc.change(2, 10^); // 容器中下标为 2 处填入数字 10^ 。
 * nc.change(1, 10^); // 容器中下标为 1 处填入数字 10^ 。
 * nc.change(3, 10^); // 容器中下标为 3 处填入数字 10^ 。
 * nc.change(5, 10^); // 容器中下标为 5 处填入数字 10^ 。
 * nc.find(10^); // 数字 10^ 所在的下标为 1 ，2 ，3 和 5 。因为最小下标为 1 ，所以返回 1 。
 * nc.change(1, 20); // 容器中下标为 1 处填入数字 20 。注意，下标 1 处之前为 10^ ，现在被替换为 20 。
 * nc.find(10^); // 数字 10^ 所在下标为 2 ，3 和 5 。最小下标为 2 ，所以返回 2 。
 *
 * 提示：
 * 	1 <= index, number <= 10^9
 * 	调用change 和find的总次数不超过10^5 次。
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/design-a-number-container-system
 * @title: 设计数字容器系统
 */
public class Code_0054_2349 {

    public static void main(String[] args) {
        IoUtil.testUtil(NumberContainers.class, ParseCodeInfo.ConstructorClass, "txt_file\\Code_0054_2349.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public static class NumberContainers {

        Map<Integer, Integer> idMap;
        Map<Integer, TreeSet<Integer>> valueMap;

        public NumberContainers() {
            idMap = new HashMap<>();
            valueMap = new HashMap<>();
        }

        public void change(int index, int number) {
            TreeSet<Integer> q = null;
            // 检查 index 是否存在
            if (idMap.containsKey((index))) {
                int val = idMap.get(index);
                q = valueMap.get(val);
                q.remove(index);
            }
            idMap.put(index, number);
            q = valueMap.getOrDefault(number, new TreeSet<>((a, b) -> a - b));
            q.add(index);
            valueMap.put(number, q);
        }

        public int find(int number) {
            if (!valueMap.containsKey(number)) {
                return -1;
            }
            TreeSet<Integer> q = valueMap.get(number);
            return q.isEmpty() ? -1 : q.first();
        }


    }

}