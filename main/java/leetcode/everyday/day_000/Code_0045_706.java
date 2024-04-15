package leetcode.everyday.day_000;

import code_generation.contest.ParseCodeInfo;
import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/design-hashmap
 * @title: 设计哈希映射
 */
public class Code_0045_706 {

    public static void main(String[] args) {
        IoUtil.testUtil(MyHashMap.class, ParseCodeInfo.ConstructorClass, "txt_file\\Code_0045_706.txt");
    }


    public static class MyHashMap {

        final static int cap = 1000001;
        int[] map;
        public MyHashMap() {
            map = new int[cap];
            for (int i = 0; i < map.length; i++) {
                map[i] = -1;
            }
        }

        public void put(int key, int value) {
            map[key] = value;
        }

        public int get(int key) {
            return map[key];
        }

        public void remove(int key) {
            map[key] = -1;
        }


    }

}