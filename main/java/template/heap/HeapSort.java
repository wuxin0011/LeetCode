package template.heap;

/**
 * @author: wuxin0011
 * @Description:
 */
public class HeapSort {
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void heapInsert(int[] arr, int i) {
        while (i > 0 && arr[i] > arr[(i - 1) / 2]) {
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    public static void heapify(int[] arr, int i, int size) {
        int l = i * 2 + 1;
        while (l < size) {
            int best = l + 1 < size && arr[l + 1] > arr[l] ? l + 1 : l;
            best = arr[best] > arr[i] ? best : i;
            if (best == i) break;
            swap(arr, i, best);
            i = best;
            l = 2 * i + 1;
        }
    }

    public static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            heapInsert(arr, i);
        }
        int size = n;
        while (size > 0) {
            swap(arr, 0, size - 1);
            heapify(arr, 0, size - 1);
            size--;
        }
    }
}
