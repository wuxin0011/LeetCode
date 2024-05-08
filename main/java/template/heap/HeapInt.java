package template.heap;

/**
 * @author: wuxin0011
 * @Description: 堆模板 一般可替优先级队列！
 */
public class HeapInt {


    public static boolean valid(int[] arr) {
        boolean ok = true;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                ok = false;
                break;
            }
        }
        if (ok) {
            System.out.println("ok");
        } else {
            System.out.println("error");
        }
        return ok;
    }


    public static void main(String[] args) {
        for (int t = 0; t < 10; t++) {
            int len = (int) (Math.random() * 1000);
            int[] arr = new int[len];
            for (int i = 0; i < len; i++) {
                arr[i] = (int) (Math.random() * 1000);
            }
            Heap heap = new Heap(arr, arr.length, true);
            heap.heapSort();
            // System.out.println(Arrays.toString(arr));
            valid(arr);
        }
//        while (!heap.isEmpty()) {
//            System.out.println(heap.poll());
//        }
    }

    /**
     * 一维数组
     */
    static class Heap {
        private boolean isBigHeap = true; // 最大堆
        public int size;
        int[] arr;

        public Heap(int size, boolean isBigHeap) {
            this.arr = new int[size];
            this.size = 0;
            this.isBigHeap = isBigHeap;
        }


        public Heap(int[] arr) {
            this(arr, arr.length, true);
        }

        public Heap(int[] arr, int size, boolean isBigHeap) {
            this.arr = arr;
            this.size = size;
            this.isBigHeap = isBigHeap;
            this.buildHeap(size);
        }


        /**
         * 针对不同情况请自定义实现堆排序比较器
         *
         * @param a 强大的
         * @param b 弱小的
         * @return int
         */
        public int cmp(int a, int b) {
            // a compare b
            int c = a - b;
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
                this.heapInsert(i);
            }
        }


        public void heapInsert(int i) {
            while (i > 0 && cmp(arr[i], arr[(i - 1) / 2]) > 0) {
                this.swap(i, (i - 1) / 2);
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
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        public int peek() {
            return arr[0];
        }

        public int poll() {
            if (isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
            int temp = arr[0]; // 取出堆顶元素
            size--;
            swap(0, size);
            heapify(0, size); // 下沉
            return temp;
        }

        public void add(int newData) {
            if (size >= arr.length) {
                throw new IndexOutOfBoundsException();
            }
            arr[size] = newData;
            heapInsert(size);
            size++;
        }

        public int size() {
            return this.size;
        }

        public void clear() {
            this.size = 0;
        }

        public boolean isEmpty() {
            return this.size == 0;
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


    /**
     * 多维数组
     */
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


        /**
         * 针对不同情况请自定义实现堆排序比较器
         *
         * @param a 强大的
         * @param b 弱小的
         * @return int
         */
        public int cmp(int[] a, int[] b) {
            // a compare b
            int c = a[0] - b[0]; // default compare

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

//        public void add(int[] newData) {
//            if (size >= arr.length) {
//                throw new IndexOutOfBoundsException();
//            }
//            arr[size] = newData;
//            heapInsert(size);
//            size++;
//        }

        public void add(int ... newData) {
            if (size >= arr.length) {
                throw new IndexOutOfBoundsException();
            }
            for(int i = 0;i<newData.length;i++){
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
