package template.heap;

import code_generation.utils.RandomArrayUtils;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author: wuxin0011
 * @Description:
 */
public class HeapQueueTemplate {


    /**
     * <a href="https://www.nowcoder.com/profile/930660180/codeBookDetail?submissionId=450338307">测试地址</a>
     */
    public static class MyPriorityQueue {
        int[] a;
        int size;

        public MyPriorityQueue(int maxSize) {
            this.a = new int[maxSize];
            this.size = 0;
        }

        public MyPriorityQueue(int[] array, int maxSize) {
            this.a = new int[maxSize];
            this.size = array.length;
            System.arraycopy(array, 0, a, 0, array.length);
            for (int i = 0; i < size; i++) {
                heapInsert(i);
            }
        }

        public void clear() {
            this.size = 0;
        }

        public boolean cmp(int i, int j) {
            return a[i] >= a[j];
        }

        public void swap(int i, int j) {
            int t = a[i];
            a[i] = a[j];
            a[j] = t;
        }

        public void heapInsert(int i) {
            while (i > 0 && cmp(i, (i - 1) / 2)) {
                swap(i, (i - 1) / 2);
                i = (i - 1) / 2;
            }
        }

        public void heapify(int i) {
            int l = i * 2 + 1;
            while (l < size) {
                int best = l + 1 < size && cmp(l + 1, l) ? l + 1 : l;
                if (cmp(i, best)) {
                    break;
                }
                swap(i, best);
                i = best;
                l = i * 2 + 1;
            }
        }

        public void add(int... vals) {
            for (int x : vals) {
                size++;
                a[size - 1] = x;
                heapInsert(size - 1);
            }
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        public int peek() {
            if (isEmpty()) {
                throw new RuntimeException("queue is Empty");
            }
            return a[0];
        }

        public int poll() {
            if (isEmpty()) {
                throw new RuntimeException("queue is Empty");
            }
            int val = a[0];
            swap(0, size - 1);
            size--;
            heapify(0);
            return val;
        }
    }


    public static void main(String[] args) {


        boolean ok = true;
        for (int t = 0; ok && t < 100; t++) {
            int[] a = RandomArrayUtils.randomIntArray(10000, -100000, 100000);
            MyPriorityQueue myQueue = new MyPriorityQueue(a.length + 100);
            PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
            for (int x : a) {
                pq.add(x);
                myQueue.add(x);
            }

            // System.out.println("pq.size = " + pq.size() + ",myQueue.size = " + myQueue.size);
            while (!pq.isEmpty() && !myQueue.isEmpty()) {
                int x = pq.poll();
                int y = myQueue.poll();
                if (x != y) {
                    System.out.println("pq = " + x + ",q = " + y);
                    ok = false;
                    break;
                }
            }


            if (!pq.isEmpty() || !myQueue.isEmpty()) {
                ok = false;
                break;
            }


        }
        System.out.println(ok ? "ok" : "error");
    }
}
