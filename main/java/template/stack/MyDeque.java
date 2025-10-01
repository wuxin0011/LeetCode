package template.stack;

/**
 * @author: wuxin0011
 * @Description:
 */
public class MyDeque {
    int[] arr;
    int l, r;

    MyDeque(int n) {
        arr = new int[n + 1];
        l = r = 0;
    }

    void clear() {
        l = r = 0;
    }

    boolean isEmpty() {
        return !(l < r);
    }

    int peekFirst() {
        return arr[l];
    }

    int pop() {
        return pollLast();
    }

    int peek() {
        return peekLast();
    }

    int pollFirst() {
        return arr[l++];
    }

    int peekLast() {
        return arr[r - 1];
    }

    int pollLast() {
        return arr[--r];
    }

    void add(int x) {
        addLast(x);
    }

    void addLast(int x) {
        arr[r++] = x;
    }

    void addFirst(int x) {
        arr[--l] = x;
    }

    int get(int i) {
        return i >= 0 ? arr[l + i] : arr[r + i];
    }

    int size() {
        return r - l < 0 ? 0 : r - l;
    }
}
