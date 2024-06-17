package template.array;

import code_generation.utils.RandomArrayUtils;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description: 二分查找
 */
public class BinarySearch {

    /**
     * 查找 target 第一次出现位置
     *
     * @return
     */
    public static int findLeft(int[] arr, int target) {
        if (arr == null) {
            return -1;
        }
        int l = 0, r = arr.length;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] >= target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    /**
     * 查找 第一个大于 target位置
     *
     * @return > target index
     */
    public static int findRight(int[] arr, int target) {
        if (arr == null) {
            return -1;
        }
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }


    // 下面两种写法和上面方法等价
    // [l,r) 写法
    public static int findLeft1(int[] arr, int target) {
        if (arr == null) {
            return -1;
        }
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    // 开区间写法
    // (l,r)
    public static int findLeft2(int[] arr, int target) {
        if (arr == null) {
            return -1;
        }
        int l = -1, r = arr.length;
        while (l + 1 < r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] >= target) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }


    public static void main(String[] args) {
        boolean ok = true;
        int T = 1000;
        while (T > 0) {
            T--;
            int[] arr = RandomArrayUtils.randomIntArray((int) 1e5, 10, 0x3fffff);
            Arrays.sort(arr);
            int index = Math.max(0, Math.min(RandomArrayUtils.randomValue(0, arr.length - 1), arr.length - 1));
            int target = arr[index];
            if (findLeft(arr, target) != checkLeft(arr, target)) {
                System.out.println("findLeft Error 1");
                ok = false;
                break;
            }
            if (findRight(arr, target) != checkRight(arr, target)) {
                System.out.println("findRight Error 1");
                ok = false;
                break;
            }
            // 查找第一个大于 target 位置可以 让 target + 1
            if (findLeft(arr, target + 1) != checkRight(arr, target)) {
                System.out.println("findLeft Error 2");
                ok = false;
                break;
            }

            // 查找最后小于等于 target 位置可以 让 (target + 1) - 1
            if (findLeft(arr, target + 1) - 1 != checkLast(arr, target)) {
                System.out.println("findLeft Error 3");
                ok = false;
                break;
            }
            // 查找最后小于等于 target 位置可以 让 (target + 1) - 1
            if (findLeft1(arr, target + 1) - 1 != checkLast(arr, target)) {
                System.out.println("findLeft1 Error");
                ok = false;
                break;
            }
            // 查找最后小于等于 target 位置可以 让 (target + 1) - 1
            if (findLeft2(arr, target + 1) - 1 != checkLast(arr, target)) {
                System.out.println("findLeft2 Error");
                ok = false;
                break;
            }

        }
        System.out.println(ok ? "ok" : "error");
    }


    // 暴力方法
    public static int checkLeft(int[] arr, int t) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= t) {
                return i;
            }
        }
        return -1;
    }


    // 查找最后一个小于等于 t 的位置
    public static int checkLast(int[] arr, int t) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= t) {
                return i;
            }
        }
        return -1;
    }

    public static int checkRight(int[] arr, int t) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > t) {
                return i;
            }
        }
        return -1;
    }
}
