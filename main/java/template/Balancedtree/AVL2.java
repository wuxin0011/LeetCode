package template.Balancedtree;

import java.util.Arrays;

/**
 * https://www.luogu.com.cn/problem/P6136
 * @author: wuxin0011
 * @Description: AVL 实现加强版
 */
public class AVL2 {
    public int cnt, head;
    public  int[] key, height, left, right, count, size;

    public AVL2(int n) {
        key = new int[n];
        height = new int[n];
        left = new int[n];
        right = new int[n];
        count = new int[n];
        size = new int[n];
        cnt = head = 0;
    }

    public  void up(int i) {
        size[i] = size[left[i]] + size[right[i]] + count[i];
        height[i] = Math.max(height[left[i]], height[right[i]]) + 1;
    }

    public  int leftRotate(int i) {
        int r = right[i];
        right[i] = left[r];
        left[r] = i;
        up(i);
        up(r);
        return r;
    }

    public  int rightRotate(int i) {
        int l = left[i];
        left[i] = right[l];
        right[l] = i;
        up(i);
        up(l);
        return l;
    }

    public  int maintain(int i) {
        int lh = height[left[i]];
        int rh = height[right[i]];
        if (lh - rh > 1) {
            if (height[left[left[i]]] >= height[right[left[i]]]) {
                i = rightRotate(i);
            } else {
                left[i] = leftRotate(left[i]);
                i = rightRotate(i);
            }
        } else if (rh - lh > 1) {
            if (height[right[right[i]]] >= height[left[right[i]]]) {
                i = leftRotate(i);
            } else {
                right[i] = rightRotate(right[i]);
                i = leftRotate(i);
            }
        }
        return i;
    }

    public  void add(int num) {
        head = add(head, num);
    }

    public  int add(int i, int num) {
        if (i == 0) {
            key[++cnt] = num;
            count[cnt] = size[cnt] = height[cnt] = 1;
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
        return maintain(i);
    }

    public  void remove(int num) {
        if (rank(num) != rank(num + 1)) {
            head = remove(head, num);
        }
    }

    public  int remove(int i, int num) {
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
                    int mostLeft = right[i];
                    while (left[mostLeft] != 0) {
                        mostLeft = left[mostLeft];
                    }
                    right[i] = removeMostLeft(right[i], mostLeft);
                    left[mostLeft] = left[i];
                    right[mostLeft] = right[i];
                    i = mostLeft;
                }
            }
        }
        up(i);
        return maintain(i);
    }

    public  int removeMostLeft(int i, int mostLeft) {
        if (i == mostLeft) {
            return right[i];
        } else {
            left[i] = removeMostLeft(left[i], mostLeft);
            up(i);
            return maintain(i);
        }
    }

    public  int rank(int num) {
        return small(head, num) + 1;
    }

    public  int small(int i, int num) {
        if (i == 0) {
            return 0;
        }
        if (key[i] >= num) {
            return small(left[i], num);
        } else {
            return size[left[i]] + count[i] + small(right[i], num);
        }
    }

    public  int index(int x) {
        return index(head, x);
    }

    public  int index(int i, int x) {
        if (size[left[i]] >= x) {
            return index(left[i], x);
        } else if (size[left[i]] + count[i] < x) {
            return index(right[i], x - size[left[i]] - count[i]);
        }
        return key[i];
    }

    public  int floor(int num) {
        return floor(head, num);
    }

    public  int floor(int i, int num) {
        if (i == 0) {
            return Integer.MIN_VALUE;
        }
        if (key[i] >= num) {
            return floor(left[i], num);
        } else {
            return Math.max(key[i], floor(right[i], num));
        }
    }

    public  int ceil(int num) {
        return ceil(head, num);
    }

    public  int ceil(int i, int num) {
        if (i == 0) {
            return Integer.MAX_VALUE;
        }
        if (key[i] <= num) {
            return ceil(right[i], num);
        } else {
            return Math.min(key[i], ceil(left[i], num));
        }
    }

    public  void clear() {
        Arrays.fill(key, 1, cnt + 1, 0);
        Arrays.fill(height, 1, cnt + 1, 0);
        Arrays.fill(left, 1, cnt + 1, 0);
        Arrays.fill(right, 1, cnt + 1, 0);
        Arrays.fill(count, 1, cnt + 1, 0);
        Arrays.fill(size, 1, cnt + 1, 0);
        cnt = 0;
        head = 0;
    }

}
