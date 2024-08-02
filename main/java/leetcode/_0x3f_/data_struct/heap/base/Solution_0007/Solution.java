package leetcode._0x3f_.data_struct.heap.base.Solution_0007;

import code_generation.utils.IoUtil;

import java.util.PriorityQueue;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/minimum-operations-to-halve-array-sum
 * @title: 将数组和减半的最少操作次数
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "halveArray", "in.txt");
        IoUtil.testUtil(Solution.class, "halveArray2", "in.txt");
    }


    public int halveArray(int[] nums) {

        PriorityQueue<Double> q = new PriorityQueue<>((a, b) -> b.compareTo(a));
        double sum = 0;
        for (int num : nums) {
            sum += num * 1.0;
            q.add(num * 1.0);
        }
        double rest = 0;
        int cnt = 0;
        while (!q.isEmpty()) {
            double a = q.poll() / 2;
            rest += a;
            cnt++;
            q.add(a);
            if (rest * 2 >= sum) {
                break;
            }
        }
        return cnt;
    }

    static int max = 100001;
    static double[] queue = new double[max];

    static int queue_size = 0;
    static int queue_index = 0;

    public int halveArray2(int[] nums) {

        int n = nums.length;
        queue_size = n;
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i] * 1.0;
            queue[i] = nums[i] * 1.0;
        }
        buildHeap();

        double rest = 0;
        int cnt = 0;
        do {
            double v = queue[0] / 2;
            rest += v;
            cnt++;
            queue[0] = v;
            heapify(0, queue_size);
        } while (!(rest * 2 >= sum));
        return cnt;
    }

    public static void swap(double[] arr, int i, int j) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void buildHeap() {
        for (int i = 0; i < queue_size; i++) {
            heapInsert(i);
        }
    }


    public static void heapInsert(int i) {
        while (i > 0 && queue[i] > queue[(i - 1) / 2]) {
            swap(queue, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    public static void heapify(int i, int size) {
        int l = i * 2 + 1;
        while (l < size) {
            int best = l + 1 < size && queue[l + 1] > queue[l] ? l + 1 : l;
            best = queue[best] > queue[i] ? best : i;
            if (best == i) break;
            swap(queue, i, best);
            i = best;
            l = 2 * i + 1;
        }
    }


}