package template.stack;

import java.util.ArrayList;

/**
 * 手写栈效率更高同时还能获取栈中不同数据 有时候需要获取栈的 第 x 条数据 这时候系统库就不好用了
 * 其中 MyStack 这个类可以被
 *
 * @see java.util.Deque
 * @see java.util.Stack
 * @see ArrayList
 */
public class StackTemplate {


    // ====================模板开始=====================
    private static final StackInt sk = new StackInt((int)1e5 + 10);
    public static class StackInt {
        int[] stack;
        int cap; // 最大容量
        int size;

        public StackInt(int cap) {
            this.cap = cap;
            this.stack = new int[cap];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // last 是一个负数 例如栈顶 可以 直接 get(-1) 栈顶第二个元素 get(-2)
        public int get(int last) {
//            if (size + last < 0 || size + last >= cap) {
//                throw new IndexOutOfBoundsException();
//            }
            return stack[size + last];
        }

        public int peek() {
            return get(-1);
        }

        public void push(int x) {
            stack[size++] = x;
        }

        public void set(int i,int x) {
            stack[i] = x;
        }

        public void clear() {
            size = 0;
        }

        public int pop() {
            return stack[--size];
        }
    }
    // ====================模板结束=====================


    // 有时候需要维护下标之外的其他信息
    // 其实可以使用ArrayList<> 替代这个 但是本人不喜欢用
    // ====================模板开始=====================
    public static class MyStack<T> {
        Object[] stack;
        int cap; // 最大容量
        int size;

        public MyStack(int cap) {
            this.cap = cap;
            this.stack = new Object[cap];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // last 是一个负数 例如栈顶 可以 直接 get(-1) 栈顶第二个元素 get(-2)
        public T get(int last) {
//            if (size + last < 0 || size + last >= cap) {
//                throw new IndexOutOfBoundsException();
//            }
            return (T) stack[size + last];
        }

        public T peek() {
            return get(-1);
        }

        public void push(T x) {
            stack[size++] = x;
        }


        public void set(int i,T x) {
            stack[i] = x;
        }

        public void clear() {
            size = 0;
        }

        public T pop() {
            return (T) stack[--size];
        }

    }
    // ====================模板结束=====================






    // 常见单调栈 求 a[i] 为最小值的 (l,r)
    // (l,r) 为开区间 如果是 计算区间长度 (r - l -1） 计算前缀和 (sums[r] - sums[l+1])
    // 注意如果使用虚拟节点 原来下标遍历需要 + 1 才能对应
    // -1 表示不存在
    // =================================单调栈模板开始=====================
    public static int[][] minArray(int[] a,boolean addDumpNode) {
        // 如果需要添加哨兵节点
        if(addDumpNode){
            int inf = Integer.MAX_VALUE >> 1;
            int[] dump = new int[a.length + 2];
            dump[0] = -inf;
            System.arraycopy(a, 0, dump, 1, a.length);
            dump[dump.length - 1] = inf;
            a = dump;
        }
        int n = a.length;
        int[][] ids = new int[n][2];
        int[] sk = new int[n];
        int size = 0;
        for (int i = 0; i < n; i++) {
            while (size > 0 && a[sk[size - 1]] >= a[i]) {
                size--;
            }
            ids[i][0] = size > 0 ? sk[size - 1] : -1;
            sk[size++] = i;
        }
        size = 0;
        for (int i = n - 1; i >= 0; i--) {
            while (size > 0 && a[sk[size - 1]] >= a[i]) {
                size--;
            }
            ids[i][1] = size > 0 ? sk[size - 1] : -1;
            sk[size++] = i;
        }
        return ids;
    }

    // 将 a[i] 转换成负 就是求最大值 的第一个范围了
    public static int[][] maxArray(int[] a,boolean addDumpNode) {
        int[] temp = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            temp[i] = -a[i];
        }
        return minArray(temp,addDumpNode);
    }

    // =================================单调栈模板开始=====================



    // 举例子
    // https://leetcode.cn/problems/maximum-subarray-min-product/
    public int maxSumMinProduct(int[] nums) {
        int[][] ids = minArray(nums,true);
        int n = nums.length;
        long ans = 0;
        int mod = (int)1e9 + 7;
        long[] sums = new long[n + 4];
        // 前缀和下标对应
        for(int i = 1;i <= n;i++) sums[i + 1] = sums[i] + nums[i - 1];
        for(int i = 1;i <= n;i++){
            int x = nums[i - 1];
            ans = Math.max(ans, x * (sums[ids[i][1]] - sums[ids[i][0] + 1]));
        }
        ans %= mod;
        return (int)ans;
    }

}
