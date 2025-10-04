package template.Balancedtree;

/**
 *
 *
 * 由于Java对象封装不容易比较，c++版本容易拓展
 * 参考链接 ： https://leetcode.cn/problems/xx4gT2/submissions/667971855/ 动态数组版本
 * 参考链接 ： https://leetcode.cn/problems/xx4gT2/submissions/667973116/ 数组版本
 *
 *
 * @author: wuxin0011
 * @Description: treap 的实现
 */
public class Treap {


    // 节点优先级
    public double[] priority;


    public int cnt, head;
    public int[] key, height, left, right, count, size;

    public Treap(int n) {
        key = new int[n];
        height = new int[n];
        left = new int[n];
        right = new int[n];
        count = new int[n];
        size = new int[n];
        priority = new double[n];
        cnt = head = 0;
    }

    public void up(int i) {
        size[i] = size[left[i]] + size[right[i]] + count[i];
    }

    public int leftRotate(int i) {
        int r = right[i];
        right[i] = left[r];
        left[r] = i;
        up(i);
        up(r);
        return r;
    }

    public int rightRotate(int i) {
        int l = left[i];
        left[i] = right[l];
        right[l] = i;
        up(i);
        up(l);
        return l;
    }

    public int add(int i, int num) {
        if (i == 0) {
            key[++cnt] = num;
            count[cnt] = size[cnt] = 1;
            priority[cnt] = Math.random();
            return cnt;
        }
        if (key[i] == num) {
            count[i]++;
        } else if (key[i] > num) {
            left[i] = add(left[i], num);
        } else {
            right[i] = add(right[i], num);
        }
        up(i);
        if (left[i] != 0 && priority[left[i]] > priority[i]) {
            return rightRotate(i);
        }
        if (right[i] != 0 && priority[right[i]] > priority[i]) {
            return leftRotate(i);
        }
        return i;
    }

    public void add(int num) {
        head = add(head, num);
    }

    public int small(int i, int num) {
        if (i == 0) {
            return 0;
        }
        if (key[i] >= num) {
            return small(left[i], num);
        } else {
            return size[left[i]] + count[i] + small(right[i], num);
        }
    }

    public int rank(int num) {
        return small(head, num) + 1;
    }

    public int index(int i, int x) {
        if (size[left[i]] >= x) {
            return index(left[i], x);
        } else if (size[left[i]] + count[i] < x) {
            return index(right[i], x - size[left[i]] - count[i]);
        }
        return key[i];
    }

    public int index(int x) {
        return index(head, x);
    }

    public int floor(int i, int num) {
        if (i == 0) {
            return Integer.MIN_VALUE;
        }
        if (key[i] >= num) {
            return floor(left[i], num);
        } else {
            return Math.max(key[i], floor(right[i], num));
        }
    }

    public int floor(int num) {
        return floor(head, num);
    }

    public int ceil(int i, int num) {
        if (i == 0) {
            return Integer.MAX_VALUE;
        }
        if (key[i] <= num) {
            return ceil(right[i], num);
        } else {
            return Math.min(key[i], ceil(left[i], num));
        }
    }

    public int ceil(int num) {
        return ceil(head, num);
    }

    public int remove(int i, int num) {
        if (key[i] < num) {
            right[i] = remove(right[i], num);
        } else if (key[i] > num) {
            left[i] = remove(left[i], num);
        } else {
            if (count[i] > 1) {
                count[i]--;
            } else {
                if (left[i] == 0 && right[i] == 0) {
                    return 0;
                } else if (left[i] != 0 && right[i] == 0) {
                    i = left[i];
                } else if (left[i] == 0 && right[i] != 0) {
                    i = right[i];
                } else {
                    if (priority[left[i]] >= priority[right[i]]) {
                        i = rightRotate(i);
                        right[i] = remove(right[i], num);
                    } else {
                        i = leftRotate(i);
                        left[i] = remove(left[i], num);
                    }
                }
            }
        }
        up(i);
        return i;
    }

    public void remove(int num) {
        if (rank(num) != rank(num + 1)) {
            head = remove(head, num);
        }
    }

    public void clear() {
        for (int i = 0; i <= cnt; i++) {
            key[i] = left[i] = right[i] = count[i] = size[i] = 0;
            priority[i] = 0;
        }
        cnt = 0;
        head = 0;
    }
}
