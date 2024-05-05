package template.heap;

/**
 * @author: wuxin0011
 * @Description:
 */
public class HeapDouble {

    public static void swap(double[] arr, int i, int j) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 构建堆
    public static void buildHeap(double[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            heapInsert(arr, i);
        }
    }


    public static void heapInsert(double[] arr, int i) {
        while (i > 0 && cmp(arr[i], arr[(i - 1) / 2]) > 0) {
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }

    }

    private final static boolean isBigHeap = true; // 最大堆

    public static int cmp(double a, double b) {
        // a compare b
        double c = a - b;
        if (c == 0) {
            return 0;
        } else if (c > 0) {
            return isBigHeap ? 1 : -1;
        } else {
            return isBigHeap ? -1 : 1;
        }

    }

    public static void heapify(double[] arr, int i, int size) {
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


    public static double peek(double[] arr) {
        return arr[0];
    }
}
