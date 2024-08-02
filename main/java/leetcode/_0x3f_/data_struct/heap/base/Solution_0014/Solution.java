package leetcode._0x3f_.data_struct.heap.base.Solution_0014;

import code_generation.utils.IoUtil;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/maximum-average-pass-ratio
 * @title: 最大平均通过率
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "maxAverageRatio", "in.txt");
        IoUtil.testUtil(Solution.class, "maxAverageRatio1", "in.txt");
    }


    public static double calc(int[] a) {
        double d1 = (a[0] * 1.0 + 1) / (a[1] + 1);
        double d2 = (a[0] * 1.0) / (a[1]);
        return d1 - d2;
    }


    public double maxAverageRatio(int[][] classes, int extraStudents) {
        // 通过率 = 通过人数 / 当前班级学生
        // 平均通过率 = 通过率总和 / 全部班级
        // 通过率总和 = a / tota  + b / totb + c / totc
        // x / y
        // (x + 2 ) / ( y + 2) - ( x + 1) / ( y + 1)  < (x + 1 / y + 1 ) - x / y
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> Double.compare(calc(b), calc(a)));
        int n = classes.length;
        q.addAll(Arrays.asList(classes));
        while (extraStudents > 0 && !q.isEmpty()) {
            int[] p = q.poll();
            p[0] += 1;
            p[1] += 1;
            q.add(p);
            extraStudents--;
        }
        double sum = 0;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            sum += p[0] * 1.0 / p[1];
        }
        // System.out.println(sum);
        return sum / n;
    }


    public double maxAverageRatio1(int[][] classes, int extraStudents) {
        HeapArray q = new HeapArray(classes);
        // 通过率 = 通过人数 / 当前班级学生
        // 平均通过率 = 通过率总和 / 全部班级
        // 通过率总和 = a / tota  + b / totb + c / totc
        // x / y
        // (x + 2 ) / ( y + 2) - ( x + 1) / ( y + 1)  < (x + 1 / y + 1 ) - x / y
        int n = classes.length;
        while (extraStudents > 0 && !q.isEmpty()) {
            int[] p = q.poll();
            p[0] += 1;
            p[1] += 1;
            q.add(p);
            extraStudents--;
        }
        double sum = 0;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            sum += p[0] * 1.0 / p[1];
        }
        // System.out.println(sum);
        return sum / n;
    }


    static class HeapArray {
        private boolean isBigHeap = true; // 最大堆
        public int size;
        int[][] arr;

        public HeapArray(int row, int col, boolean isBigHeap) {
            this.arr = new int[row][col];
            this.isBigHeap = isBigHeap;
        }


        public HeapArray(int[][] arr) {
            this(arr, arr.length, true);
        }

        public HeapArray(int[][] arr, int size, boolean isBigHeap) {
            this.arr = arr;
            this.size = size;
            this.isBigHeap = isBigHeap;
            this.buildHeap(size);
        }


        public static double ccp(int[] a) {
            double d1 = (a[0] * 1.0 + 1) / (a[1] + 1);
            double d2 = (a[0] * 1.0) / (a[1]);
            return d1 - d2;
        }

        /**
         * 针对不同情况请自定义实现堆排序比较器
         *
         * @param a 强大的
         * @param b 弱小的
         * @return int
         */
        public int cmp(int[] a, int[] b) {
            // a compare b
            double c = ccp(a)-ccp(b); // default compare

            // example
            // isBigHeap = true 最大堆
            // isBigHeap = true 最小堆
            // int c = a[0] == b[0] ? a[1] - b[1] : a[0] - b[0];
            // int c = a[1] == b[1] ? a[0] - b[0] : a[1] - b[1];
            // int c = a[1] - b[1];


            if (c == 0) {
                return 0;
            } else if (c > 0) {
                return isBigHeap ? 1 : -1;
            } else {
                return isBigHeap ? -1 : 1;
            }

        }

        // 构建堆
        public void buildHeap(int n) {
            for (int i = 0; i < n; i++) {
                heapInsert(i);
            }
        }


        public void heapInsert(int i) {
            while (i > 0 && cmp(arr[i], arr[(i - 1) / 2]) > 0) {
                swap(i, (i - 1) / 2);
                i = (i - 1) / 2;
            }

        }


        public void heapify() {
            heapify(0, arr.length);
        }

        public void heapify(int i, int size) {
            int l = i * 2 + 1;
            while (l < size) {
                int best = 0;
                if (l + 1 < size && cmp(arr[l + 1], arr[l]) > 0) {
                    best = l + 1;
                } else {
                    best = l;
                }
                if (cmp(arr[i], arr[best]) >= 0) {
                    break;
                }
                swap(i, best);
                i = best;
                l = 2 * i + 1;
            }
        }

        public void swap(int i, int j) {
            int[] temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        public int[] peek() {
            return arr[0];
        }

        public int[] poll() {
            if (isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
            int[] temp = arr[0]; // 取出堆顶元素
            size--;
            swap(0, size);
            heapify(0, size); // 下沉
            return temp;
        }

        public void add(int... newData) {
            if (size >= arr.length) {
                throw new IndexOutOfBoundsException();
            }
            for (int i = 0; i < newData.length; i++) {
                arr[size][i] = newData[i];
            }
            heapInsert(size);
            size++;
        }


        public int size() {
            return this.size;
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        public void clear() {
            this.size = 0;
        }

        /**
         * 堆排序
         */
        public void heapSort() {
            buildHeap(size);
            int cur = size;
            while (cur > 0) {
                cur--;
                swap(0, cur);
                heapify(0, cur);
            }
        }
    }


}