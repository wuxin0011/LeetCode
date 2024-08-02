package template.array;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 */
public class OneDiffTemplate {


    // 一个维差分
    static class D {
        int n;
        int[] diffs;

        D(int[] a) {
            n = a.length;
            diffs = new int[n + 1];
        }

        void add(int l, int r, int v) {
            diffs[l] += v;
            diffs[r + 1] -= v;
        }

        void origin(int[] a) {
            for (int i = 1; i < n + 1; i++) {
                diffs[i] += diffs[i - 1];
                a[i] += diffs[i - 1];
            }
        }
    }


    // 一维等差差分
    static class OneDiff {

        int[] diff;

        int[] arr;

        OneDiff(int[] arr) {
            this.arr = arr;
            this.init();
        }

        void init() {
            int N = arr.length + 2;
            this.diff = new int[N];

            // 变成差分数组
            for (int i = 0; i < arr.length; i++) {
                if (i == 0) {
                    diff[i] = arr[i];
                } else {
                    diff[i] = arr[i] - arr[i - 1];
                }
            }
        }


        void update(int l, int r, int s, int d) {
            int e = (r - l + 1) * d + s;
            update(l, r, s, d, e);
        }


        /**
         * @param l 左
         * @param r 右
         * @param s 等差首
         * @param d 等差 D
         * @param e 末尾值
         */
        void update(int l, int r, int s, int d, int e) {
            this.diff[l] += s;
            this.diff[l + 1] += d - s;
            this.diff[r + 1] -= (e + d);
            this.diff[r + 2] += e;
        }


        int[] origin() {
            int[] a = new int[this.diff.length - 2];
            for (int x = 0; x < 2; x++) {
                for (int i = 1; i < this.diff.length; i++) {
                    this.diff[i] += this.diff[i - 1];
                }
            }
            for (int i = 0; i < this.diff.length - 2; i++) {
                a[i] = this.diff[i];
            }
            return a;
        }
    }


    public static void main(String[] args) {

        int T = 10;
        boolean ok = true;
        while (--T > 0 && ok) {

            int N = (int) (Math.random() * 30);

            int[] arr = new int[N];

            for (int i = 0; i < N; i++) {
                arr[i] = (int) (Math.random() * 20);
            }


            int[] nums = Arrays.copyOf(arr, arr.length);
            OneDiff oneDiff = new OneDiff(arr);


            int k = 100;
            boolean u = false;
            while (--k > 0 && ok) {


                boolean op = Math.random() <= 0.5;


                if (op) {
                    int S = (int) (Math.random() * N);
                    int D = (int) (Math.random() * 10);

                    int a = (int) (Math.random() * N);
                    int b = (int) (Math.random() * N);

                    int l = Math.min(a, b);
                    int r = Math.max(a, b);
                    u = true;
                    oneDiff.update(l, r, S, D);
                    update(nums, l, r, S, D);
                } else {
                    if (u) {
                        int[] origin = oneDiff.origin();
                        if (origin.length != nums.length) {
                            ok = false;
                            System.out.println("length Not equal");
                            break;
                        }
                        for (int i = 0; i < N; i++) {
                            if (origin[i] != nums[i]) {
                                System.out.println("nums   :" + Arrays.toString(nums));
                                System.out.println("origin :" + Arrays.toString(origin));
                                ok = false;
                                break;
                            }
                        }
                    }

                }
            }


        }

        System.out.println(ok ? "ok" : "error");

    }


    /*测试部分*/
    public static void update(int[] arr, int l, int r, int s, int d) {
        for (int i = l; i <= r; i++) {
            arr[i] += (s + (i - l) * d);
        }
    }
}
