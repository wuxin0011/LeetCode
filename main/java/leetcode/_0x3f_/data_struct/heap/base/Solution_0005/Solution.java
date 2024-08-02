package leetcode._0x3f_.data_struct.heap.base.Solution_0005;

import code_generation.utils.IoUtil;

import java.util.PriorityQueue;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/remove-stones-to-minimize-the-total
 * @title: 移除石子使总数最小
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "minStoneSum", "in.txt");
        IoUtil.testUtil(Solution.class, "minStoneSum2", "in.txt");
    }


    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        for (int pile : piles) {
            q.add(pile);
        }
        while (k > 0 && !q.isEmpty()) {
            int v = q.poll();
            int rest = ceil(v, 2);
            q.add(rest);
            k--;
        }
        int tot = 0;
        while (!q.isEmpty()) {
            tot += q.poll();
        }
        return tot;
    }

    public static int ceil(int a, int b) {
        if (a % b == 0) {
            return a / b;
        }
        return (a + b - 1) / b;
    }


    /**
     * public int minStoneSum2(int[] piles, int k) {
     * int n = piles.length;
     * buildHeap(piles);
     * while (k > 0) {
     * int v = queue[0];
     * int rest = ceil(v, 2);
     * queue[0] = rest;
     * heapify(0, queue_size);
     * k--;
     * }
     * int tot = 0;
     * for (int i = 0; i < queue_size; i++) {
     * tot += queue[i];
     * }
     * return tot;
     * }
     * <p>
     * <p>
     * <p>
     * static int max = 100001;
     * static int[] queue = new int[max];
     * <p>
     * static int queue_size = 0;
     * static int queue_index = 0;
     * <p>
     * <p>
     * public static void swap(int[] arr, int i, int j) {
     * int temp = arr[i];
     * arr[i] = arr[j];
     * arr[j] = temp;
     * }
     * <p>
     * public static void buildHeap(int[] arr) {
     * queue_size = arr.length;
     * for (int i = 0; i < arr.length; i++) {
     * queue[i] = arr[i];
     * }
     * for (int i = 0; i < queue_size; i++) {
     * heapInsert(i);
     * }
     * }
     * <p>
     * <p>
     * public static void heapInsert(int i) {
     * while (i > 0 && queue[i] > queue[(i - 1) / 2]) {
     * swap(queue, i, (i - 1) / 2);
     * i = (i - 1) / 2;
     * }
     * }
     * <p>
     * public static void heapify(int i, int size) {
     * int l = i * 2 + 1;
     * while (l < size) {
     * int best = l + 1 < size && queue[l + 1] > queue[l] ? l + 1 : l;
     * best = queue[best] > queue[i] ? best : i;
     * if (best == i) break;
     * swap(queue, i, best);
     * i = best;
     * l = 2 * i + 1;
     * }
     * }
     */


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static int sum = 0;

    public static void buildHeap(int[] arr) {
        sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            heapInsert(arr, i);
        }
    }


    public static void heapInsert(int[] queue, int i) {
        while (i > 0 && queue[i] > queue[(i - 1) / 2]) {
            swap(queue, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    public static void heapify(int[] queue, int i, int size) {
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

    public int minStoneSum2(int[] piles, int k) {
        buildHeap(piles);
        while (k > 0) {
            int v = ceil(piles[0], 2);
            int use = piles[0] - v;
            sum -= use;
            piles[0] = v;
            heapify(piles, 0, piles.length);
            k--;
        }
        return sum;
    }

}