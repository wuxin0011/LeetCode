package leetcode._0x3f_.data_struct.heap.to_the_top_pile.Solution_0001;

import code_generation.contest.ParseCodeInfo;
import code_generation.utils.IoUtil;

import java.util.PriorityQueue;

/**
 *
 * LCR 160. 数据流中的中位数
 *
 * 中位数是有序整数列表中的中间值。
 * 如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
 * 例如，
 * [2,3,4] 的中位数是 3
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * 设计一个支持以下两种操作的数据结构：
 * 	void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * 	double findMedian() - 返回目前所有元素的中位数。
 *
 * 示例 1：
 * 输入：
 * ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
 * [[],[1],[2],[],[3],[]]
 * 输出：[null,null,null,1.50000,null,2.00000]
 *
 * 示例 2：
 * 输入：
 * ["MedianFinder","addNum","findMedian","addNum","findMedian"]
 * [[],[2],[],[3],[]]
 * 输出：[null,null,2.00000,null,2.50000]
 *
 * 提示：
 * 	最多会对addNum、findMedian 进行50000次调用。
 * 注意：本题与主站 295 题相同：
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/find-median-from-data-stream
 * @title: 数据流的中位数
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(MedianFinder.class, ParseCodeInfo.ConstructorClass, "in.txt");
    }


    public static class MedianFinder {

        PriorityQueue<Integer> max; // 大顶堆
        PriorityQueue<Integer> min; // 小顶堆

        public MedianFinder() {
            max = new PriorityQueue<>((a, b) -> b - a);
            min = new PriorityQueue<>((a, b) -> a - b);
        }

        public void addNum(int num) {
            if (min.isEmpty() && max.isEmpty()) {
                max.add(num);
            } else if (max.size() == 1 && min.isEmpty()) {
                if (max.peek() > num) {
                    min.add(max.poll());
                    max.add(num);
                } else {
                    min.add(num);
                }
            } else {
                if (num > max.peek()) {
                    min.add(num);
                } else {
                    max.add(num);
                }

            }
            balance();

        }

        public double findMedian() {
            if (min.isEmpty() && max.isEmpty()) {
                return 0;
            }
            if (min.isEmpty()) {
                return max.peek();
            } else if (max.isEmpty()) {
                return min.peek();
            }
            boolean isO = ((max.size() + min.size()) & 1) == 0;
            return isO ? ((min.peek() + max.peek()) * 1.0 / 2) : (min.size() > max.size() ? min.peek() : max.peek());
        }

        // 平衡
        public void balance() {
            if (Math.abs(max.size() - min.size()) >= 2) {
                // 多的给一点小的 调整
                if (max.size() > min.size()) {
                    min.add(max.poll());
                } else {
                    max.add(min.poll());
                }
            }

        }
    }

}