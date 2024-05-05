package test;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 */
public class T2 {

    public static void main(String[] args) throws Exception {
        // Class<T1> t1Class = T1.class;

//        for (int i = 0; i < 3; i++) {
//            Class<?> a1 = Class.forName(T1.class.getName());
//            System.out.println("T1.class.getName():" + T1.class.getName());
//        }
//        double a = 30.66667;
//        double b = 30.666666666666664;
//        System.out.println(Double.parseDouble(String.valueOf(a)) == Double.parseDouble(String.valueOf(b)));
//        a = Double.parseDouble(String.format("%.5f", a));
//        b = Double.parseDouble(String.format("%.5f", b));
//        System.out.println(a == b);
//        System.out.println(a - b);

        int[] arr = {1, 2, 2, 3, 100, -2, 100, 10};
        System.out.println("before : " + Arrays.toString(arr));
        heapSort(arr);
        System.out.println("after : " + Arrays.toString(arr));
        valid(arr);

    }

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
            swap(arr,i,best);
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
            swap(arr, 0, size-1);
            heapify(arr, 0, size-1);
            size--;
        }
    }



}
