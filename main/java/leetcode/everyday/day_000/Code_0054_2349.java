package leetcode.everyday.day_000;

import code_generation.contest.ParseCodeInfo;
import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
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