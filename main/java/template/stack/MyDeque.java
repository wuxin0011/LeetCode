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
        return pop(-1);
    }


    int pop(int index) {
        if(index >= 0) {
            if(index==0)index++;
            l += index;
            return arr[l];
        }else{
            r += index;
            return arr[r];
        }
    }

    int peek() {
        return get(-1);
    }

    int pollFirst() {
        return pop(1);
    }

    int peekLast() {
        return get(-1);
    }

    int pollLast() {
        return pop(-1);
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
