package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/watering-plants
 * @title: 给植物浇水
 */
public class Code_0061_2079 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0061_2079.class,"wateringPlants","txt_file\\Code_0061_2079.txt");
    }
     

    public int wateringPlants(int[] plants, int capacity) {
        int n = plants.length;
        int c = capacity;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt++;
            if(plants[i] > c) {
                c = capacity;
                cnt += i << 1;
            }
            c -= plants[i];
        }
        return cnt;
    }

  

}