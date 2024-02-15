package leetcode.array;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author: wuxin001
 * @Description: 两数之和
 */
public class TwoSum {

    public static HashMap<Integer, Integer[]> map = new HashMap<>();

    public static void main(String[] args) {
        int[] ints = {1, 3, 4, 5};
        int[] targets = {4, 5, 8, 9};

        System.out.println("解法1");

        long l1 = System.currentTimeMillis();
        for (int i : targets) {
            System.out.println(Arrays.toString(getSum0(ints, i)));
        }
        long l2 = System.currentTimeMillis();
        System.out.println("耗时 : " + (l2 - l1) + " ms");


        System.out.println("解法二");
        long l3 = System.currentTimeMillis();
        for (int i : targets) {
            System.out.println(Arrays.toString(getSum1(ints, i)));
        }
        long l4 = System.currentTimeMillis();
        System.out.println("耗时 : " + (l4 - l3) + " ms");


        init(ints);

        long l5 = System.currentTimeMillis();
        for (int i : targets) {
            System.out.println(Arrays.toString(getSum1(ints, i)));
        }
        long l6 = System.currentTimeMillis();
        System.out.println("耗时 : " + (l6 - l5) + " ms");

    }


    public static Integer[] getSum0(int[] ints, int target) {
        for (int i = 0; i < ints.length; i++) {
            for (int j = 1; j < ints.length; j++) {
                if (target == (ints[i] + ints[j])) {
                    return new Integer[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * 解法一
     *
     * @param ints
     * @param target
     * @return
     */
    public static int[] getSum1(int[] ints, int target) {
        if (ints == null || ints.length <= 2) {
            return null;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < ints.length; i++) {
            int need = target - ints[i];
            if (map.containsKey(need)) {
                return new int[]{map.get(need), i};
            }
            map.put(ints[i], i);
        }
        return null;
    }

    public static void init(int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            for (int i1 = 1; i1 < ints.length; i1++) {
                map.put((ints[i] + ints[i1]), new Integer[]{i, i1});
            }
        }
    }

    public static Integer[] getSum2(int target) {
        return map.get(target);
    }
}
