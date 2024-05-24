package leetcode.ox3if.data_struct.heap.to_the_top_pile.Solution_0002;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 480. 滑动窗口中位数
 * <p>
 * 中位数是有序序列最中间的那个数。
 * 如果序列的长度是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
 * 例如：
 * [2,3,4]，中位数是3
 * [2,3]，中位数是 (2 + 3) / 2 = 2.5
 * 给你一个数组 nums，有一个长度为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
 * <p>
 * 示例：
 * 给出nums = [1,3,-1,-3,5,3,6,7]，以及k = 3。
 * 窗口位置                      中位数
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 * 1 [3  -1  -3] 5  3  6  7      -1
 * 1  3 [-1  -3  5] 3  6  7      -1
 * 1  3  -1 [-3  5  3] 6  7       3
 * 1  3  -1  -3 [5  3  6] 7       5
 * 1  3  -1  -3  5 [3  6  7]      6
 * 因此，返回该滑动窗口的中位数数组[1,-1,-1,3,5,6]。
 * <p>
 * 提示：
 * 你可以假设k始终有效，即：k 始终小于等于输入的非空数组的元素个数。
 * 与真实值误差在 10 ^ -5 以内的答案将被视作正确答案。
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/sliding-window-median
 * @title: 滑动窗口中位数
 */
public class Solution {


    static class Solution0 {
        public static void main(String[] args) {
            IoUtil.testUtil(Solution0.class, "medianSlidingWindow", "in.txt");
        }

        List<Long> list = new ArrayList<>();

        boolean isSort = false;

        public double[] medianSlidingWindow(int[] nums, int k) {

            int n = nums.length;
            double[] ans = new double[n - k + 1];

            for (int i = 0; i < nums.length; i++) {
                // 离开窗口的元素
                if (i >= k) {
                    remove(nums[i - k]);
                }
                //
                add(nums[i]);


                if (i >= k - 1) {

                    if (!isSort) {
                        // 这题数据很诡异，直接减少的会会导致数据溢出，连比较器也是
                        // 因此需要 直接比较数据大小 调用默认比较器
                        // a - b 的化会导致数据溢出
                        list.sort((a, b) -> a.compareTo(b));
                        isSort = true;
                    }

                    ans[i - k + 1] = calc();
                }
            }

            return ans;
        }

        public void remove(int val) {

            list.remove(Math.max(0,Math.min(lower_bound(val),list.size()-1)));
        }

        public double calc() {
            int mid = list.size() >> 1;
            if (list.size() % 2 == 0) {
                return ((long) list.get(mid) + (long) list.get((mid - 1))) * 1.0 / 2;
            } else {
                return (list.get(mid)) * 1.0 ;
            }
        }


        public void add(int val) {
            if (!isSort) {
                list.add((long) val);
            } else {
                list.add(lower_bound(val), (long) val);
            }
        }

        public int lower_bound(int val) {
            int r = list.size() - 1, l = 0;
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                if (list.get((mid)) >= val) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            return l;
        }


    }


    static class Solution1 {
        public static void main(String[] args) {
            IoUtil.testUtil(Solution1.class, "medianSlidingWindow", "in.txt");
        }

        PriorityQueue<Integer> max = new PriorityQueue<>((a, b) -> b - a); // 大顶堆
        PriorityQueue<Integer> min = new PriorityQueue<>((a, b) -> a - b); // 小顶堆

        int[] queue;
        int size = 0;

        public double[] medianSlidingWindow(int[] nums, int k) {
            double[] ans = new double[nums.length - k + 1];
            queue = new int[k];


            for (int i = 0; i < nums.length; i++) {
                // 离开窗口的元素
                if (i >= k) {
                    removeId(i - k, nums[i - k]);
                }
                //
                add(i, nums[i]);


                if (i >= k - 1) {

                    ans[i - k + 1] = calc();
                }
            }

            return ans;
        }


        public double calc() {
            double val = 0;
            if ((max.size() > min.size())) {
                val = max.peek() * 1.0;
            } else if (max.size() < min.size()) {
                val = min.peek() * 1.0;
            } else {
                val = (max.peek() * 1L + min.peek() * 1L) * 1.0 / 2;
            }
            return val;
        }


        public void add(int id, int val) {
            if (max.isEmpty()) {
                max.add(val);
            } else if (min.isEmpty()) {
                if (val < max.peek()) {
                    min.add(max.poll());
                    max.add(val);
                } else {
                    min.add(val);
                }
            } else {
                if (val >= max.peek()) {
                    min.add(val);
                } else {
                    max.add(val);
                }
            }


            balance();

        }

        public void balance() {
            if (Math.abs(max.size() - min.size()) >= 2) {
                if (max.size() > min.size()) {
                    min.add(max.poll());
                } else {
                    max.add(min.poll());
                }
            }
        }


        public void removeId(int id, int val) {
            if (!max.isEmpty() && val <= max.peek()) {
                updateQueue(val, max);
            } else {
                updateQueue(val, min);
            }
            balance();
        }


        public void updateQueue(int val, PriorityQueue<Integer> pq) {
            while (!pq.isEmpty()) {
                int p = pq.poll();
                if (p == val) {
                    break;
                }
                queue[size++] = p;
            }
            while (size > 0) {
                pq.add(queue[--size]);
            }
        }


    }
}