package leetcode.ox3if.data_struct.heap.base.Solution_0000;

import code_generation.utils.IoUtil;

import java.util.PriorityQueue;

/**
 * 10^46. 最后一块石头的重量有一堆石头，每块石头的重量都是正整数。
 * 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为x 和y，且x <= y。那么粉碎的可能结果如下：
 * 如果x == y，那么两块石头都会被完全粉碎；
 * 如果x != y，那么重量为x的石头将会完全粉碎，而重量为y的石头新重量为y-x。
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
 * <p>
 * 示例：
 * 输入：[2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 先选出 7 和 8，得到 1，所以数组转换为 [2,4,1,1,1]，
 * 再选出 2 和 4，得到 2，所以数组转换为 [2,1,1,1]，
 * 接着是 2 和 1，得到 1，所以数组转换为 [1,1,1]，
 * 最后选出 1 和 1，得到 0，最终数组转换为 [1]，这就是最后剩下那块石头的重量。
 * <p>
 * 提示：
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/last-stone-weight
 * @title: 最后一块石头的重量
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "lastStoneWeight", "in.txt");
        IoUtil.testUtil(Solution.class, "lastStoneWeight2", "in.txt");
        // IoUtil.testUtil(Solution.class, "lastStoneWeight3", "in.txt");
    }

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        for (int stone : stones) {
            q.add(stone);
        }
        while (q.size() >= 2) {
            int a = q.poll();
            assert !q.isEmpty();
            int b = q.poll();
            int rest = a - b;
            if (rest != 0) {
                q.add(rest);
            }
        }
        return q.isEmpty() ? 0 : q.peek();
    }

    public int lastStoneWeight2(int[] stones) {
        MaxHeap maxHeap = new MaxHeap(stones.length);

        // 将石头重量添加到最大堆中
        for (int stone : stones) {
            maxHeap.insert(stone);
        }

        // 当堆中还有至少两个石头时，继续处理
        while (maxHeap.size() >= 2) {
            int a = maxHeap.extractMax(); // 取出最重的石头
            int b = maxHeap.extractMax(); // 取出次重的石头
            int rest = a - b; // 计算石头的碎片
            if (rest != 0) {
                maxHeap.insert(rest); // 如果碎片不为零，则放入堆中
            }
        }

        // 返回最后剩下的石头重量
        return maxHeap.isEmpty() ? 0 : maxHeap.extractMax();
    }

    // 插入新元素并调整堆
    public static class MaxHeap {
        private int[] heap;
        private int size;
        private int capacity;

        public MaxHeap(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.heap = new int[capacity];
        }

        public void insert(int value) {
            if (size == capacity) {
                // 如果堆已满，则扩容
                expandCapacity();
            }

            // 将新元素放入堆的最后位置
            heap[size] = value;
            size++;

            // 对新元素进行上浮操作，维持堆的性质
            heapifyUp(size - 1);
        }

        public int extractMax() {
            if (size == 0) {
                throw new IllegalStateException("Heap is empty");
            }

            // 取出堆顶元素（最大值）
            int max = heap[0];

            // 将堆中最后一个元素移到堆顶
            heap[0] = heap[size - 1];
            size--;

            // 对堆顶元素进行下沉操作，维持堆的性质
            heapifyDown(0);

            return max;
        }

        private void heapifyUp(int index) {
            while (index > 0 && heap[parent(index)] < heap[index]) {
                // 与父节点进行交换
                swap(index, parent(index));
                index = parent(index);
            }
        }

        private void heapifyDown(int index) {
            int maxIndex = index;
            int leftChild = leftChild(index);
            int rightChild = rightChild(index);

            // 与左子节点比较
            if (leftChild < size && heap[leftChild] > heap[maxIndex]) {
                maxIndex = leftChild;
            }

            // 与右子节点比较
            if (rightChild < size && heap[rightChild] > heap[maxIndex]) {
                maxIndex = rightChild;
            }

            // 如果最大值不是当前节点，则进行交换并继续向下调整
            if (index != maxIndex) {
                swap(index, maxIndex);
                heapifyDown(maxIndex);
            }
        }

        private void swap(int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

        private static int parent(int i) {
            return (i - 1) / 2;
        }

        private static int leftChild(int i) {
            return 2 * i + 1;
        }

        private static int rightChild(int i) {
            return 2 * i + 2;
        }

        private void expandCapacity() {
            int[] newHeap = new int[capacity * 2];
            System.arraycopy(heap, 0, newHeap, 0, size);
            heap = newHeap;
            capacity *= 2;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    private int[] stones;
    private int n;

    public int lastStoneWeight3(int[] stones) {
        int size = stones.length;
        for (int i = 0; i < size; i++) {
            heapInsert(stones, i);
        }
        this.n = size;
        this.stones = stones;
        while (n >= 2) {
            int a = pop();
            int b = pop();
            int rest = a - b;
            if (rest != 0) {
                n++;
                stones[n - 1] = rest;
                heapInsert(stones, 0);
            }
        }

        // 返回最后剩下的石头重量
        return size == 0 ? 0 : stones[0];
    }

    // 插入新元素并调整堆
    public void heapInsert(int[] arr, int i) {
        if (i >= arr.length) {
            return;
        }
        while (i > 0 && arr[i] < arr[(i - 1) / 2]) {
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    public int pop() {
        if (n <= 0) {
            throw new IllegalStateException("Heap is empty");
        }
        int ans = stones[0];
        stones[0] = stones[n - 1];
        heapify(stones, 0, n - 1);
        n--;
        return ans;
    }

    // 堆化数组
    public void heapify(int[] arr, int i, int n) {
        int l = 2 * i + 1;
        while (l < n) {
            int best = l + 1 < n && arr[l + 1] < arr[l] ? l + 1 : l;
            if (arr[i] <= arr[best]) {
                break;
            }
            swap(arr, i, best);
            i = best;
            l = 2 * i + 1;
        }
    }

    // 交换数组中的两个元素
    public void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }


}