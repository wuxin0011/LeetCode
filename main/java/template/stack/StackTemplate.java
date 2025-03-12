package template.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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


    public static void main(String[] args) {
        MyStack<int[]> sk = new MyStack<>(20);
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < 19; i++) {
            sk.push(new int[]{i, i * i});
        }
        System.out.println(Arrays.toString(sk.get(-1)));
        System.out.println(Arrays.toString(sk.get(-2)));
    }
}
