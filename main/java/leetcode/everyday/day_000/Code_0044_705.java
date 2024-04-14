package leetcode.everyday.day_000;

import code_generation.contest.ParseCodeInfo;
import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/design-hashset
 * @title: 设计哈希集合
 */
public class Code_0044_705 {

    public static void main(String[] args) {
        IoUtil.testUtil(MyHashSet.class, ParseCodeInfo.ConstructorClass, "txt_file\\Code_0044_705.txt");
    }


    public static class MyHashSet {

        int[] map;
        public MyHashSet() {
            map = new int[10000001];
        }

        public void add(int key) {
            map[key] = 1;
        }

        public void remove(int key) {
            map[key] = 0;

        }

        public boolean contains(int key) {
           return  map[key] == 1;
        }


    }

}