package template.heap;

/**
 * @author: wuxin0011
 * @Description:
 */
public class HeapInt {


    private final static boolean isBigHeap = true; // 最大堆

    // 构建堆
    public static void buildHeap(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            heapInsert(arr, i);
        }
    }


    public static void heapInsert(int[] arr, int i) {
        while (i > 0 && cmp(arr[i], arr[(i - 1) / 2]) > 0) {
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }

    }


    public static int cmp(int a, int b) {
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


    public static void heapify(int[] arr) {
        heapify(arr, 0, arr.length);
    }

    public static void heapify(int[] arr, int i, int size) {
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
            swap(arr, i, best);
            i = best;
            l = 2 * i + 1;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int peek(int[] arr) {
        return arr[0];
    }

//    public static void heapSort(int[] arr) {
//        int n = arr.length;
//        for (int i = 0; i < n; i++) {
//            heapInsert(arr, i);
//        }
//        int size = n;
//        while (size > 0) {
//            swap(arr, 0, size - 1);
//            heapify(arr, 0, size - 1);
//            size--;
//        }
//    }
}
