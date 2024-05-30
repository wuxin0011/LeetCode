package leetcode.ox3if.data_struct.segment.segment_0000;

import code_generation.contest.ParseCodeInfo;
import code_generation.utils.IoUtil;

/**
 * 1157. 子数组中占绝大多数的元素
 * <p>
 * 设计一个数据结构，有效地找到给定子数组的 多数元素 。
 * 子数组的 多数元素 是在子数组中出现threshold次数或次数以上的元素。
 * 实现 MajorityChecker 类:
 * MajorityChecker(int[] arr)会用给定的数组 arr对MajorityChecker 初始化。
 * int query(int left, int right, int threshold)返回子数组中的元素 arr[left...right]至少出现threshold次数，如果不存在这样的元素则返回 -1。
 * <p>
 * 示例 1：
 * 输入:
 * ["MajorityChecker", "query", "query", "query"]
 * [[[1, 1, 2, 2, 1, 1]], [0, 5, 4], [0, 3, 3], [2, 3, 2]]
 * 输出：
 * [null, 1, -1, 2]
 * 解释：
 * MajorityChecker majorityChecker = new MajorityChecker([1,1,2,2,1,1]);
 * majorityChecker.query(0,5,4); // 返回 1
 * majorityChecker.query(0,3,3); // 返回 -1
 * majorityChecker.query(2,3,2); // 返回 2
 * <p>
 * 提示：
 * 1 <= arr.length <= 2 * 10^4
 * 1 <= arr[i] <= 2 * 10^4
 * 0 <= left <= right < arr.length
 * threshold <= right - left + 1
 * 2 * threshold > right - left + 1
 * 调用query的次数最多为10^4
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/online-majority-element-in-subarray
 * @title: 子数组中占绝大多数的元素
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(MajorityChecker.class, ParseCodeInfo.ConstructorClass, "in.txt");
    }


    public static class Node {

    }


    public static class MajorityChecker {


        public MajorityChecker(int[] arr) {

        }


        public int query(int left, int right, int threshold) {
            return 0;
        }


    }

}