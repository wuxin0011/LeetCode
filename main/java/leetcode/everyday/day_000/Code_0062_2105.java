package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;
/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/watering-plants-ii
 * @title: watering-plants-ii
 */
public class Code_0062_2105 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0062_2105.class, "minimumRefill", "txt_file\\Code_0062_2105.txt");
    }


    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int n = plants.length;
        int a = 0, b = 0;
        int ca = capacityA, cb = capacityB;
        for (int l = 0, r = n - 1; l <= r; l++, r--) {
            if (r == l && n % 2 == 1) {
                if (ca >= cb) {
                    if (plants[l] > ca) {
                        a++;
                    }
                } else {
                    if (plants[r] > cb) {
                        b++;
                    }
                }
                break;
            }
            if (plants[l] > ca) {
                ca = capacityA;
                a++;
            }
            if (plants[r] > cb) {
                cb = capacityB;
                b++;
            }
            ca -= plants[l];
            cb -= plants[r];
        }
        return a + b;
    }


}